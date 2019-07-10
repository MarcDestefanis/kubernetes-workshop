# Bakery Service

## Build

docker build -t marcdestefanis/bakery-service .

## Run

docker run -p 80:80 marcdestefanis/bakery-service:latest

## Push to Docker Hub

docker login --username=marcdestefanis

docker push marcdestefanis/bakery-service:latest

## Pull from Docker Hub

docker pull marcdestefanis/bakery-service:latest