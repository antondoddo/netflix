FROM openjdk:8-alpine

# Install maven
RUN apk update
RUN apk add maven

WORKDIR /netflix

# Prepare by downloading dependencies
ADD pom.xml /netflix/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /netflix/src
RUN ["mvn", "package"]

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/sparkexample-jar-with-dependencies.jar"]