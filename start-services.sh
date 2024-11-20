#!/bin/bash

# Boje za output
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Funkcija za proveru uspešnosti
check_status() {
  if [ $? -eq 0 ]; then
    echo -e "${GREEN}[SUCCESS]${NC} $1 started."
  else
    echo -e "${RED}[FAIL]${NC} Failed to start $1."
    exit 1
  fi
}

echo "Starting Docker Compose for database and Kafka..."
docker-compose up -d
check_status "Docker Compose"

# Pokretanje Config Server-a
echo "Starting Config Server..."
cd config-server || exit
./gradlew bootRun &
check_status "Config Server"
cd ..

# Pokretanje Eureka Server-a
echo "Starting Eureka Server..."
cd eureka-server || exit
./gradlew bootRun &
check_status "Eureka Server"
cd ..

# Pokretanje API Gateway-a
echo "Starting API Gateway..."
cd api-gateway || exit
./gradlew bootRun &
check_status "API Gateway"
cd ..

# Pokretanje Order Service-a
echo "Starting Order Service..."
cd order-service || exit
./gradlew bootRun &
check_status "Order Service"
cd ..

# Pokretanje Inventory Service-a
echo "Starting Inventory Service..."
cd inventory-service || exit
./gradlew bootRun &
check_status "Inventory Service"
cd ..

# Pokretanje Notification Service-a
echo "Starting Notification Service..."
cd notification-service || exit
./gradlew bootRun &
check_status "Notification Service"
cd ..

echo -e "${GREEN}All services started successfully!${NC}"
