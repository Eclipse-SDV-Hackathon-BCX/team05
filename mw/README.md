# Middleware

- should provide REST API endpoints for
  - /stations
  - /station/<id>status

  - consumes data either
    - from database (persisted timeseries, see ../conv)
    - realtime e.g. MQTT, Zenoh

## REST API

* startup service
  ```shell
  ./gradlew quarkusDev
  ```

* check OpenAPI schema
  ```shell
  curl http://localhost:8080/q/openapi
  ```

* open [Swagger UI](http://localhost:8080/q/swagger-ui)
