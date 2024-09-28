# Step 1: Build the application
FROM gradle:7.2.0-jdk17 AS build
WORKDIR /app
COPY . .
# Grant execution permissions to the gradlew script
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# Step 2: Run the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar diu-student-result.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "diu-student-result.jar"]
