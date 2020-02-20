FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=moviecruiserdb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviecruiserdb
ADD ./target/MovieCruiserServerApp-0.0.1-SNAPSHOT.jar /usr/src/MovieCruiserServerApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","MovieCruiserServerApp-0.0.1-SNAPSHOT.jar"]
