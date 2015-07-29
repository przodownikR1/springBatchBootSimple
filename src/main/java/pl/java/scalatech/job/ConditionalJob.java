package pl.java.scalatech.job;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@Profile("conditional")
public class ConditionalJob {

    @PostConstruct
    public void init() {
        log.info("+++ conditionalJob");
    }

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job cjob() {
        return jobBuilderFactory.get("job").start(stepFirst()).on("FAILED").to(stepSecond()).from(stepSecond()).on("COMPLETED").to(stepThird())
                .from(stepFirst()).on("COMPLETED").to(stepThird()).end().build();
    }

    Step stepFirst() {
        return stepBuilderFactory.get("stepFirst").tasklet((stepContribution, chunkContext) -> {
            log.info("+++  1 step executed");
            stepContribution.setExitStatus(ExitStatus.FAILED);
            return RepeatStatus.FINISHED;

        }).build();
    }

    Step stepSecond() {
        return stepBuilderFactory.get("stepSecond").tasklet((stepContribution, chunkContext) -> {
            log.info("+++  2 step executed");
            return RepeatStatus.FINISHED;
        }).build();
    }

    Step stepThird() {
        return stepBuilderFactory.get("step3").tasklet((stepContribution, chunkContext) -> {
            log.info("+++ 3 step executed ");
            return RepeatStatus.FINISHED;
        }).build();
    }
}