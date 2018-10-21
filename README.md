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

# Analysis

You should see something like this

```
REPOSITORY               SIZE
try-docker_small-alpine   4.41MB
try-docker_small-java     96.3MB

NAME                CPU %               MEM USAGE / LIMIT     MEM %               NET I/O             BLOCK I/O           PIDS
small-alpine        0.00%               548KiB / 1.952GiB     0.03%               858B / 0B           0B / 0B             2
small-java          0.33%               15.99MiB / 1.952GiB   0.80%               1.39kB / 0B         0B / 0B             25
``` 