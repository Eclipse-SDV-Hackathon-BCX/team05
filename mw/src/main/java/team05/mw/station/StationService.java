package team05.mw.station;

import java.util.List;

public interface StationService {

    List<StationLight> list();
    Station getStation(String rpId);
    List<StationLight> near(NearStationsRequest request);
    void increaseOccupied(String stationId);

}
