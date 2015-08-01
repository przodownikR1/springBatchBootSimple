package springBatchBootSimple.continue_;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

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
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;
import com.jayway.awaitility.Awaitility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:continueTest.xml" })
@ActiveProfiles(value = { "dev" })
@Slf4j
public class ContinueTest {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job continueJob;
    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobExplorer jobExplorer;

    @Test
    public void shouldReadWrite() throws SQLException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException {

        Map<String, JobParameter> params = Maps.newHashMap();
        params.put("time", new JobParameter(new Date()));

        JobExecution execution = jobLauncher.run(continueJob, new JobParameters(params));
        log.info("Exit Status :  {}", execution.getExitStatus());

        Assert.assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());

    }

    @Test
    public void shouldAwaitUnitDone() throws NoSuchJobException, JobInstanceAlreadyExistsException, JobParametersInvalidException {
        long executionId = jobOperator.start("continueJob", "start=1");
        Awaitility.await().until(finished(executionId));

    }

    private Callable<Boolean> finished(final long executionId) {
        return () -> jobExplorer.getJobExecution(executionId).isRunning() == false;
    }
}
