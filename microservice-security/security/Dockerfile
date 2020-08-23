FROM adoptopenjdk/openjdk13
VOLUME /tmp
EXPOSE 8010
ADD ./target/security-0.0.1-SNAPSHOT.jar micro-security.jar
ENTRYPOINT [ "java", "-jar", "/micro-security.jar" ]