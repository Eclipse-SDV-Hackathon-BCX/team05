package team05.mw.rp;

import lombok.Builder;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
public class Station {

    private String id;
    private String name;
    private Coordinates coordinates;
    private int capacity;
    private AtomicInteger occupancy;

}
