package springjdbcjpa.springjdbcjpabasics.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "Person")
@NamedQuery(name = "find_all_persons", query = "SELECT p FROM Person p")
public class Person {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name")
	private String name;
	private String location;
	@Column(name = "birthdate")
	private Date birthDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	public Person( String name, String location, Date birthDate) {
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public Person() {
		// for hibernate
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}
}
