#!/bin/bash

KAFKA_BROKER="localhost:9092"

kafka-topics.sh --create --topic order-created --partitions 3 --replication-factor 1 --bootstrap-server $KAFKA_BROKER
kafka-topics.sh --describe --topic order-created --bootstrap-server $KAFKA_BROKER
