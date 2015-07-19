package pl.java.scalatech.reader;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaPagingItemReader;

import pl.java.scalatech.domain.Person;

public class JpaItemReader extends JpaPagingItemReader<Person> {
	
	 public JpaItemReader(EntityManagerFactory emf, String queryString){
		 setEntityManagerFactory(emf);
		 setQueryString(queryString);	 
	 }


}