package pl.java.scalatech.skip;

import java.util.List;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemWriter;

@Slf4j
public class SkipWriter implements ItemWriter<String> {

    @Setter
    private int invalidValue;

    @Override
    public void write(List<? extends String> items) throws Exception {
        log.info("www   : {}",items);
        @SuppressWarnings("boxing")
        boolean invalid = items.stream().map(s -> Integer.parseInt(s)).anyMatch(i -> i == invalidValue);
        if (invalid) { throw new MySkipException("writer : invalid value :  " + invalidValue); }

    }

}
