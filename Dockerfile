FROM maven:3.9.6-eclipse-temurin-21-jammy AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN apt-get update && apt-get install -y maven && \
    mvn clean package && \
    mv target/*.jar app.jar

FROM openjdk:11-jre-slim

COPY --from=build /app/app.jar /app/app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar"]
