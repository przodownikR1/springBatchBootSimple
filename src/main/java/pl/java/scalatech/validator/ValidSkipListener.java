package pl.java.scalatech.validator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.SkipListener;

import pl.java.scalatech.domain.Person;

@Slf4j
public class ValidSkipListener implements SkipListener<Person, Person> {

    @Override
    public void onSkipInRead(Throwable t) {
        log.info("SSS  onSkipInRead {}", t.getMessage());

    }

    @Override
    public void onSkipInWrite(Person item, Throwable t) {
        log.info("SSS  onSkipInWrite {} {}", item, t.getMessage());

    }

    @Override
    public void onSkipInProcess(Person item, Throwable t) {
        log.info("SSS  onSkipInProcess {} {}", item, t.getMessage());

    }

}
