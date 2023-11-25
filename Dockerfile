FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/app.jar"]