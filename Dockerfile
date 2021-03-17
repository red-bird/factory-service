FROM openjdk:15.0.2

RUN mkdir -p /usr/src/app/
WORKDIR /usr/src/app/

COPY build/libs/factory-service-0.0.1-SNAPSHOT.jar /usr/src/app/

CMD ["java", "-jar", "factory-service-0.0.1-SNAPSHOT.jar"]