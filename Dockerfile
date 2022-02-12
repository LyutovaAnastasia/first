FROM openjdk:16-alpine3.13
ADD target/ServerApp-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]