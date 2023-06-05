# Developing Spring Boot Microservices

- Navigate into one of the projects.

Currently working together:
- analyzer-component
- Motor_Verification_Algorithm


## Deployment

```bash
./mvnw spring-boot:run 
```

## Packaging

```bash
./mvnw clean package

# After packaging the file is executable.
java -jar target/gs-rest-service-0.1.0.jar
```

Creates executable .jar file inside `target/`-directory. Can be used as "microservice"


## Entwickeln


### Port changing

`application.properties` - file inside `src/main/resources/` defines server port.


### Endpoint definition

Inside: `src/main/java/.../AnalyzingController.java`. (Same path in other project)


### External Endpoint access

Access via: `localhost:8080/get_status`



## Order of Verification algorithm

1. Fuel
2. Gearbox
3. Monitoring
4. Oil

## Resources

### Checkout

- Sending Post requests with spring: https://www.baeldung.com/spring-resttemplate-post-json


### Used

- Rest Server Service Guide: https://spring.io/guides/gs/rest-service/#scratch
- Rest Consuming Guide: https://spring.io/guides/gs/consuming-rest/
- Resilience4 Spring Guide: https://spring.io/guides/gs/cloud-circuit-breaker/