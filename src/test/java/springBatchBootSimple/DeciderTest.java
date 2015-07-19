package springBatchBootSimple;

import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:decider.xml")
public class DeciderTest {
    
    @Autowired
    private Job simpleDeciderTask;
    @Autowired
    private JobLauncher jobLauncher;
    
    @Test
    public void shouldContextStart() {
        Assertions.assertThat(simpleDeciderTask).isNotNull();
        Assertions.assertThat(jobLauncher).isNotNull();
    }
    
    @Test
    public void shouldDeciderWork() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        Map<String,JobParameter> params = Maps.newHashMap();
        params.put("test", new JobParameter("przodownik"));
        params.put("time", new JobParameter(new Date()));
        JobExecution execution = jobLauncher.run(simpleDeciderTask, new JobParameters(params));
        log.info("Exit Status :  {}", execution.getExitStatus());
    }
}
