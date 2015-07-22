package pl.java.scalatech.tasklet;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Multiset.Entry;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;
@Slf4j
public class HelloTasklet implements Tasklet {
    
    //@Value("#{jobParameters['message']}") 
    private String message;
    
    public RepeatStatus execute(final StepContribution sc, final ChunkContext context) throws Exception {
        
        log.info("First simple task ..... execute !!! ");
     
        ExecutionContext jobExecutionContext = context.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        JobParameters jobParams = context.getStepContext().getStepExecution().getJobExecution().getJobParameters();
        if (jobParams.isEmpty()) {
            log.info("+++ No job parameters!");
        } else {
            log.info("Job parameters:");
            jobParams.getParameters().entrySet().stream().forEach(t -> log.info("param.key : {}, param.value : {} , param.value.type {}",t.getKey(),t.getValue().getValue(),t.getValue().getType()));
        }
        log.info("time : {}",jobParams.getDate("time"));
        log.info("test : {}",jobParams.getString("test"));
        log.info("message : {}",message);
        jobExecutionContext.put("x", "y");
        return FINISHED;
    }
}