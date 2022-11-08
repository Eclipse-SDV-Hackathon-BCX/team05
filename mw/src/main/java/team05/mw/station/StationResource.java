package team05.mw.station;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/station")
public class StationResource {

    private final StationService stationService;

    public StationResource(StationService stationService) {
        this.stationService = stationService;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Station> list() {
        return stationService.list();
    }

    @GET
    @Path("/{stationId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Station getRp(@PathParam("stationId") String stationId) {
        return stationService.getStation(stationId);
    }

    @POST
    @Path("/near")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Station> near(NearStationsRequest req) {
        return stationService.near(req);
    }

}
