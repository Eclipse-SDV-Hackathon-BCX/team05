package team05.mw.rp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/truck")
public class TruckResource {

    private final TruckService truckService;

    public TruckResource(TruckService truckService) {
        this.truckService = truckService;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(truckService.list()).build();
    }

}
