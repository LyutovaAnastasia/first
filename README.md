# Local Api for project
This API was developed for the educational project "Sai for collecting feedback from different IT schools".
The API allows you to transfer information about online schools, IT courses and reviews of these courses.
The API contains methods for the admin part, access to which is available after authentication and authorization.
The API contains a method for adding feedback, which is available after authentication and authorization.
## Tools

The following tools have been used:
+ **Programming languages**: Java, SQL
+ **Technologies and Frameworks**: Spring Core, Spring Data JPA, Spring Security, Hibernate, Spring Boot, Docker, Docker Compose
+ **Application / Web Servers**: Tomcat
+ **Databases**: Postgresql
+ **IDE**: Intellij Idea
+ **Version control system**: git, liquibase for bd

For tracing was used *Spring Cloud Sleuth, Zalando*

For logging was used *Log4j2 lombok, logback*
## Start

### With docker
```Bash
mvn clean package
```

```Bash
docker-compose up
```

The project is running and available at localhost:8080

### Swagger
[swagger](http://editor.swagger.io/?raw=https://raw.githubusercontent.com/LyutovaAnastasia/reviews-server-app/master/swagger.yaml)


Swagger is available at *http://localhost:8080/*
