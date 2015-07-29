package pl.java.scalatech.skip;

import java.util.List;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class SkipProcessor implements ItemProcessor<String, String> {
    @Setter
    private List<Integer> invalidValues;
    @Setter
    private int latency = 0;

    @Override
    public String process(String item) throws Exception {
        log.info("$$$ process item {}", item);

        if (invalidValues.contains(Integer.parseInt(item))) { throw new MySkipException("invalid value"); }
        if (latency != 0) {
            Thread.sleep(latency);
        }

        return item;
    }
}