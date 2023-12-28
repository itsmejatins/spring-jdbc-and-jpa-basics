package springjdbcjpa.springjdbcjpabasics;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springjdbcjpa.springjdbcjpabasics.entity.Person;
import springjdbcjpa.springjdbcjpabasics.jdbc.PersonJdbcDao;

//@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

	@Autowired
	PersonJdbcDao dao;
	
	private Logger logger = LoggerFactory.getLogger(SpringJdbcDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("fetching complete table -> {}", dao.findAll());
		logger.info("person with id = 100 is -> {}", dao.findById(100));
		logger.info("deleting person with id = 100 -> {}", dao.deleteById(100));
		logger.info("again fetching complete table -> {}", dao.findAll());
		
		Person p = new Person(101, "updatedName", "updatedLocation", new Date());
		logger.info("updating person with id = 101 -> {}", dao.update(p));
		logger.info("fetching table after updating person with id = 101 -> {}", dao.findAll());
		
		p = new Person(105, "name5", "location5", new Date());
		logger.info("inserting a new person with id = 105 -> {}", dao.insert(p));
		logger.info("printing the person just inserted -> {}", dao.findById(105));
		
		
		// when required data does not exists in the table
		logger.info("person with id = 12345 is -> {}", dao.findById(12345));
		logger.info("deleting person with id = 12345 -> {}", dao.deleteById(12345));
		p = new Person(12345, "updatedName", "updatedLocation", new Date());
		logger.info("updating person with id = 12345 -> {}", dao.update(p));
		
	}

}
