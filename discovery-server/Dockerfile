FROM openjdk:11-jdk-alpine
LABEL author="ivana"
#COPY target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar
#ENTRYPOINT ["java","-jar","/discovery-server.jar"]
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]