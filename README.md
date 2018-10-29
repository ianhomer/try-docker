# try-docker

Try docker.  In particular determine minimum footprints of different toolings.

# TL;DR

    mvn clean package

# Docker

Full rebuild

    mvn package -P quick
    docker-compose up -d --build && docker-compose logs -f    
    
See image footprint

    docker image list

See process stats

    docker stats

Just redeploy small-java-11

    docker-compose up -d --build small-java-11 && docker-compose logs -f small-java-11

To view the process [status](http://man7.org/linux/man-pages/man5/proc.5.html), with
VmRSS being an indicator of the memory footprint of the process.

    docker exec small-java-10 cat /proc/8/status
    docker exec small-java-11 cat /proc/8/status

# Analysis

## Docker stats

You should see something like this

```
REPOSITORY               SIZE
try-docker_small-alpine   4.41MB
try-docker_small-java     96.3MB

NAME                CPU %               MEM USAGE / LIMIT     MEM %               NET I/O             BLOCK I/O           PIDS
small-alpine        0.00%               548KiB / 1.952GiB     0.03%               858B / 0B           0B / 0B             2
small-java          0.33%               15.99MiB / 1.952GiB   0.80%               1.39kB / 0B         0B / 0B             25
```

## JVM Analysis

Run up a local node with

    java -Xms1m -verbose:class -verbose:module -verbose:gc -verbose:jni -jar small-java-11/target/small-java-11-1.0.0-SNAPSHOT.jar

And connect with

    jconsole



