# Bakery Service

## Build

docker build -t marcdestefanis/bakery-service:v1 .

## Run

docker run -p 80:80 marcdestefanis/bakery-service:v1

## Push to Docker Hub

docker login --username=marcdestefanis

docker push marcdestefanis/bakery-service:v1

## Pull from Docker Hub

docker pull marcdestefanis/bakery-service:v1