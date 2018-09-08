FROM openjdk:8-jre-alpine

RUN mkdir -p /srv/app

COPY /maven/spring-boot.jar /srv/app/spring-boot.jar

WORKDIR /srv/app
ENTRYPOINT [ "java" ]
CMD [ "-jar", "spring-boot.jar" ]