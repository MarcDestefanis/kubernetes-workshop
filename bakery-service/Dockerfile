FROM openjdk:12-jdk-oraclelinux7
COPY ./target/bakery-service.jar /usr/src/kubernetes-workshop/
WORKDIR /usr/src/kubernetes-workshop/
EXPOSE 80
CMD ["java", "-jar", "bakery-service.jar"]