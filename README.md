# Spring Boot Application with Docker, PostgreSQL, and pgAdmin

This project demonstrates how to containerize a Spring Boot application using Docker and run it alongside PostgreSQL and pgAdmin.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- Docker
- Docker Compose
- Maven (for building the Spring Boot application)

## Steps to Run the Application

### 1. Create a Dockerfile for Your Spring Boot Application

Ensure you have a `Dockerfile` in the root directory of your Spring Boot application. Here's a basic example:

```dockerfile
# Use a Maven build container to compile and package the application
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/web_app_0.0.3.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
```

### 2. Uncomment the Spring Boot Service in Docker Compose

Ensure that PostgreSQL and pgAdmin services are running, and uncomment the Spring Boot service in your docker-compose.yml file:

```dockerfile
# docker-compose.yml
# uncomment spring-boot-app section
version: "3.8"
services:
  spring-boot-app:
    build:
      context: .
      dockerfile: dockerfile
    ports:
      - "8082:8081"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/studentDB
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - postgres

  postgres:
    container_name: postgres_container
    image: postgres:15
    restart: always
    environment:
      POSTGRES_USER: root
      POSTGRES_PASSWORD: root
      POSTGRES_DB: studentDB
    ports:
      - "5432:5432"
  pgadmin:
    container_name: pgadmin4_container
    image: dpage/pgadmin4
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@admin.com
      PGADMIN_DEFAULT_PASSWORD: root
    ports:
      - "5050:80"
    depends_on:
      - postgres

```

### 3. Build and Run the Docker Containers

1. **Build the Docker images and start the containers:**

   Run the following command in the root directory of your project:

   ```bash
   docker-compose up --build
   ```
2. **Access your Spring Boot application:**
   The application will be accessible at http://localhost:8082 due to the port mapping (8082:8081).

3. **Check PostgreSQL and pgAdmin:**

Once the Docker containers are up and running, you can verify the following:

- **PostgreSQL**: The PostgreSQL database is running on port `5432`.
- **pgAdmin**: The pgAdmin web interface is accessible at `http://localhost:5050`.

### 4. Troubleshooting

If you encounter any issues, here are some common troubleshooting steps:

- **Database Connection Issues**: If the Spring Boot application cannot connect to PostgreSQL, ensure that the `SPRING_DATASOURCE_URL` in the `docker-compose.yml` file is correctly configured as:

  ```yaml
  SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/studentDB
