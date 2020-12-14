package ba.codecta.academy;

import ba.codecta.academy.model.DisneyCharacter;
import ba.codecta.academy.services.DisneyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/disney")
public class quarkusPostgresql {

    @Inject
    DisneyService disneyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return  Response.ok(disneyService.welcome()).build();
    }

    @GET
    @Path("/characters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCharacters()
    {
        List<DisneyCharacter> characterList = disneyService.findAllCharacters();
        if(characterList == null || characterList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(characterList).build();
    }
    @GET
    @Path("/characters/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharacterById(@PathParam("id") Integer id)
    {
        DisneyCharacter character = disneyService.findCharacterById(id);
        if(character == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(character).build();
    }

    @PUT
    @Path("/characters/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCharacterById(@PathParam("id") Integer id, DisneyCharacter disneyCharacter)
    {
        DisneyCharacter character = disneyService.updateCharacter(id, disneyCharacter);
        if(character == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(character).build();
    }

    @POST
    @Path("/characters")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createCharacter(DisneyCharacter character, @Context UriInfo uriInfo)
    {
        DisneyCharacter disneyCharacter = disneyService.addCharacter(character);
        if(disneyCharacter != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(disneyCharacter.getId()));
            return Response.created(uriBuilder.build()).entity(disneyCharacter).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}