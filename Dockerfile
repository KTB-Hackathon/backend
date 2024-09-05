FROM eclipse-temurin:17-jre
COPY build/libs/KTB-Hackathon-BE-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
