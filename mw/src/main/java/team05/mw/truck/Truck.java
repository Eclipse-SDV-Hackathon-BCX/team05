package team05.mw.truck;

import lombok.Builder;
import lombok.Data;
import team05.mw.common.Coordinates;

@Builder
@Data
public class Truck {

    private String id;
    private Coordinates position;

}
