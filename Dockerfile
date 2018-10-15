FROM openjdk:8-jre-alpine

RUN mkdir -p /usr/src/nlp
COPY ./target/nlp-docker-jar-with-dependencies.jar /usr/src/nlp
WORKDIR /usr/src/nlp/

RUN apk add --update bash && rm -rf /var/cache/apk/*