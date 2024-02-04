# Use the official OpenJDK base image
FROM openjdk:latest

# Set the working directory inside the container
WORKDIR /app

# Copy the application's files into the container
COPY . /app

# Run the application using Maven
CMD ["./mvnw", "spring-boot:run"]