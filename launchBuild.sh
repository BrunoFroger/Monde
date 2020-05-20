#!/bin/bash

#launchBuild.sh

if [ "$1" == "clear" ] 
then
    echo "efface les fichier log et jar"
    rm monde.log
    rm monde.jar
fi
if [ "$1" == "build" ] 
then
    echo "Launch Build" 
    kotlinc ../Monde -include-runtime -d monde.jar
fi
if [ "$1" == "exec" ] 
then
    echo "Launch exec" 
    java -jar monde.jar
fi
if [ "$1" == "" ] 
then
    echo "Launch Build" 
    kotlinc ../Monde -include-runtime -d monde.jar
    if [ $? = 0 ] 
    then
        echo "Launch exec" 
        java -jar monde.jar
    fi
fi 

