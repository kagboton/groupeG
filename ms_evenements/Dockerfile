FROM java:8-jre

ADD ./target/ms_evenements.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ms_evenements.jar"]

EXPOSE 8085
