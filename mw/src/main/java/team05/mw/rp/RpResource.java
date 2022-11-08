package team05.mw.rp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rp")
public class RpResource {

    private final RpService rpService;

    public RpResource(RpService rpService) {
        this.rpService = rpService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(rpService.list()).build();
    }

    @GET
    @Path("/{rpId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rp getRp(@PathParam("rpId") String rpId) {
        return rpService.getRp(rpId);
    }

    @POST
    @Path("/near")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RpLight> near(NearRequest req) {
        return rpService.near(req);
    }

}
