#!/bin/bash
set -e

CONTAINERS=$(docker ps -aq)
CURRENT_PATH=$(pwd)

if [[ $CONTAINERS != "" ]]; then
    docker stop $(docker ps -aq);
    docker rm $(docker ps -aq);
fi

# RUN DBs
docker compose \
    -f $CURRENT_PATH/payment/db/docker-compose.yml \
    -f $CURRENT_PATH/tennis-court/db/docker-compose.yml \
    -f $CURRENT_PATH/tennis-player/db/docker-compose.yml \
    up -d

# # RUN APIGW AND DISCOVERY
# docker compose \
#     -f $CURRENT_PATH/docker-compose.yml \
#     up -d

# sleep 30s

# # RUN SERVICES
# docker compose \
#     -f $CURRENT_PATH/payment/docker-compose.yml \
#     -f $CURRENT_PATH/tennis-court/docker-compose.yml \
#     -f $CURRENT_PATH/tennis-player/docker-compose.yml \
#     up -d