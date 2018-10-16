# nlp

# Où sont mes affaires
[![Build Status](https://travis-ci.org/bbougon/nlp.svg)](https://travis-ci.org/bbougon/nlp)
[![codecov.io](https://codecov.io/gh/bbougon/nlp/coverage.svg?branch=master)](https://codecov.io/gh/bbougon/nlp/codecov.io?branch=master)

## CI
https://travis-ci.org/bbougon/nlp

## Docker

### Building container
`docker build -t nlp-api .`

### Running NLP container and join to bridge
***Run container***
```bash
docker run -d --rm --name nlp-server --env-file ./.env.list --mount source=nlp-storage,target=/image-storage --publish 8090:8183 -w /usr/src/nlp/ --ip 192.168.100.20 --network=bridge-ou-sont-mes-affaires nlp-api java -jar nlp-docker-jar-with-dependencies.jar
docker run -d --name nlp-server --env-file ./.env.list --mount source=nlp-storage,target=/image-storage --publish 8090:8183 -w /usr/src/nlp/ --ip 192.168.100.20 --network=bridge-ou-sont-mes-affaires nlp-api java -jar nlp-docker-jar-with-dependencies.jar
```

***Run in debug mode***
```bash
docker run -d --rm --name nlp-server --env-file ./.env.list --mount source=nlp-storage,target=/image-storage --publish 8090:8183 --publish 8797:8787 -w /usr/src/nlp/ --ip 192.168.100.20 --network=bridge-ou-sont-mes-affaires nlp-api java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n -jar nlp-docker-jar-with-dependencies.jar
```

### Running Ou Sont Mes Affaires angular container

```
docker run -d --rm --name ou-sont-mes-affaires-angular --publish 4200:80 --ip 192.168.100.15 --network=bridge-ou-sont-mes-affaires ou-sont-mes-affaires-web
```