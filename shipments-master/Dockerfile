FROM openjdk:11.0.7-jdk
MAINTAINER luisvillab@gmail.com
ADD target/shipments-master.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]