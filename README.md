# Apollo


<a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu" /></a>

## With Docker

Build
```bash
docker build -t marvinvov/apollo:latest
```

Build And Start
```bash
docker-compose up --build -d
``` 

Push
```bash
docker-compose push
```

Run on remote
```bash
docker-compose pull
docker-compose up -d --no-build
```

## Local Dev

Package 
```bash
mvn clean package -DskipTests=true
```

Start
```bash
java -jar web-boot-apollo-exec.jar --spring.profiles.active=dev --db_pwd=root --db_user=root
```