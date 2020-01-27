FROM java:8-jre
MAINTAINER SALOGBA Kook-Yong


ADD ./target/ms_authentification.jar /app/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/ms_authentification.jar"]
EXPOSE 5000