package springBatchBootSimple.validator;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:validateLaunchParams.xml" })
@ActiveProfiles(value = { "dev" })
@Slf4j
public class ValidatorJobTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job validJob;
    @Autowired
    private DataSource dataSource;

    @Test
    public void shouldInValidJobValidatorWork() throws SQLException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        thrown.expect(JobParametersInvalidException.class);
        log.info(" +++   db driver :  {}", dataSource.getConnection().getMetaData().getDriverName());

        Map<String, JobParameter> params = Maps.newHashMap();
        params.put("date1", new JobParameter(new Date()));
        JobExecution execution = jobLauncher.run(validJob, new JobParameters(params));
        log.info("Exit Status :  {}", execution.getExitStatus());
        Assert.assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
    }

    @Test
    public void shouldValidJobWork() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException {
        Map<String, JobParameter> params = Maps.newHashMap();
        params.put("date", new JobParameter(new Date()));
        JobExecution execution = jobLauncher.run(validJob, new JobParameters(params));
        log.info("Exit Status :  {}", execution.getExitStatus());
        Assert.assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());

    }
}
