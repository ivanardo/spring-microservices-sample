#### For dockerize the application:
1. `mvn clean compile jib:build -DskipTests`
2. `docker compose up -d`

#### For get access token 
Generate secret and access token on [Credentials page](http://localhost:8080/admin/master/console/#/realms/spring-boot-microservices-realm/clients/97c3b790-0cfc-4c78-8ed7-c31ab23f9b56/credentials)

#### [Eureka](http://localhost:8181/eureka/web)
 - http://localhost:8181/eureka/web
 - http://localhost:8761

