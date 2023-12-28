package springjdbcjpa.springjdbcjpabasics.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import springjdbcjpa.springjdbcjpabasics.entity.Person;

@Repository
@Transactional
public class PersonRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@PersistenceContext
	private EntityManager em;

	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = em.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}

	public Person findById(int id) {
		Person person = em.find(Person.class, id);
		return person;
	}

	public void insert(Person p) {
		em.merge(p); // don't use em.persist() -> InvalidDataAccessApiUsageException: detached entity
						// passed to persist
	}

	public void update(Person p) {
		Person orig = findById(p.getId());
		if (p.getName() == null)
			p.setName(orig.getName());
		if (p.getLocation() == null)
			p.setLocation(orig.getLocation());
		if (p.getBirthDate() == null)
			p.setBirthDate(orig.getBirthDate());
		em.merge(p);
	}

	public void delete(Person p) {
		p = findById(p.getId()); // as p is a detached entity, you need to fetch it again so that entity manager
									// can manage it.
		if (p != null)
			em.remove(p);
		else
			logger.warn("Attempted to delete a non-existent person");
	}

	public void deleteById(int id) {
		Person p = findById(id);
		if (p != null)
			em.remove(p);
		else
			logger.warn("Attempted to delete a non-existent person");
	}

}
