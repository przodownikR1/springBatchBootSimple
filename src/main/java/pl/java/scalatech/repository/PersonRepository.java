package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
