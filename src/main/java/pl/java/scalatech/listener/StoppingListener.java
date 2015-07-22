package pl.java.scalatech.listener;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.Preconditions;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class StoppingListener implements StepExecutionListener  {

    @Override
    public void beforeStep(StepExecution stepExecution) {

        
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        JobParameters params = stepExecution.getJobExecution().getJobParameters();
        Preconditions.checkNotNullOrEmpty(params.getString("test"));
        if("true".equals(params.getString("error"))) {
            log.info("$$$  NO_DATA STATUS");
            return new ExitStatus("NO_DATA");
        }
        log.info("$$$  DATA_EXISTS STATUS");
        return new ExitStatus("DATA_EXISTS");
        
    
    }

}
