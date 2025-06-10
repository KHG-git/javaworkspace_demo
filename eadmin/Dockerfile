FROM eclipse-temurin:21-jre-alpine
ENV TZ=Asia/Seoul
EXPOSE 8080
COPY build/libs/eadmin-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT java -jar /app.jar