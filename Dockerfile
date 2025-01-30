FROM openjdk:21-jdk-slim
WORKDIR /app
 target/BookStore_API-0.0.1-SNAPSHOT.jar /app/BookStore_API.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "BookStore_API.jar"]
