package pl.java.scalatech.validator;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemWriter;

import pl.java.scalatech.domain.Person;

@Slf4j
public class PersonVWriter implements ItemWriter<Person> {

    @Override
    public void write(List<? extends Person> items) throws Exception {
        items.stream().forEach(p -> log.info("{}", p));

    }

}
