package team05.mw.truck;

import lombok.extern.slf4j.Slf4j;
import team05.mw.common.Coordinates;
import team05.mw.ecal.EcalMessage;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@Slf4j
public class MockedTruckService implements TruckService {

    private final Map<String, Truck> internalStore = Collections.synchronizedMap(new HashMap<>());

    @Override
    public List<Truck> list() {
        return internalStore.values().stream().toList();
    }

    @Override
    public void updateTruck(EcalMessage ecalMessage) {
        String truckId = ecalMessage.getUuid();
        Truck truck = internalStore.get(truckId);
        if (truck == null) {
            truck = Truck.builder()
                    .id(truckId)
                    .build();
        }
        truck.setPosition(Coordinates.builder()
                .lat(ecalMessage.getLatitude())
                .lng(ecalMessage.getLongitude())
                .build());

        internalStore.put(truckId, truck);
    }

    @Override
    public void detectStation(Truck truck) {

    }

    @Override
    public void enterStation(Truck truck) {

    }

    @Override
    public void leaveStation(Truck truck) {

    }

}
