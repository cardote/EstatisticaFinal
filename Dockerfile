FROM openjdk:8
ADD target/docker-estatistica.jar docker-estatistica.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "docker-estatistica.jar"]