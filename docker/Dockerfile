FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD simple-bank-account-service-latest.jar app.jar
ENTRYPOINT java $JAVA_OPTS -jar /app.jar