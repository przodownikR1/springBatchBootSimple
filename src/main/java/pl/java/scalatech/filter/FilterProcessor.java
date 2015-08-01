package pl.java.scalatech.filter;

import java.util.List;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class FilterProcessor implements ItemProcessor<String, String> {
    @Setter
    private List<Integer> invalidValues;

    @Override
    public String process(String item) throws Exception {
        log.info("$$$ process item {}", item);

        if (invalidValues.contains(Integer.parseInt(item))) { return null; }

        return item;
    }
}