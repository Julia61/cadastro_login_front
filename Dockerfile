FROM maven:3.8.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package


FROM eclipse-temurin:17-jdk-slim

WORKDIR /app


COPY --from=build /app/target/front_cadastro_login-0.0.1.jar app.jar


EXPOSE 8082


ENTRYPOINT ["java", "-jar", "app.jar"]

