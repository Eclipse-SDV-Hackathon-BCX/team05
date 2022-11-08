package team05.mw.rp;

import java.util.List;

public interface RpService {

    List<RpLight> list();
    Rp getRp(String rpId);

    List<RpLight> near(NearRequest request);
}
