FROM java:8-jre
MAINTAINER Kilian Agboton <kilian.agboton@gmail.com>

ADD ./target/config-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-server.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8888/actuator/health || exit 1

EXPOSE 8888