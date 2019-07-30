#! /bin/sh
mvn clean install
cd src/front
docker-compose up
