FROM openjdk:18-jdk-slim
WORKDIR /app
COPY target/evento_mascotas-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_RGXMKSIFDZFRV5GB /app/wallet
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# docker build -t evento_mascotas:latest .
# docker run -d -p 8080:8080 -e SPRING_PROFILES_ACTIVE=docker --name evento_mascotas evento_mascotas:latest