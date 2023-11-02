FROM openjdk:17-alpine
LABEL authors="denizOgut"
WORKDIR /app
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9191
ENTRYPOINT ["java", "jar","app.jar"]