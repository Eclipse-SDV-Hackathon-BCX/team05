package team05.mw.rp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response list() {
        return Response.ok(stationService.list()).build();
    }

    @GET
    @Path("/{rpId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Station getRp(@PathParam("rpId") String rpId) {
        return stationService.getRp(rpId);
    }

    @POST
    @Path("/near")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StationLight> near(NearRequest req) {
        return stationService.near(req);
    }

}
