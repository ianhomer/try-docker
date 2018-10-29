FROM openjdk:11.0.1-jdk-slim as linker

RUN apt-get update
RUN apt-get install -y binutils
RUN /docker-java-home/bin/jlink \
    --module-path /docker-java-home/jmods/ \
    --verbose \
    --add-modules java.base \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --strip-debug \
    --output /jdk-slim
RUN strip -p --strip-unneeded /jdk-slim/lib/server/libjvm.so

FROM debian:sid-slim
COPY --from=linker /jdk-slim /jdk-slim
ENV JAVA_HOME=/jdk-slim
ENV PATH="$PATH:$JAVA_HOME/bin"
WORKDIR /
RUN java -Xshare:dump
ADD target/small-java-11-1.0.0-SNAPSHOT.jar application.jar
CMD echo "Starting application"
CMD java -Xshare:on -Xms1m -verbose:class -verbose:module -verbose:gc -verbose:jni -jar application.jar