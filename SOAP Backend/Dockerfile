## SOAP Backend with Spring Boot -- JAX-WS webservices (which spawn their own WSDL server and all)

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


# Stage II: Run JAR on dedicated image
FROM openjdk:8-slim

# Setup script for waiting on MySQL
RUN apt-get update
RUN apt-get install -y default-mysql-client
COPY wait-for-mysql.sh .
RUN chmod +x ./wait-for-mysql.sh

# Setup webapp
COPY --from=builder /app/target/*.jar home/soap-backend.jar

CMD ./wait-for-mysql.sh \
    && java -jar home/soap-backend.jar
