# Business Platform V1

Reusable small-business management template:
- Java 21 + Spring Boot 3.5
- PostgreSQL 17
- React + TypeScript + Vite
- Spring Security + JWT
- Flyway migrations
- Docker Compose
- Basic Users, Customers, Products, Orders APIs
- React dashboard/login/customers/products/orders starter UI

## Run with Docker
```bash
docker compose up --build
```

Frontend: http://localhost:5173
Backend: http://localhost:8080
Swagger: http://localhost:8080/swagger-ui.html
Health: http://localhost:8080/actuator/health

Default admin:
username: admin
password: Admin@123

Change the default password before any real deployment.

## Local development
Backend:
```bash
cd backend
./mvnw spring-boot:run
```

Frontend:
```bash
cd frontend
npm install
npm run dev
```
