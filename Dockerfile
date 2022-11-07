FROM openjdk: 8
EXPOSE 8080
ADD target/springboot-docker-images.jar springboot-docker-images.jar
ENTRYPOINT["java","-jar","/springboot-docker-images.jar"]