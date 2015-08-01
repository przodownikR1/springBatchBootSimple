package springBatchBootSimple.writer;

import java.util.Date;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.awaitility.Awaitility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:writerFlatFile.xml" })
@ActiveProfiles(value = { "dev" })
@Slf4j
public class FlatWriterTest {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job passThroughLineFileJob;
    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobExplorer jobExplorer;

    @Test
    public void shouldAwaitUnitDone() throws NoSuchJobException, JobInstanceAlreadyExistsException, JobParametersInvalidException {
        long executionId = jobOperator.start("passThroughLineFileJob", "time" + new Date().getTime());
        Awaitility.await().until(finished(executionId));

    }

    private Callable<Boolean> finished(final long executionId) {
        return () -> jobExplorer.getJobExecution(executionId).isRunning() == false;
    }
}
