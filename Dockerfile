FROM openjdk:8-jre-alpine

ENV TOKEN xxx
ENV DB_URI http://localhost:27017
ENV PROFILE bot
ENV CHART_URL http://gtbchart:7000

RUN mkdir -p /srv/app

COPY /target/spring-boot.jar /srv/app/spring-boot.jar

WORKDIR /srv/app
ENTRYPOINT [ "java" ]
CMD [ "-jar", "spring-boot.jar","--spring.profiles.active=${PROFILE}", "--telegram.bot.token=${TOKEN}", "--spring.data.mongodb.uri=${DB_URI}", "--gtbchart.url=${CHART_URL}" ]