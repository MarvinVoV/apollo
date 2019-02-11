FROM alpine/git as repo
ARG url
WORKDIR /app
RUN git clone https://github.com/MarvinVoV/MybatisTutorial.git
#
FROM maven:3.6.0-jdk-8-alpine as build
ARG project
WORKDIR /app
COPY --from=repo /app/${PROJECT_NAME} /app
RUN mvn install -DskipTests=true
#
FROM openjdk:8-jre-alipine
ARG artifactId
ARG version
ENV artifact ${artifactId}-${version}.jar
WORKDIR /app
COPY --from=build /app/target/classes/${artifact} /app
CMD ["java -jar ${artifact}"]
