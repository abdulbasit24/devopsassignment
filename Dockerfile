# FROM openjdk:17-jdk-alpine
# #FROM openjdk:17-alpine


# VOLUME /tmp
# ADD target/DevopsAssignment-0.0.1-SNAPSHOT.jar app.jar
# #COPY target/docker-java-jar-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]


FROM maven:3.6.3-openjdk-17 AS build
COPY ./ ./
RUN mvn clean package
FROM openjdk:11-jre-slim
COPY --from=build target/DevopsAssignment-0.0.1-SNAPSHOT.jar /app/
# startup command to run JAR file.
CMD ["java", "-jar", "/app/DevopsAssignment-0.0.1-SNAPSHOT.jar"]
