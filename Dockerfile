# Use an OpenJDK base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY gradle/ ./gradle/

# Copy the application source code
COPY src/ ./src/

# Ensure gradlew has execute permissions and build the application using Gradle
RUN chmod +x ./gradlew
RUN ./gradlew build

# Set the port to expose
EXPOSE ${PORT}

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "build/libs/unilocal-1.0-SNAPSHOT.jar"]
