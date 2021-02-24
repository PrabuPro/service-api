FROM centos

RUN yum install -y java

VOLUME /temp
ADD target/service-api-0.0.1-SNAPSHOT.jar service-api.jar
RUN sh -c "touch /service-api.jar"
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/service-api.jar"]
