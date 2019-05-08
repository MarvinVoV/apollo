FROM maven:3.6.0-jdk-8-alpine as build
WORKDIR /app
COPY . .
RUN mvn install -DskipTests=true

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/classes/web-boot-apollo-exec.jar /app
CMD ["java", "-jar", "web-boot-apollo-exec.jar --spring.profiles.active=prod"]
