FROM openjdk:17-alpine
LABEL authors="denizOgut"
WORKDIR /app
COPY target/weather-service-1.0.0.jar app.jar
EXPOSE 8086
CMD ["java", "-jar", "app.jar"]