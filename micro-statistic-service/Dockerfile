FROM openjdk:17-alpine
LABEL authors="FLAMINGO-PC"
#copy jar file from target
COPY target/statistic-service-0.0.1-SNAPSHOT.jar statistic-service-0.0.1-SNAPSHOT.jar
#command line to run jar
ENTRYPOINT ["java","-jar","./statistic-service-0.0.1-SNAPSHOT.jar"]