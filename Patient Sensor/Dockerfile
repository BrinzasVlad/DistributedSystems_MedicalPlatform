## Spring Boot activity sensor with no Tomcat at all (no need; it's a console application basically)

# Stage I: Build JAR
FROM maven:3.6.3-jdk-8 as builder
WORKDIR /app

# Install dependencies first, so we don't need to reinstall if POM is unchanged
# See https://dzone.com/articles/spring-boot-run-and-build-in-docker
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Copy and build code
COPY src/ src/
RUN mvn package

# Stage II: Run
# We use a different image to minimise image size (and since normally we'd just deploy the JAR directly, not build it on-site)
FROM openjdk:8-slim
COPY --from=builder app/target/*.jar home/activity-sensor.jar
CMD ["java", "-jar", "home/activity-sensor.jar"]