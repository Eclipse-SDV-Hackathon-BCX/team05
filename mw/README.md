# Middleware component

- should provide REST API endpoints for
  - /stations
  - /station/<id>status

  - consumes data either
    - from database (persisted timeseries, see ../conv)
    - realtime e.g. MQTT, Zenoh
