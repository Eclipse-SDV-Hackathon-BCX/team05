package team05.mw.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Coordinates {

    private double lat;
    private double lng;

}
