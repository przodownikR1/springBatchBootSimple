package pl.java.scalatech.tasklet;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
@Slf4j
public class IndexTasklet  implements Tasklet {
   
    public RepeatStatus execute(final StepContribution sc, final ChunkContext context) throws Exception {  
        log.info("+++ Index tasklet ..... execute !!! ");
        ExecutionContext jobExecutionContext = context.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        String id = jobExecutionContext.getString("ID");
        log.info("+++ retrieve id from context {}",id);
                
    
        return FINISHED;
    }

}