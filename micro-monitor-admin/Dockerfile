FROM openjdk:17-alpine
LABEL authors="FLAMINGO-PC"
#copy jar file from target
COPY target/monitor-admin-0.0.1-SNAPSHOT.jar monitor-admin-0.0.1-SNAPSHOT.jar
#command line to run jar
ENTRYPOINT ["java","-jar","./monitor-admin-0.0.1-SNAPSHOT.jar"]