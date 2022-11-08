package team05.mw.rp;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class MockedStationService implements StationService {

    private final Map<String, Station> internalStore = new HashMap<>();

    @PostConstruct
    public void setup() {
        createAndStoreMockedRp("RP-1", "Heide Nord", 0.0, 0.0, 3, 2);
        createAndStoreMockedRp("RP-2", "Zum Wilden Hirsch", 0.0, 0.0, 5, 5);
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
                        .occupancy(occupancy)
                        .build());
    }

    @Override
    public List<StationLight> list() {
        return internalStore.values()
                .stream()
                .map(station -> StationLight.builder()
                        .id(station.getId())
                        .name(station.getName())
                        .full(station.getCapacity() == station.getOccupancy())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Station getRp(String rpId) {
        return internalStore.get(rpId);
    }

    @Override
    public List<StationLight> near(NearRequest request) {
        return list();
    }

}
