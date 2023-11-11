FROM openjdk:17-alpine
LABEL authors="denizOgut"
WORKDIR /app
COPY target/location-service-1.0.0.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java", "jar","app.jar"]