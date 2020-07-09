FROM maven:3.6.0-jdk-11-slim AS build
COPY src src
COPY pom.xml pom.xml
RUN mvn -f pom.xml clean package

FROM openjdk:8-jdk-alpine
MAINTAINER FQ.com
VOLUME /tmp
EXPOSE 8080
ADD target/FastQuestion-0.0.1-SNAPSHOT.jar fastQuestion.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/fastQuestion.jar"]