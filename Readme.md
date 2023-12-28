# Using JDBC and JPA with spring - basics

This repository demonstrates basics of interacting with database using Spring JDBC and Spring JPA. The database that we will be using is H2 which is an in-memory database.

## Using Spring JDBC

- There is a single table called `Person`. A row of this table will be parse to an object of `Person`. This class is in the file `Person.java`.
- The data access object for person table using JDBC method is present in `package springjdbcjpa.springjdbcjpabasics.jdbc`. This contains method for basic CRUD operations on the `Person` table.

## Using Spring JPA
 - The same `Person` class will be used as the entity for `Person` table in the database.
 - The repository for this is `PersonRepository.java`. It makes use of `EntityManager` to perform CRUD operations on the database.
 
## How to run

### Using JDBC

- Comment the line containing `@SpringBootApplication` in `SpringJpaDemoApplication.java`.
- Comment the line containing `@Repository` and `@Transactional` in `PersonRepository.java`.
- Comment the line containing `@Entity` in `Person.java`.
- Above three steps are for ensuring that Spring does not runs classes meant for demonstrating the use of JPA.
- Make sure that command to create table in `data.sql` is not commented.
- Now run `SpringJdbcDemoApplication.java.`

### Using JPA

- Uncomment everything that you did for running `SpringJdbcDemoApplication.java`.
- Comment the line containing `@SpringBootApplication` in `SpringJdbcDemoApplication.java`.
- Comment the line containing `@Repository` in `PersonJdbcDao.java`.
- Above two steps are for ensuring that Spring does not run classes meant for demonstrating the use of JDBC.
- You also need to ensure that command to create table in `data.sql` is commented, because since `Person` class acts as entity, hibernate will automatically create a schema corresponding to it.
- Now run `SpringJpaDemoApplication.java.`
