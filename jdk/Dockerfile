FROM openjdk:17-jdk-slim
ADD ./builds/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]