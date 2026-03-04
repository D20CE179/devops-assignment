# Stage 1: Build the application
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY app/sample-spring-boot-app/pom.xml .
COPY app/sample-spring-boot-app/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application (Using JRE Jammy for an optimized Ubuntu-based image)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
