package pl.java.scalatech.writer;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.Person;

@Slf4j
public class JpaItemWriter extends org.springframework.batch.item.database.JpaItemWriter<Person> {

    @Override
    public void write(List<? extends Person> items) {
        for (Person person : items) {
            log.info("jpa write : {}", person);
        }
        super.write(items);
    }
}