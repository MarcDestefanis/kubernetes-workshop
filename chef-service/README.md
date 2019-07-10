# Chef Service

## Build

docker build -t marcdestefanis/chef-service .

## Run

docker run -p 80:80 marcdestefanis/chef-service:latest

## Push to Docker Hub

docker login --username=marcdestefanis

docker push marcdestefanis/chef-service:latest

## Pull from Docker Hub

docker pull marcdestefanis/chef-service:latest