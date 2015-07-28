package springBatchBootSimple;

import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.BatchConfig;
import pl.java.scalatech.job.ConditionalJob;

import com.google.common.collect.Maps;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {BatchConfig.class,ConditionalJob.class})
@ActiveProfiles("conditional")
public class ConditionalJavaConfigJobTest {

    @Autowired
    private Job cjob;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobExplorer jobExplorer;
    
    
    @Test
    public void shouldInterfacesInstanceExists() {
        Assertions.assertThat(jobRepository).isNotNull();
        Assertions.assertThat(jobExplorer).isNotNull();
    }
    
    @Test
   
    public void shouldTaskletWork() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        Map<String,JobParameter> params = Maps.newHashMap();
        params.put("time", new JobParameter(new Date()));
        JobExecution execution = jobLauncher.run(cjob, new JobParameters(params));
        log.info("Exit Status :  {}", execution.getExitStatus());
        for (StepExecution step : execution.getStepExecutions()) {
            log.info("step {}, {}",step.getStepName(),step.getExitStatus());
            
        }
        Assert.assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
      
           
    }
    
    
}