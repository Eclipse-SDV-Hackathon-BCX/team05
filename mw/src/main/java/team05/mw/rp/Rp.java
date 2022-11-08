package team05.mw.rp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rp {

    private String id;
    private String name;
    private RpCoordinates coordinates;
    private int capacity;
    private int occupancy;

}
