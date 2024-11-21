FROM openjdk:21-jdk-slim

COPY build/libs/admin-*.jar admin-service.jar

ENTRYPOINT ["java", "-jar", "admin-service.jar"]
