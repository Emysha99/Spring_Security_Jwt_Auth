# Step 1: Use an official Java image as base
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy the JAR file into the container
COPY target/authspring.jar app.jar

# Step 4: Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
