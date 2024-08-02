FROM openjdk:21
LABEL authors="Oleksii_Drabchak"

VOLUME /tmp
EXPOSE 8080
COPY target/couchsurfing-backend-0.0.1-SNAPSHOT.jar couchsurfing-backend.jar

ENTRYPOINT ["java","-jar","couchsurfing-backend.jar"]

