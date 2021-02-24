FROM openjdk:11
EXPOSE 8080
ADD target/service-api-0.0.1-SNAPSHOT.jar service-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/service-api-0.0.1-SNAPSHOT.jar"]