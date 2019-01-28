FROM alpine/git:latest as repo
ARG url
WORKDIR /app
RUN git clone ${url}

FROM redis:5-alpine

FROM maven:3.6.0-jdk-8-alpine as build
ARG project
WORKDIR /app
COPY --from=repo /app/${project} /app
RUN mvn install -DskipTests=true

FROM openjdk:8-jre-alipine
ARG artifactid
ARG version
ENV artifact ${artifactid}-${version}.jar
WORKDIR /app
COPY --from=build /app/target/classes/${artifact} /app
CMD ["java -jar ${artifact}"]
