package team05.mw.station;

import java.util.List;
import java.util.Optional;

public interface StationService {

    List<Station> list();
    Station getStation(String rpId);
    List<Station> near(NearStationsRequest request);
    void increaseOccupied(Station station);
    void decreaseOccupied(Station station);

    Optional<Station> findByName(String name);

    void park(Station station, String truckId);
    void unpark(String truckId);
}
