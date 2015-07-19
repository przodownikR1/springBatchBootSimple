package pl.java.scalatech.jobs;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import pl.java.scalatech.repository.PersonRepository;
@Slf4j
@Configuration
public class JpaJob {
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.addScript(resourceLoader.getResource("classpath:person.sql"));

        DatabasePopulatorUtils.execute(populator, dataSource);
        
        log.info("retrieve {}",personRepository.findAll());
        
    }
/*
    @Bean
    @JobScope
    public JpaItemReader itemReader() {
        return new JpaItemReader(entityManagerFactory, "select c from Person ");
    }

    @Bean
    @StepScope
    public JpaItemWriter itemWriter() {
        JpaItemWriter itemWriter = new JpaItemWriter();
        return itemWriter;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<Person, Person> chunk(5).reader(itemReader()).writer(itemWriter()).build();
    }

    @Bean
    public Job javaJob() {
        return jobBuilderFactory.get("jpaJob").start(step()).build();
    }
*/
}
