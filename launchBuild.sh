#!/bin/bash

#launchBuild.sh

echo "Launch Build"

kotlinc ../Monde -include-runtime -d monde.jar
if [ $? = 0 ] 
then
    java -jar monde.jar
fi
