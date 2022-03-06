FROM openjdk:16-alpine3.13
ADD server-app/target/server-app-00.001.00-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
