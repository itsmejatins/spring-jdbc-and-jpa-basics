----CREATE TABLE Person (
----	id integer NOT NULL,
----	name varchar(255) NOT NULL,
----	location varchar(255) NOT null,
----	birthDate timestamp,
----	PRIMARY KEY(id)
----);



INSERT INTO Person (id, name, location, birthDate) 
VALUES
	(100, 'name1', 'location1', now()),
	(101, 'name2', 'location2', now()),
	(102, 'name3', 'location3', now()),
	(104, 'name4', 'location4', now());