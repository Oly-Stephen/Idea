FROM maven:3.8.5-openjdk-19 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19-jdk-slim
COPY --from=build /target/thought-0.0.1-SNAPSHOT.jar thought.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","thought.jar"]