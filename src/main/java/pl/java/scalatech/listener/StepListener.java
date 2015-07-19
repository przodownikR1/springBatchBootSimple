package pl.java.scalatech.listener;

import java.time.LocalTime;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class StepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
       log.info("StepExecutionListener - {} begin at  {}", stepExecution.getStepName(),LocalTime.now());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("StepExecutionListener - {} end at {} ", stepExecution.getStepName(),LocalTime.now());
                
        return null;
    }

}