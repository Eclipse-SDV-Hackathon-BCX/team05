# Middleware

- should provide REST API endpoints for
  - /station/list
  - /station/{statioId}/status

  - consumes data either
    - from database (persisted timeseries, see ../conv)
    - realtime e.g. MQTT, Zenoh

- based on Quarkus 2.13.4

## TODOs

- use data from raststaetten.geojson to setup Station repo (=> "capacity:truck": "200")
- implement station detection (in TruckService)
- implement decreaseOccupied (in TruckService)
- ...

## Build and run native image

Also see instructions here: 
* https://quarkus.io/guides/container-image
* https://quarkus.io/guides/podman
```
./gradlew build -Dquarkus.package.type=native
build/mw-1.0-SNAPSHOT-runner
curl localhost:8080/station
```

## REST API

* set MQTT configuration
  ```
  export MP_MESSAGING_INCOMING_ECAL_TOPIC=ecal/mqtt
  export MP_MESSAGING_INCOMING_ECAL_HOST=<host-from-slack>
  export MP_MESSAGING_INCOMING_ECAL_PORT=1883
  export MP_MESSAGING_INCOMING_ECAL_USERNAME=<username-from-slack>
  export MP_MESSAGING_INCOMING_ECAL_PASSWORD=<password-from-slack>
  ```

* startup service
  ```shell
  ./gradlew quarkusDev
  ```

* startup service on all ip addresses
  ```shell
  ./gradlew -Dquarkus.http.host=0.0.0.0 quarkusDev
  ```

* check OpenAPI schema
  ```shell
  curl http://localhost:8080/q/openapi
  ```

* open [Swagger UI](http://localhost:8080/q/swagger-ui)

## Glossar
* station: service area
