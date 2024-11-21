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

# Pokretanje Admin sevice-a
echo "Starting Admin Service..."
cd admin || exit
./gradlew clean build -x test &
check_status "Admin Service"
cd ..

# Pokretanje Config Server-a
echo "Starting Config Server..."
cd config-server || exit
./gradlew clean build -x test &
check_status "Config Server"
cd ..

# Pokretanje Eureka Server-a
echo "Starting Eureka Server..."
cd eureka-server || exit
./gradlew clean build -x test &
check_status "Eureka Server"
cd ..

# Pokretanje API Gateway-a
echo "Starting API Gateway..."
cd api-gateway || exit
./gradlew clean build -x test &
check_status "API Gateway"
cd ..

# Pokretanje Order Service-a
echo "Starting Order Service..."
cd order-service || exit
./gradlew clean build -x test &
check_status "Order Service"
cd ..

# Pokretanje Inventory Service-a
echo "Starting Inventory Service..."
cd inventory-service || exit
./gradlew clean build -x test &
check_status "Inventory Service"
cd ..

# Pokretanje Notification Service-a
echo "Starting Notification Service..."
cd notification-service || exit
./gradlew clean build -x test &
check_status "Notification Service"
cd ..

echo -e "${GREEN}All services started successfully!${NC}"
