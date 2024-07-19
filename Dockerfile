FROM openjdk:19-jdk

COPY target/thought-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "thought.jar"]
