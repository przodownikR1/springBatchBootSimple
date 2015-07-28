package pl.java.scalatech.skip;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SkipProcessor implements ItemProcessor<String, String> {
    @Setter
    private List<Integer> invalidValues;
    
    @Override
    public String process(String item) throws Exception {
        log.info("$$$ process item {}",item);
        
        if (invalidValues.contains(Integer.parseInt(item))) { throw new MySkipException("invalid value"); }

        return item;
    }
}