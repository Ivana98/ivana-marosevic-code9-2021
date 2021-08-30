FROM openjdk:11 AS BUILD_IMAGE
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw clean package -DskipTests

FROM openjdk:11-jre
WORKDIR /app
COPY --from=BUILD_IMAGE /app/target/discovery-server-0.0.1-SNAPSHOT.jar ./myapp.jar
EXPOSE 8761
CMD ["java","-jar","myapp.jar"]
