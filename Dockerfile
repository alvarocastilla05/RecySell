# Etapa 1: Construir frontend
FROM node:20-alpine AS build-frontend
WORKDIR /app/front
COPY Front-RecySell/package*.json ./
RUN npm install -g @angular/cli && npm install
COPY Front-RecySell/ .
RUN ng build --configuration production

# Etapa 2: Construir backend
FROM maven:3.9-eclipse-temurin-17 AS build-backend
WORKDIR /app
COPY Recysell/ .
RUN mvn clean package -DskipTests

# Etapa 3: Imagen final
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build-backend /app/target/*.jar app.jar
COPY --from=build-frontend /app/front/dist/front-recy-sell/ /app/static/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
