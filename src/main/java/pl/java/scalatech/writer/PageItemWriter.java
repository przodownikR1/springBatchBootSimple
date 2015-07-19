package pl.java.scalatech.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import pl.java.scalatech.domain.Person;

public class PageItemWriter extends JdbcBatchItemWriter<Person> {

	public PageItemWriter(DataSource ds) {
		super();
		setDataSource(ds);
		setSql("update person set login=:login where id=:id");
		setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>() );
	}

}