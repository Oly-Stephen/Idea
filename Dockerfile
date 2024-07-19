FROM maven:3.8.5-openjdk-19 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19-alpine
COPY --from=build /target/thoughtapp.jar thoughtapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","thoughtapp.jar"]