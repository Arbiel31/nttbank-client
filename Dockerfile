FROM openjdk:11
VOLUME /tmp
EXPOSE 8085
ADD ./target/nttbank-client-0.0.1-SNAPSHOT.jar nttbank-client.jar
ENTRYPOINT ["java","-jar","nttbank-client.jar"]