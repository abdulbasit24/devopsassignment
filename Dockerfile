# FROM openjdk:17-jdk-alpine
# #FROM openjdk:17-alpine


# VOLUME /tmp
# ADD target/DevopsAssignment-0.0.1-SNAPSHOT.jar app.jar
# #COPY target/docker-java-jar-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]


FROM maven:3.6.3-jdk-17 AS build
 
# Copy the project files and build the application.
COPY ./ ./
RUN mvn clean package
 
# Use the official OpenJDK image to create a lightweight image.
FROM openjdk:11-jre-slim
 
# Copy the compiled app from the build stage.
COPY --from=build target/DevopsAssignment-0.0.1-SNAPSHOT.jar /app/
 
# Set the startup command to run the JAR file.
CMD ["java", "-jar", "/app/DevopsAssignment-0.0.1-SNAPSHOT.jar"]
 
