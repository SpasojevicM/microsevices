#!/bin/bash

# List of topics to create
TOPICS=("order-created")

# Kafka broker address
BROKER=kafka:9092

# Create each topic
for TOPIC in "${TOPICS[@]}"; do
    echo "Creating topic: $TOPIC"
    kafka-topics.sh --create \
        --bootstrap-server $BROKER \
        --replication-factor 1 \
        --partitions 1 \
        --topic $TOPIC
done
