# Microgrid

An application implemented as a collection of microservices communicating together
 in a manner which encourage low coupling and high cohesion.


### Gateway
An unified entrypoint/interface for the microservice system. 
This gateway will also communicate with the auth service (keycloak).
Customize and tailor the API based on who the client is; 
different clients need different APIs.

### Auth

Session management become complicated as each service is self-contained and stateless.
`JWT` will eventually be attached to each request and shared across services. 
`JWT` are stateless (client validate token without 3rd party) and time bound. 

---

## TBA

- `Keycloak`: Identity and access management. Partially implemented.
- `CircuitBreaker`: Addressing failures in communication between services (e.g., service down, response dropped)

---

### Run application
#### Maven
```bash
docker run -p 8082:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:21.0.0 start-dev
# requires local database instance running
cd blog-service
mvn spring-boot:run

cd api-gateway
mvn spring-boot:run
```

#### Docker
```bash
docker-compose up
```
