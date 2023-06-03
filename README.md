# Spring Data Redis Example with Kotlin

## Introduction

This example shows how to use Spring Data with Redis to do CRUD operations. Additionally, it demonstrates using Redis as a message broker.

The code is implemented using Kotlin, but it's easy for Java developers to understand as well.

For more in-depth details, have a look at the tutorials at links below:

- [Getting started with Spring Data Redis with Kotlin](https://www.geekyhacker.com/getting-started-with-spring-data-redis-with-kotlin/)
- [Redis Pub/Sub with Spring Boot](https://www.geekyhacker.com/redis-pub-sub-with-spring-boot/)
- [Server-Sent Events with Spring MVC SseEmitter](https://www.geekyhacker.com/server-sent-events-with-spring-mvc-sseemitter/)
- [Distributed SSE with Spring SseEmitter and Redis Pub/Sub](https://www.geekyhacker.com/distributed-sse-with-spring-sseemitter-and-redis-pub-sub/)

## Endpoints

To demonstrate CRUD operations, I use `Movie`, `Actor` model and created some endpoints to do basic CRUD operations as follows:

- Create Movie
- Update Movie
- Delete Movie
- List Movies
- Create Actor
- Update Actor
- Delete Actor
- List Actors
- Add an Actor to a Movie 
 
## How to run

The project needs Redis to run. There's a `docker-compose` file contains all necessary configuration to run Redis flawlessly.

To use it. Just run:

```bash
$ docker-compose -f docker-compose.yml up -d
``` 

After that the Redis should be accessible via port `6379` on `localhost` 

Then you can run the application like below:

```bash
$ ./mvnw spring-boot:run -Dserver.port=8090
```

If you have a Redis running in another port or host, simply override below environment variables:

- `SPRING_REDIS_HOST`
- `SPRING_REDIS_PORT`

Or modify `application.properties` file.

To interact with the APIs, after running the project, just open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). You should see Swagger to interact with.
