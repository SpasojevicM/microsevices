# Koristi zvaničnu JDK sliku (Java 21)
FROM openjdk:21-jdk-slim

# Definiši radni direktorijum u kontejneru
WORKDIR /app

# Kopiraj "jar" fajl iz build foldera u kontejner
COPY build/libs/inventory-service-*.jar inventory-service.jar

# Otvorite port 8080 (ili koji god port koristi aplikacija)
EXPOSE 8080

# Definiši komandu koja se pokreće kada kontejner startuje
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]
