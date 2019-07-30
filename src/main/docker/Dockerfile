FROM openjdk:8-jre
WORKDIR /app
VOLUME ["/app"]
COPY maven/start.sh start.sh
COPY maven/wait-for-it.sh wait-for-it.sh
COPY maven/app.jar app.jar
RUN sh -c 'touch app.jar'
ENTRYPOINT ["./start.sh"]
