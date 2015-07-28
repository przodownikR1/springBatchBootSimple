package pl.java.scalatech.skip;

import java.util.List;

import javax.annotation.PostConstruct;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.Lists;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

@Slf4j
public class SkipReader implements ItemReader<String> {
    @Setter
    private int invalidValue;
    List<String> values = Lists.newArrayList();

    @SuppressWarnings("boxing")
    @PostConstruct
    public void init() {
        ContiguousSet.create(Range.open(1, 30), DiscreteDomain.integers()).forEach(t -> values.add("" + t));
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        String str = !values.isEmpty() ? values.remove(0) : null;
        log.info("+++ reader {}", str);
        if (str != null && Integer.parseInt(str) == invalidValue) { throw new MySkipException("invalid value " + invalidValue); }
        return str;

    }

}
