package pl.java.scalatech.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.jdbc.core.RowMapper;

import pl.java.scalatech.domain.Person;

public class PageItemReader extends JdbcPagingItemReader<Person> {
	

	public PageItemReader(DataSource ds) {
		super();
		setDataSource(ds);
		setPageSize(10);
		setQueryProvider(queryProvider(ds));
		setRowMapper(rowMapper());
	}
	
	private RowMapper<Person> rowMapper() {
		return (rs, rowNum) ->
        {
        	Person person=new Person();
        	
        	person.setId(rs.getLong("id"));
        	person.setLogin(rs.getString("login"));
        	person.setPasswd(rs.getString("passwd"));
        	person.setAge(rs.getInt("age"));
        	return person;
        };
	}

	private PagingQueryProvider queryProvider(DataSource ds) {
		SqlPagingQueryProviderFactoryBean queryProvider=new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(ds);
		queryProvider.setSelectClause("id, login,passwd,age ");
		queryProvider.setFromClause("person");
		queryProvider.setSortKey("id");
		try {
			return queryProvider.getObject();
		} catch (Exception e) {
		}
		return null;
	}

	
}