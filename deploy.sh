#!/bin/bash
REPOSITORY=/home/ubuntu/repo/devoong_server
cd $REPOSITORY

echo "> Git Pull"
git pull

echo "> Gradle Clean..."
./gradlew clean

echo "> Gradle Build..."
./gradlew build -x test

echo "> Docker Build [Profile=prod]"
docker build --build-arg SPRING_PROFILES_ACTIVE=prod,DEPENDENCY=build/dependency -t drogbacuty/devoong_server .

echo "> DockerHub push"
docker push drogbacuty/devoong_server

echo "> DockerHub pull"
docker pull drogbacuty/devoong_server

CONTAINER_PID=docker top devoong | awk '{print $2}' | sed -n 2p
echo "> $CONTAINER_PID"

if [ -z $CONTAINER_PID ]; then
  echo "> 현재 구동중인 서버가 없으므로 종료하지 않습니다."
else
  echo "> kill -9 $CONTAINER_PID"
  kill -9 $CONTAINER_PID
  sleep 5
fi

echo "> Docker Container Run"
docker run -d -p 8080:8080 --name devoong drogbacuty/devoong_server