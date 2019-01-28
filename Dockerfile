FROM alpine/git:latest as remoteRepo
ARG url="https://github.com/MarvinVoV/MybatisTutorial.git"

WORKDIR /app
RUN git clone ${url}



FROM maven:3.6.0-jdk-8-alpine as maven
WORKDIR /app
COPY --from=remoteRepo /app/MybatisTutorial /app
RUN mvn install