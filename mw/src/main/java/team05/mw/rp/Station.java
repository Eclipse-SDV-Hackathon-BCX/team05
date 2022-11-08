package team05.mw.rp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Station {

    private String id;
    private String name;
    private Coordinates coordinates;
    private int capacity;
    private int occupancy;

}
