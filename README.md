# Hugbúnaðarverkefni 1

## Skil 3

Start by running the following command in your shell to create the **moviedb** in Postgres: 
```
createDB -U postgres moviedb
```

enter your postgresql password
This will create the moviedb database

if you want to populate the database with preexisting data, run the following commands in your shell
```
psql -h localhost -d moviedb -U postgres -f users.sql
psql -h localhost -d moviedb -U postgres -f movies.sql
```
the path to these files is **Verkefni/src/test/resources/sql**
make sure you are in the correct directory before running the commands

the admin user created has username admin and password admin

Secondly, configure the **application.properties** file in such a way that the username and password match your own PostgreSQL login
like so:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/moviedb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
```


you can now run the following command from the Verkefni folder to start the project on port 8080: 
```
mvn spring-boot:run
```
the application should now be running on http://localhost:8080


now either attempt to log in with the admin account or create your own





