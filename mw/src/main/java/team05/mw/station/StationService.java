package team05.mw.station;

import java.util.List;

public interface StationService {

    List<Station> list();
    Station getStation(String rpId);
    List<Station> near(NearStationsRequest request);
    void increaseOccupied(String stationId);

}
