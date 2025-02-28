# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

ENV VERSION=0.1.0

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY pet-store-data-transform-${VERSION}.jar app.jar

# Expose the application port (adjust if needed)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
