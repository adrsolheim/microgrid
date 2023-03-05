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

To keep the `iss` part of the `JWT` consistent, the docker name of keycloak has to be a
valid hostname that points to the running keycloak instance.
Additionally, *Frontend URL* of keycloak container needs to be set in keycloak: `http://keycloak:8082`
Add the following line to `/etc/hosts`
```
127.0.0.1 keycloak
```

---

## TBA

- `Keycloak`: Identity and access management. Partially implemented.
- `CircuitBreaker`: Addressing failures in communication between services (e.g., service down, response dropped)

---

### Run application
Run keycloak and fetch the client secret. 
Put the secret (`KEYCLOAK_CLIENT_SECRET=`) in `/.env` 
where docker-compose will be able to load it as an environment variable.

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
