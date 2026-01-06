# ---------- Build ----------
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

# Download dependencies first for better layer caching
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Build application
COPY src ./src
RUN mvn -q -e clean package


# ---------- Runtime ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/order-api-0.0.1-SNAPSHOT.jar app.jar

# Application port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]