FROM openjdk:17-alpine
LABEL authors="FLAMINGO-PC"
#copy jar file from target
COPY target/micro-config-service-0.0.1-SNAPSHOT.jar micro-config-service-0.0.1-SNAPSHOT.jar
#command line to run jar
ENTRYPOINT ["java","-jar","./micro-config-service-0.0.1-SNAPSHOT.jar"]