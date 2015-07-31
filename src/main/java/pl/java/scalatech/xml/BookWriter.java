package pl.java.scalatech.xml;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemWriter;

@Slf4j
public class BookWriter implements ItemWriter<Book> {

    @Override
    public void write(List<? extends Book> items) throws Exception {
        items.stream().forEach(b -> log.info(" {}", b));

    }

}
