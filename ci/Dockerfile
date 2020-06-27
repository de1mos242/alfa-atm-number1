FROM openjdk:14 as BUILD_IMAGE

WORKDIR /app
COPY build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle
RUN ./gradlew build; exit 0

COPY src /app/src