# Etapa 1: Build de Angular
FROM node:20-alpine AS build-frontend
WORKDIR /frontend
COPY front-recysell/package*.json ./
RUN npm install -g @angular/cli && npm install
COPY front-recysell/ .
RUN ng build --configuration production

# Etapa 2: Build de Spring Boot
FROM maven:3.9-eclipse-temurin-17 AS build-backend
WORKDIR /backend
COPY recysell/pom.xml .
COPY recysell/src ./src
# Copiar el dist generado de Angular dentro de static/
COPY --from=build-frontend /frontend/dist/* ./src/main/resources/static/
RUN mvn clean package -DskipTests

# Etapa 3: Imagen final
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build-backend /backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
