FROM java:8-jre
MAINTAINER SALOGBA Kook-Yong

ADD ./target/gateway.jar /app/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/gateway.jar"]
EXPOSE 8080