FROM eclipse-temurin:21-jdk-alpine

EXPOSE 8080

WORKDIR /app
COPY taller-*.jar tallerbackend.jar
ENTRYPOINT ["java", "-jar", "tallerbackend.jar","-Dspring.profiles.active=docker"]