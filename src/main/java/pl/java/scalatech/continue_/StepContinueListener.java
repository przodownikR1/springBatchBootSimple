package pl.java.scalatech.continue_;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class StepContinueListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("$$$ before step : {}  - {}", stepExecution.getStepName(), stepExecution.getSummary());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("$$$ after step : {}  - {}", stepExecution.getStepName(), stepExecution.getSummary());
        return null;
    }

}
