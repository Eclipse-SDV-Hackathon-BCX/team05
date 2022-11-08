package team05.mw.rp;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class MockedRpService implements RpService {

    private final Map<String, Rp> internalStore = new HashMap<>();

    @PostConstruct
    public void setup() {
        createAndStoreMockedRp("RP-1", "Heide Nord", 0.0, 0.0, 3, 2);
        createAndStoreMockedRp("RP-2", "Zum Wilden Hirsch", 0.0, 0.0, 5, 5);
    }

    private void createAndStoreMockedRp(String id, String name, double lat, double lng, int capacity, int occupancy) {
        internalStore.put(id,
                Rp.builder()
                        .id(id)
                        .name(name)
                        .coordinates(RpCoordinates.builder()
                                .lat(lat)
                                .lng(lng)
                                .build())
                        .capacity(capacity)
                        .occupancy(occupancy)
                        .build());
    }

    @Override
    public List<RpLight> list() {
        return internalStore.values()
                .stream()
                .map(rp -> RpLight.builder()
                        .id(rp.getId())
                        .name(rp.getName())
                        .full(rp.getCapacity() == rp.getOccupancy())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Rp getRp(String rpId) {
        return internalStore.get(rpId);
    }

    @Override
    public List<RpLight> near(NearRequest request) {
        return list();
    }

}
