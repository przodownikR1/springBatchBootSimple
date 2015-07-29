package pl.java.scalatech.retry;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import pl.java.scalatech.skip.MySkipException;

@Slf4j
public class RetryProcessor implements ItemProcessor<String, String> {
    @Setter
    private List<Integer> invalidValues;
    @Setter
    private int latency = 0;
    @Setter
    private int thresholdRetry;
    @Getter
    AtomicInteger ai = new AtomicInteger();
    Random r = new Random();

    @Override
    public String process(String item) throws Exception {
        log.info("$$$ process item {}", item);
        int i = r.nextInt(100);
        if (i < thresholdRetry) {
            ai.getAndIncrement();
            log.info("$$$ retry !!!!!!!!!!!!!!  =>  {}", i);
            throw new UnexpectedSituation("unexpected exception ! : " + i);
        }

        if (invalidValues.contains(Integer.parseInt(item))) { throw new MySkipException("invalid value"); }
        if (latency != 0) {
            Thread.sleep(latency);
        }

        return item;
    }

}
