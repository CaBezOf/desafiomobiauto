FROM openjdk:21

RUN mvn clean install

COPY target/desafiomobiauto-0.0.1-SNAPSHOT.jar desafiomobiauto.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Duser.language=pt", "-Duser.country=BR", "-jar", "desafiomobiauto.jar"]
