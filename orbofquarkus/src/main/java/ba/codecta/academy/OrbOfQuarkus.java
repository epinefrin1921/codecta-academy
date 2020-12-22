package ba.codecta.academy;

import ba.codecta.academy.services.GameService;
import ba.codecta.academy.services.model.GameCharacterDto;
import ba.codecta.academy.services.model.GameDto;
import ba.codecta.academy.services.model.PowerUpDto;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/orb")
public class OrbOfQuarkus {
    public class Error {
        public String code;
        public String description;

        public Error(String code, String description) {
            this.code = code;
            this.description = description;
        }
    }
    @Inject
    GameService gameService;

    @Inject
    Validator validator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok(gameService.welcome()).build();
    }

    @GET
    @Path("/game")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGames(){
        List<GameDto> gameDtoList = gameService.findAllGames();
        if(gameDtoList==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameDtoList).build();
    }

    @GET
    @Path("/game/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameById(@PathParam("id") Integer id){
        GameDto gameDto = gameService.findGameById(id);
        if(gameDto==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameDto).build();
    }

    @POST
    @Path("/game")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(GameDto game, @Context UriInfo uriInfo){
        GameDto addedGame = gameService.addGame(game);
        if(addedGame != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(addedGame.getId()));
            return Response.created(uriBuilder.build()).entity(addedGame).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown theme park in request.")) .build();
    }
    @PUT
    @Path("/game/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGameById(@PathParam("id") Integer id, GameDto game){
        GameDto gameDto = gameService.updateGame(id, game);
        if(gameDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameDto).build();
    }
    @POST
    @Path("/game/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPowerUpToGame(@PathParam("id") Integer id, Integer powerUp){
        GameDto gameDto = gameService.addPowerUpToGame(id, powerUp);
        if(gameDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameDto).build();
    }

    @GET
    @Path("/characters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCharacters(){
        List<GameCharacterDto> gameCharacterDto=gameService.findAllGameCharacters();
        if(gameCharacterDto==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameCharacterDto).build();
    }

    @GET
    @Path("/characters/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharacterById(@PathParam("id") Integer id)
    {
        GameCharacterDto character = gameService.findGameCharacterById(id);
        if(character == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(character).build();
    }

    @POST
    @Path("/characters")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCharacter(GameCharacterDto character, @Context UriInfo uriInfo){
        GameCharacterDto gameCharacter = gameService.addGameCharacter(character);
        if(gameCharacter!=null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(gameCharacter.getId()));
            return Response.created(uriBuilder.build()).entity(gameCharacter).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown theme park in request.")) .build();
    }

    @GET
    @Path("/powerups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPowerUps(){
        List<PowerUpDto> powerUpDtoList = gameService.findAllPowerUps();
        if(powerUpDtoList==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(powerUpDtoList).build();
    }

    @GET
    @Path("/powerups/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPowerUpsById(@PathParam("id") Integer id)
    {
        PowerUpDto powerUp = gameService.findPowerUpById(id);
        if(powerUp == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(powerUp).build();
    }

    @POST
    @Path("/powerups")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPowerUp(PowerUpDto power, @Context UriInfo uriInfo){
        PowerUpDto powerUpDto = gameService.addPowerUp(power);
        if(powerUpDto!= null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(powerUpDto.getId()));
            return Response.created(uriBuilder.build()).entity(powerUpDto).build();

        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown theme park in request.")) .build();
    }
}