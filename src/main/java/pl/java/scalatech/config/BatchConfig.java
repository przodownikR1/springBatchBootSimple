package pl.java.scalatech.config;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import pl.java.scalatech.domain.Person;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig {
    @Value("${csv.source}")
    private String csv;

    @Bean
    public ItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(csv));
        reader.setLineMapper(new DefaultLineMapper<Person>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "login", "age" });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
                    {
                        setTargetType(Person.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public ItemProcessor<Person, Person> processor() {
        return item ->
        {
            final String firstName = item.getLogin().toUpperCase();
            String passwd = Hashing.md5().hashString("passwd_" + firstName, Charsets.UTF_8).toString();
            final Person transformed = new Person(firstName, passwd, item.getAge());
            log.info("transform (" + item + ") -> (" + transformed + ")");
            return transformed;
        };
    }

    @Bean
    public ItemWriter<Person> writer(DataSource dataSource) {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        writer.setSql("INSERT INTO person (login, passwd, age) VALUES (:login,:passwd,:age)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step step1, JobExecutionListener listener) {
        return jobs.get("importJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1).end().build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer, ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory.get("step1").<Person, Person> chunk(3).reader(reader).processor(processor).writer(writer).build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
