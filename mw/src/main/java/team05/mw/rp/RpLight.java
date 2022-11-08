package team05.mw.rp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RpLight {

    private String id;
    private String name;
    private boolean full;

}
