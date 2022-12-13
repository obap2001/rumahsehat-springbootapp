FROM openjdk:17-alpine
ARG JAR_FILE=rumahsehat/build/libs/TA_C_ANS_78-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9099
ENTRYPOINT ["java","-jar","/app.jar"]
