package team05.mw.ecal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EcalMessage {

    private String uuid;
    private int acceleration;
    private double latitude;
    private double longitude;

}
