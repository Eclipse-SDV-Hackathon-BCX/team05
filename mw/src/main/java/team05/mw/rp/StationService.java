package team05.mw.rp;

import java.util.List;

public interface StationService {

    List<StationLight> list();
    Station getRp(String rpId);

    List<StationLight> near(NearRequest request);
}
