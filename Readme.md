# Using JDBC and JPA with spring - basics

This repository demonstrates basics of interacting with database using Spring JDBC and JPA. The database that we will be using is H2 database. 

## Using Spring JDBC

- There is a single table or entity called `Person`which can be found in **`package** springjdbcjpa.springjdbcjpabasics.entity`. This class represents a basic row in `Person` table.
- The data access object for person table using JDBC method is present in **`package** springjdbcjpa.springjdbcjpabasics.jdbc`. This contains method for basic CRUD operations on the `Person` table.
- Run `SpringJdbcDemoApplication` class present in **`package** springjdbcjpa.springjdbcjpabasics`. Inside it you can find that various CRUD operations are being done in the override `run` method from `CommandLineRunner` interface.