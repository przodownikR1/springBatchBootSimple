package pl.java.scalatech.decider;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.item.ExecutionContext;

@Slf4j
public class RegisterItemDecider implements JobExecutionDecider {
    private Random r = new Random();

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        log.info("+++  jobExecutionContext {} ", jobExecution.getExecutionContext());
        ExecutionContext jobExecutionContext = stepExecution.getJobExecution().getExecutionContext();
        if (r.nextBoolean()) {
            return new FlowExecutionStatus("COMPLETED WITH SKIPS");
        }
        return FlowExecutionStatus.COMPLETED;
    }

}
