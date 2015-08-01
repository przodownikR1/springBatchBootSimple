package pl.java.scalatech.continue_;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class StepContinueParentListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("$$$ before parent step : {}  - {}", stepExecution.getStepName(), stepExecution.getSummary());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("$$$ after parent step : {}  - {}", stepExecution.getStepName(), stepExecution.getSummary());
        return null;
    }
}
