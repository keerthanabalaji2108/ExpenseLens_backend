# Use the official OpenJDK 17 image from Docker Hub
FROM openjdk:17
# Set working directory inside the container
WORKDIR /app1
# Copy the compiled Java application JAR file into the container
COPY ./target/ExpenseLens-0.0.1-SNAPSHOT.jar /app1
# Expose the port the Spring Boot application will run on
EXPOSE 9090
# Command to run the application
CMD ["java", "-jar", "ExpenseLens-0.0.1-SNAPSHOT.jar"]