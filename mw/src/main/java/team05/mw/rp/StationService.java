package team05.mw.rp;

import java.util.List;

public interface StationService {

    List<StationLight> list();
    Station getStation(String rpId);
    List<StationLight> near(NearRequest request);
    void increaseOccupied(String stationId);

}
