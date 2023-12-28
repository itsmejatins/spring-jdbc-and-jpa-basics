package springjdbcjpa.springjdbcjpabasics;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springjdbcjpa.springjdbcjpabasics.entity.Person;
import springjdbcjpa.springjdbcjpabasics.jpa.PersonRepository;

@SpringBootApplication
public class SpringJpaDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired

	PersonRepository dao;

	@Override
	public void run(String... args) {
		logger.info("fetching all persons -> {}", dao.findAll());
		logger.info("finding person with id = 100 -> {}", dao.findById(100));
		logger.info("finding person with id = 1100 -> {}", dao.findById(1100));

		logger.info("inserting 3 new person with name = name5, name6, name7");
		Date date = new Date();

		Person p1 = new Person("name5", "location 5", date);
		dao.insert(p1);
		Person p2 = new Person("name6", "location 6", date);
		dao.insert(p2);
		Person p3 = new Person(33, "name7", "location 7", date); // even though id is manually assigned, hibernate will assigned id as per the sequence
		dao.insert(p3);

		logger.info("fetching all persons -> {}", dao.findAll());
		logger.info("updating person with name=name5 's location. It was auto-assigned id = 1");
		dao.update(new Person(1, null, "updated_location_5", null));
		logger.info("fetching this updated person (id = 1) -> {}", dao.findById(1));

		logger.info("deleting person with id = 2");
		dao.deleteById(2);
		logger.info("deleting the person with id = 3 by passing the p3Duplicate");
		Person p3Duplicate = new Person(3, "name7", "location7", date);
		dao.delete(p3Duplicate);
		logger.info("deleting person represented by object p1");
		dao.delete(p1);

		logger.info("fetching all persons -> {}", dao.findAll());
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaDemoApplication.class, args);
	}
}
