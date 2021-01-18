## REST Backend with Spring Boot -- compile WAR, run in standalone Tomcat server


# Stage I: Build WAR
FROM maven:3.6.3-jdk-8 as builder
WORKDIR /app

# Install dependencies first, so we don't need to reinstall if POM is unchanged
# See https://dzone.com/articles/spring-boot-run-and-build-in-docker
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Copy and build code
COPY src/ src/
RUN mvn package


# Stage II: Deploy WAR on Tomcat
FROM tomcat:9.0.41-jdk8

# Setup script for waiting on MySQL (a little hacky?)
RUN apt-get update 
RUN apt-get install -y default-mysql-client
COPY wait-for-mysql.sh .
RUN chmod +x ./wait-for-mysql.sh

# Setup the WAR so Tomcat sees it
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/rest-backend.war

# Setup Tomcat HTTPS
COPY server.xml /usr/local/tomcat/conf/server.xml
COPY src/main/resources/keystore /usr/local/tomcat/conf/keystore

CMD ./wait-for-mysql.sh \
    && catalina.sh run
