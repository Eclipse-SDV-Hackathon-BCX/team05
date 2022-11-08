package team05.mw.rp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NearRequest {

    private int maxDistanceInMeter;
    private double lat;
    private double lng;

}
