@echo off
title SmarTrip Server
cd /d %~dp0
java -jar target/controller-0.0.1-SNAPSHOT.jar
pause
