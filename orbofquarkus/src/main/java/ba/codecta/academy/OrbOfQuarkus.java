package ba.codecta.academy;

import ba.codecta.academy.services.GameService;
import ba.codecta.academy.services.model.*;

import javax.inject.Inject;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response start() {
        return Response.ok(gameService.welcome()).build();
    }
    //game
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
    public Response createGame(GameStartDto game, @Context UriInfo uriInfo){
        GameDto addedGame = gameService.addGame(game);
        if(addedGame != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(addedGame.getId()));
            return Response.created(uriBuilder.build()).entity(addedGame).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Cannot post that game. Try changing some attributes and try again")) .build();
    }
    @PUT
    @Path("/game/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGameById(@PathParam("id") Integer id, GameStartDto game){
        GameDto gameDto = gameService.updateGame(id, game);
        if(gameDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameDto).build();
    }
    @POST
    @Path("/game/{id}/powerup")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPowerUpToGame(@PathParam("id") Integer id, Integer powerUp){
        GameDto gameDto = gameService.addPowerUpToGame(id, powerUp);
        if(gameDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameDto).build();
    }
    //add get powerup
    //character
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
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request.")) .build();
    }
    @PUT
    @Path("/characters/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCharacterById(@PathParam("id") Integer id, GameCharacterDto character){
        GameCharacterDto gameCharacterDto = gameService.updateGameCharacter(id, character);
        if(gameCharacterDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameCharacterDto).build();
    }
    //powerups
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
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request.")) .build();
    }
    @PUT
    @Path("/powerups/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePowerUpById(@PathParam("id") Integer id, PowerUpDto powerUp){
        PowerUpDto powerUpDto = gameService.updatePowerUp(id, powerUp);
        if(powerUpDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(powerUpDto).build();
    }
    //dungeons
    @GET
    @Path("/dungeons")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDungeons(){
        List<DungeonDto> dungeonDtoList = gameService.findAllDungeons();
        if(dungeonDtoList==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dungeonDtoList).build();
    }
    @GET
    @Path("/dungeons/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDungeonById(@PathParam("id") Integer id)
    {
        DungeonDto dungeonDto = gameService.findDungeonById(id);
        if(dungeonDto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dungeonDto).build();
    }
    @POST
    @Path("/dungeons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDungeon(DungeonDto dungeon, @Context UriInfo uriInfo){
        DungeonDto dungeonDto = gameService.addDungeon(dungeon);
        if(dungeonDto!= null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(dungeonDto.getId()));
            return Response.created(uriBuilder.build()).entity(dungeonDto).build();

        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request.")) .build();
    }
    @PUT
    @Path("/dungeons/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDungeonById(@PathParam("id") Integer id, DungeonDto dungeonDto){
        DungeonDto dungeon = gameService.updateDungeon(id, dungeonDto);
        if(dungeon == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dungeon).build();
    }
    //monsters
    @GET
    @Path("/monsters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMonsters(){
        List<MonsterDto> monsterDtoList = gameService.findAllMonsters();
        if(monsterDtoList==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(monsterDtoList).build();
    }
    @GET
    @Path("/monsters/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMonsterById(@PathParam("id") Integer id)
    {
        MonsterDto monsterDto = gameService.findMonsterById(id);
        if(monsterDto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(monsterDto).build();
    }
    @POST
    @Path("/monsters")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMonster(MonsterDto monster, @Context UriInfo uriInfo){
        MonsterDto monsterDto = gameService.addMonster(monster);
        if(monsterDto!= null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(monsterDto.getId()));
            return Response.created(uriBuilder.build()).entity(monsterDto).build();

        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request.")) .build();
    }
    @PUT
    @Path("/monsters/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMonsterById(@PathParam("id") Integer id, MonsterDto monster){
        MonsterDto monsterDto = gameService.updateMonster(id, monster);
        if(monsterDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(monsterDto).build();
    }
    //game maps, no update
    @GET
    @Path("/gamemaps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGameMaps(){
        List<GameMapDto> gameMapDtoList = gameService.findAllGameMaps();
        if(gameMapDtoList==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameMapDtoList).build();
    }
    @GET
    @Path("/gamemaps/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameMapById(@PathParam("id") Integer id)
    {
        GameMapDto gameMapDto = gameService.findGameMapById(id);
        if(gameMapDto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(gameMapDto).build();
    }
    @POST
    @Path("/gamemaps")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGameMap(GameMapDto gameMap, @Context UriInfo uriInfo){
        GameMapDto gameMapDto = gameService.addGameMap(gameMap);
        if(gameMapDto!= null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(gameMapDto.getId()));
            return Response.created(uriBuilder.build()).entity(gameMapDto).build();

        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request.")) .build();
    }

}