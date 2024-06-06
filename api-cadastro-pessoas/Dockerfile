FROM amazoncorretto:21.0.3
COPY target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "Xms128m", "-Xmx512m", "-XX:+PrintFlagsFinal", "-XX:+UseZGC", "-XX:MinRAMPercentage=60", "-XX:MaxRAMPercentage=60", "/app.jar"]