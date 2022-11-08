package team05.mw.station;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NearStationsRequest {

    private int maxDistanceInMeter;
    private double lat;
    private double lng;

}
