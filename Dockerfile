FROM openjdk:8-jdk-alpine
MAINTAINER FQ.com
VOLUME /tmp
EXPOSE 8080
ADD target/FastQuestion-0.0.1-SNAPSHOT.jar fastQuestion.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/fastQuestion.jar"]