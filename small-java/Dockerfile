FROM openjdk:10.0.2-jdk-slim as linker

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
ADD target/small-java-1.0.0-SNAPSHOT.jar application.jar
CMD echo "Starting application"
CMD java -XshowSettings:all -Xms16m -Xmx16m -Xmn16m -jar application.jar