FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ENV	USE_PROFILE local
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar","/app.jar"]
#ENTRYPOINT ["java","-jar","/app.jar"]