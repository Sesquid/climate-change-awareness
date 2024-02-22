FROM openjdk:17
WORKDIR /app
COPY ./climate.jar /app/
EXPOSE 8080
CMD ["java", "-jar", "climate.jar"]
