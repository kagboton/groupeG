FROM java:8-jre
MAINTAINER Kilian Agboton <kilian.agboton@gmail.com>

ADD ./target/eureka-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/eureka-server.jar"]

EXPOSE 9102