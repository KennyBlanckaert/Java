FROM openjdk:8

WORKDIR /app

RUN apt-get update && apt-get install -y curl

ENTRYPOINT ["java", "-jar", "./target/patient-0.0.1-SNAPSHOT.jar"]
