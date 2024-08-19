## JDBC URL Differences in Docker Networking

The difference between `jdbc:postgresql://postgres:5432/studentDB` and `jdbc:postgresql://localhost:5432/studentDB` lies in how Docker networking works:

1. **`jdbc:postgresql://postgres:5432/studentDB`:**
    - `postgres` refers to the service name defined in your `docker-compose.yml`.
    - In Docker Compose, each service (such as `spring-boot-app`, `postgres`, and `pgadmin`) runs in its own container, but they share a common Docker network by default.
    - Docker Compose automatically sets up DNS resolution for service names. Within the Docker network, the `spring-boot-app` container can connect to the `postgres` container using the service name `postgres`.
    - This means that `jdbc:postgresql://postgres:5432/studentDB` will work correctly inside the Docker network.

2. **`jdbc:postgresql://localhost:5432/studentDB`:**
    - `localhost` refers to the loopback interface of the container where the application is running.
    - If you use `localhost` inside the Spring Boot container, it will try to connect to a PostgreSQL instance running within the same container. Since PostgreSQL is running in a separate container, this connection will fail.
    - However, if you were running your Spring Boot application directly on your host machine (outside of Docker), `localhost` would refer to your host machine. In this case, you could use `jdbc:postgresql://localhost:5432/studentDB` to connect to a PostgreSQL instance running locally.

### Summary

- **Inside Docker Compose:** Use `jdbc:postgresql://postgres:5432/studentDB`.
- **Outside Docker (direct on host):** Use `jdbc:postgresql://localhost:5432/studentDB`.

Since your Spring Boot application is running inside a Docker container, it should use the `postgres` service name to connect to PostgreSQL, as Docker handles the networking between containers.
