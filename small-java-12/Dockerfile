FROM openjdk:12-ea-14-jdk-alpine3.8
WORKDIR /
ADD target/small-java-12-1.0.0-SNAPSHOT.jar application.jar
CMD echo "Starting application"
CMD java -Xms1m -verbose:class -verbose:module -verbose:gc -verbose:jni -jar application.jar