#!/bin/bash
echo ============================================================
echo Generate JAR
echo ============================================================
mvn clean package -DskipTests
echo ============================================================
echo BUILD and PUSH Project to Docker
echo ============================================================
docker build -t antony0618/micro-security .
docker push antony0618/micro-security
echo ============================================================
echo End Process
echo ============================================================