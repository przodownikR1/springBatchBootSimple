package pl.java.scalatech.retryTemplate;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

@Slf4j
public class RetryTasklet implements Tasklet {
    @Setter
    private RetryTemplate retryTemplate;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("+++ retryTaskletStart");
        retryTemplate.execute(new RetryCallback<Object, Exception>() {
            private int counter = 0;
            private String msg = "RetryTemplate test -> simulate generate Exception !!!";

            @Override
            public Object doWithRetry(RetryContext context) throws Exception {
                while (counter < 3) {
                    counter++;
                    log.error(msg);
                    throw new Exception(msg);
                }
                log.info("+++ end");
                return null;
            }
        });
        return RepeatStatus.FINISHED;
    }

}
