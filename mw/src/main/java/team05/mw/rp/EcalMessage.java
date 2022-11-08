package team05.mw.rp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EcalMessage {

    private String carId;
    private double latitude;
    private double longitude;
    private int accel;

}
