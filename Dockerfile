FROM openjdk:19-jdk

COPY target/thoughtapp.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "crudapp.jar"]