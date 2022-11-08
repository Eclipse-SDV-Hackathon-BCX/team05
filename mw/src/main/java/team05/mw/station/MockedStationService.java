package team05.mw.station;

import lombok.extern.slf4j.Slf4j;
import team05.mw.common.Coordinates;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
@Slf4j
public class MockedStationService implements StationService {

    private final Map<String, Station> internalStore = Collections.synchronizedMap(new HashMap<>());

    @PostConstruct
    public void setup() {
        createAndStoreMockedRp("RP-1", "Wolfslake-Ost", 13.008814, 52.686675, 3, 2);
        createAndStoreMockedRp("RP-2", "Wolfslake-West", 13.004903, 52.667512, 5, 5);
        createAndStoreMockedRp("RP-3", "Autohof Oberkrämer", 13.108898, 52.70811, 0, 5);
    }

    private void createAndStoreMockedRp(String id, String name, double lat, double lng, int capacity, int occupancy) {
        internalStore.put(id,
                Station.builder()
                        .id(id)
                        .name(name)
                        .coordinates(Coordinates.builder()
                                .lat(lat)
                                .lng(lng)
                                .build())
                        .capacity(capacity)
                        .occupancy(new AtomicInteger(occupancy))
                        .build());
    }

    @Override
    public List<Station> list() {
        return internalStore.values()
                .stream()
                .toList();
    }

    @Override
    public Station getStation(String stationId) {
        return internalStore.get(stationId);
    }

    @Override
    public List<Station> near(NearStationsRequest request) {
        return list();
    }

    @Override
    public void increaseOccupied(String stationId) {
        Station station = internalStore.get(stationId);
        if (station == null) {
            log.warn("station {} not found", stationId);
            return;
        }
        station.getOccupancy().incrementAndGet();
    }

}
