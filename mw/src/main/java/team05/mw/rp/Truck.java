package team05.mw.rp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Truck {

    private String id;
    private Coordinates position;

}
