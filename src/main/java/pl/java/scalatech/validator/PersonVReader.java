package pl.java.scalatech.validator;

import java.util.List;

import javax.annotation.PostConstruct;

import org.assertj.core.util.Lists;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import pl.java.scalatech.domain.Person;

public class PersonVReader implements ItemReader<Person> {

    List<Person> persons;

    @PostConstruct
    public void init() {
        persons = Lists.newArrayList(new Person("przodownik", "p1", 36), new Person("bak", "p3", 3), new Person("agieszka", "p2", 35));
    }

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Person person = !persons.isEmpty() ? persons.remove(0) : null;
        return person;
    }

}
