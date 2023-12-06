FROM openjdk:17-alpine
LABEL authors="denizOgut"
WORKDIR /app
COPY target/booking-service-1.0.0.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java", "jar","app.jar"]