package pl.java.scalatech.job;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import pl.java.scalatech.domain.Person;
import pl.java.scalatech.fixed.PersonFixedLengthTokenizer;

@Slf4j
@Configuration
@Profile("fixed")
public class FixedLengthPersonJob {
    @Bean
    public ItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("personFixLength.txt"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {
            {
                setLineTokenizer(new PersonFixedLengthTokenizer());
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
    public ItemWriter<Person> writer() {
        return items -> items.stream().forEach(p -> log.info("+++  {}", p));

    }

    @Bean
    public Job fixedJob(JobBuilderFactory factory, Step s1) {
        return factory.get("fixedJob").incrementer(new RunIdIncrementer()).flow(s1).end().build();
    }

    @Bean
    public Step step1(StepBuilderFactory factory, ItemReader<Person> reader, ItemWriter<Person> writer) {
        return factory.get("step1").<Person, Person> chunk(1).reader(reader).writer(writer).build();
    }

}
