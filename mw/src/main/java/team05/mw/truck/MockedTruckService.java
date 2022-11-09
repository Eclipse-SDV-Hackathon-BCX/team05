package team05.mw.truck;

import io.vertx.mutiny.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import team05.mw.common.Coordinates;
import team05.mw.ecal.EcalMessage;
import team05.mw.geo.GeoService;
import team05.mw.station.Station;
import team05.mw.station.StationService;

import javax.inject.Singleton;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingQueue;

@Singleton
@Slf4j
public class MockedTruckService implements TruckService {

    private final int QUEUE_CAPACITY = 60 / 3;
    private final Map<String, Truck> internalStore = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Queue<Coordinates>> postitionQueues = new ConcurrentSkipListMap<>();

    private final GeoService geoService;
    private final StationService stationService;
    private final EventBus eventBus;

    public MockedTruckService(GeoService geoService, StationService stationService, EventBus eventBus) {
        this.geoService = geoService;
        this.stationService = stationService;
        this.eventBus = eventBus;
    }

    @Override
    public List<Truck> list() {
        return internalStore.values().stream().toList();
    }

    @Override
    public Optional<Truck> findById(String truckId) {
        return Optional.ofNullable(internalStore.get(truckId));
    }

    @Override
    public void updateTruck(EcalMessage ecalMessage) {
        String truckId = ecalMessage.getUuid();
        Truck truck = internalStore.get(truckId);
        Queue<Coordinates> positionQueue = postitionQueues.get(truckId);

        if (truck == null) {
            truck = Truck.builder()
                    .id(truckId)
                    .build();
            positionQueue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
        }

        positionQueue.offer(Coordinates.builder()
                .lat(ecalMessage.getLatitude())
                .lng(ecalMessage.getLongitude())
                .build());

        internalStore.put(truckId, truck);
        postitionQueues.put(truckId, positionQueue);

        updateTruckCurrentPosition(truckId, positionQueue.poll());
//        if (positionQueue.size() == QUEUE_CAPACITY) {
//            new PositionConsumer(truckId, positionQueue)
//                    .run();
//        }
    }

    void updateTruckCurrentPosition(String truckId, Coordinates position) {
        Truck truck = internalStore.get(truckId);
        truck.setPosition(position);
        log.trace("updated truck #{} position to {}", truck, position);

        // invoke detect if truck is in station area
        Optional<Station> optionalInStation = geoService.isInRoadhouse(position);
        optionalInStation.ifPresent(station -> stationService.park(station, truckId));
        if (optionalInStation.isEmpty()) {
            stationService.unpark(truckId);
        }
        eventBus.publish("truck-position-update", truck);
    }

    class PositionConsumer implements Runnable {

        private String truckId;
        private Queue<Coordinates> positionQueue;

        PositionConsumer(String truckId, Queue<Coordinates> positionQueue) {
            this.truckId = truckId;
            this.positionQueue = positionQueue;
        }

        @Override
        public void run() {
            log.info("started PositionConsumer for truck #{}", truckId);
            int size = positionQueue.size();
            Coordinates currentPosition = null;
            for (int idx=0; idx < size; idx++) {
                currentPosition = positionQueue.poll();
            }
            log.info("processed position queue for truck #{}", truckId);
            if (currentPosition != null) {
                updateTruckCurrentPosition(truckId, currentPosition);
            }
        }
    }
}
