# Docker
Docker is open source container software platform that packages applications in "Containers" allowing them to be portable among systems running Linux.

https://hub.docker.com

# Image
* Image defines containers
* Image is immutable
* Image is layered

# Container
A Container is an isolated runtime inside of Linux.
Container has its own:
* Process space
* Network interface
* Disk space

# Docker File
Contains steps to create image.

###### Use base Image
FROM <base-image>
###### Add file from local directory to image
ADD <source-file-name> <destination-file-name>
###### Run command
RUN <command>
###### Set environment variable for container 
ENV <env-var>=<env-var-value>
###### Start main script
ENTRYPOINT
###### Other commands
COPY <source> <destination>
VOLUME 
ARG
CMD
  
# Steps to run docker container
###### Basic run command
docker run <image-name>
###### With container name
docker run --name <container-name> <image-name>
###### With environment variable for container
docker run -e <env-var>=<env-var-value> <image-name>
###### To run container in background
docker run -d <image-name>
###### With port mapping
docker run -d -p <host-port>:<container-port> <image-name>
###### With external volume
docker run -d -v <host-directory>:<container-directory><image-name>
###### Start existing container
docker start <container-name>

# Docker commands
###### Pull image
docker pull <image-name>
###### List containers
docker ps
###### List all containers including stopped containers
docker ps -a
###### Print container logs
docker logs <container-id>
###### Tail container logs
docker logs -f <container-id>
###### Stop container
docker stop <container-id>

docker kill <container-id>
###### Inspect image
docker image inspect <image-name>
###### Inspect container
docker inspect <container-id> 
###### List images
docker images
###### Run command in docker
docker exec -it <container-name> <command>
###### Build image
docker build -t <tag-name> <directory>
###### Remove contaier
docker rm <container-id>
###### Remove image
docker rmi <image-name>

# Cleanup
###### Kill all Running Docker Containers
docker kill $(docker ps -q)
###### Delete all Stopped Docker Containers
docker rm $(docker ps -a -q)
###### Remove a Docker Image
docker rmi <image name>
###### Delete Untagged (dangling) Images
docker rmi $(docker images -q -f dangling=true)
###### Delete All Images
docker rmi $(docker images -q)
###### Remove all dangling volumes
docker volume rm $(docker volume ls -f dangling=true -q)
* NOTE: Does not remove files 
* NOTE: container-id and container-name are interchangeable

# Examples
```
docker run --name my-mongo -d -p 27017:27017 -v /root/dockerdata/mongo:/data/db mongo
docker run -d --hostname my-rabbit --name ks2-rabbit rabbitmq
docker run --name ks2-mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=true -v /root/dockerdata/mysql:/var/lib/mysql -d -p 3036:3036 mysql
docker build -t springdbm .
docker run --name my-app --link my-mongo:mongo -p 8080:8080 -d springdbm
```

# How to create spring boot app docker image
```
Create docker file
Create spring boot jar
Run docker build command to create image on machine where docker is installed
```

# Dockerfile
```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD spring-boot-mongodb-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT <"java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar">
EXPOSE 8080
```