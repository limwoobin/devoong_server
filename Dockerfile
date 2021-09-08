FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ARG SPRING_PROFILES_ACTIVE
RUN echo $SPRING_PROFILES_ACTIVE
ENV	SPRING_PROFILES_ACTIVE $SPRING_PROFILES_ACTIVE
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar","/app.jar"]
