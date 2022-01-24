package soa.lab3.web;

import soa.lab3.common.entities.HumanBeing;
import soa.lab3.beans.HumanBeingService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/human_being")
public class HumanBeingRes {
    @EJB
    private HumanBeingService humanBeingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getHumanBeings(@QueryParam("pageSize") String pageSize, @QueryParam("pageNumber") String pageNumber) {
        return Response.status(200)
                .entity(humanBeingService.listUser(pageSize, pageNumber))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHumanBeing(@PathParam("id") int id) {
        return Response.status(200)
                .entity(humanBeingService.getUserById(id))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewHumanBeing(HumanBeing humanBeing) {
        return Response.status(200)
                .entity(humanBeingService.insertUser(humanBeing))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHumanBeing(@PathParam("id") int id) {
        return Response.status(200)
                .entity(humanBeingService.deleteUser(id))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHumanBeing(HumanBeing humanBeing) {
        return Response.status(200)
                .entity(humanBeingService.updateUser(humanBeing))
                .build();
    }


}
