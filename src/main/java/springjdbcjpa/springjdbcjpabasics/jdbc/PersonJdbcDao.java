package springjdbcjpa.springjdbcjpabasics.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import springjdbcjpa.springjdbcjpabasics.entity.Person;

//@Repository
public class PersonJdbcDao {

	static class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person p = new Person();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setLocation(rs.getString("location"));
			p.setBirthDate(rs.getTimestamp("birthDate"));
			return p;
		}

	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @return list of all person in the table.
	 */
	public List<Person> findAll() {
		List<Person> result = jdbcTemplate.query("SELECT * FROM Person",
				new BeanPropertyRowMapper<Person>(Person.class));
		return result;
	}

	/**
	 * 
	 * @param id: the id of the person to query for
	 * @return object of the person with that id if it exists
	 */
	public Person findById(int id) {
		Person result = null;
		try {
			result = jdbcTemplate.queryForObject("SELECT * FROM person WHERE id = ?", new Object[] { id },
					new PersonRowMapper());

		} catch (EmptyResultDataAccessException e) {

		}

		return result;
	}

	/**
	 * 
	 * @param id - the id of the person you want to delete
	 * @return true if a row was deleted, else false
	 */
	public boolean deleteById(int id) {
		int rowsEffected;
		try {
			rowsEffected = jdbcTemplate.update("DELETE FROM person WHERE id = " + id);
		} catch (EmptyResultDataAccessException e) {
			rowsEffected = 0;
		}
		return rowsEffected == 1 ? true : false;
	}

	/**
	 * 
	 * @param p: the object of person to be inserted
	 * @return true if insertion was successful
	 */

	public boolean insert(Person p) {
		int rowsEffected = jdbcTemplate.update("INSERT INTO person (id, name, location, birthDate) VALUES (?, ?, ?, ?)",
				new Object[] { p.getId(), p.getName(), p.getLocation(), new Timestamp(p.getBirthDate().getTime()) });
		return rowsEffected == 1 ? true : false;

	}

	/**
	 * 
	 * @param p the object of the person to be updated
	 * @return true if row with id of 'p' was updated with new values of 'p'
	 */
	public boolean update(Person p) {

		int rowsEffected;
		try {
			rowsEffected = jdbcTemplate.update("UPDATE person SET name = ?, location = ?, birthDate = ? WHERE id = ?",
					new Object[] { p.getName(), p.getLocation(), p.getBirthDate(), p.getId() });
		} catch (EmptyResultDataAccessException e) {
			rowsEffected = 0;
		}
		return rowsEffected == 1 ? true : false;
	}

}
