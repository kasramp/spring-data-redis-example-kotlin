# Spring Data Redis Example with Kotlin

## Introduction

This example shows how to use Spring Data with Redis to do CRUD operations. Additionally, it demonstrates using Redis as a message broker.

The code is implemented using Kotlin but it's easy for Java developers to understand as well.

For more in-depth details, have a look at the tutorials at links below:

- [https://blog.madadipouya.com/2019/07/09/getting-started-with-spring-data-redis-with-kotlin/](https://blog.madadipouya.com/2019/07/09/getting-started-with-spring-data-redis-with-kotlin/)
- [https://blog.madadipouya.com/2019/07/17/redis-pub-sub-with-spring-boot/](https://blog.madadipouya.com/2019/07/17/redis-pub-sub-with-spring-boot/)
- [https://blog.madadipouya.com/2019/08/02/server-sent-events-with-spring-mvc-sseemitter/](https://blog.madadipouya.com/2019/08/02/server-sent-events-with-spring-mvc-sseemitter/)

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
$ docker-compose -f docker-compose up -d
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

To interact with the APIs, after running the project, just oper [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html). You should see Swagger to interact with.
