FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/spring-boot-minio.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE 8081



#FROM maven:3.6.3 AS maven
#WORKDIR /usr/src/app
#COPY . /usr/src/app
#RUN mvn clean package -DskipTests

#FROM adoptopenjdk/openjdk11:alpine-jre
#ARG JAR_FILE=spring-boot-minio.jar
#COPY --from=maven /usr/src/app/target/${JAR_FILE} application.jar
#ENTRYPOINT ["java", "-jar", "application.jar"]
#EXPOSE 8081