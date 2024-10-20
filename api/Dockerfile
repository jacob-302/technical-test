FROM amazoncorretto:21-alpine-jdk

ARG git_sha
ENV COMMIT_SHA=${git_sha}

WORKDIR /opt

COPY build/libs/application-*.jar /opt/app.jar

EXPOSE 8080

CMD java $JAVA_OPTS -jar app.jar