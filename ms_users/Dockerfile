FROM java:8-jre

ADD ./target/ms_users.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ms_users.jar"]

EXPOSE 8084
