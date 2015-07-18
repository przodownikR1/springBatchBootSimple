package springBatchBootSimple;

import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:tasklet.xml")
public class HelloTaskletTest {

    @Autowired
    private Job taskletSimple;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobExplorer jobExplorer;
    
    @Test
    public void shouldInterfacesInstanceExists() {
        Assertions.assertThat(jobRepository).isNotNull();
        Assertions.assertThat(jobRepository).isNotNull();
    }
    
    @Test
    public void shouldTaskletWork() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        Map<String,JobParameter> params = Maps.newHashMap();
        params.put("test", new JobParameter("przodownik"));
        params.put("time", new JobParameter(new Date()));
        JobExecution execution = jobLauncher.run(taskletSimple, new JobParameters(params));
        log.info("Exit Status :  {}", execution.getExitStatus());
        Assert.assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
           
    }
    
    


}
