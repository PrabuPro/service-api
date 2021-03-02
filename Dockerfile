FROM openjdk:11
EXPOSE 8080
ADD target/service-app.jar service-app.jar
ENTRYPOINT ["java","-jar","/service-app.jar"]
