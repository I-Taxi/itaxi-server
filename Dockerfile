#FROM adoptopenjdk/openjdk8 AS builder
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x ./gradlew
#RUN ./gradlew bootJar
#
#FROM adoptopenjdk/openjdk8
#COPY --from=builder build/libs/*.jar app.jar
#
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} test.jar
ENTRYPOINT ["java","-jar","/test.jar"]
