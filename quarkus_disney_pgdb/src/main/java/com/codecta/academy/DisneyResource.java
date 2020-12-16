package com.codecta.academy;

import com.codecta.academy.services.DisneyService;
import com.codecta.academy.services.model.CharacterDto;
import com.codecta.academy.services.model.ParkDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Path("/disney")
public class DisneyResource {

    public class Error {
        public String code;
        public String description;

        public Error(String code, String description) {
            this.code = code;
            this.description = description;
        }
    }

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
        List<CharacterDto> characterList = disneyService.findAllCharacters();
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
        CharacterDto character = disneyService.findCharacterById(id);
        if(character == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(character).build();
    }

    @PUT
    @Path("/characters/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCharacterById(@PathParam("id") Integer id, CharacterDto disneyCharacter)
    {
        CharacterDto character = disneyService.updateCharacter(id, disneyCharacter);
        if(character == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(character).build();
    }

    @POST
    @Path("/characters")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createCharacter(CharacterDto character, @Context UriInfo uriInfo)
    {
        CharacterDto disneyCharacter = disneyService.addCharacter(character);
        if(disneyCharacter != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(disneyCharacter.getId()));
            return Response.created(uriBuilder.build()).entity(disneyCharacter).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown theme park in request.")) .build();
    }

    @POST
    @Path("/themeparks")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createThemePark(ParkDto park, @Context UriInfo uriInfo)
    {
        ParkDto themePark = disneyService.addThemePark(park);
        if(themePark != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(themePark.getId()));
            return Response.created(uriBuilder.build()).entity(themePark).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("TPD-001", "Invalid request.")).build();
    }
    @GET
    @Path("/themeparks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllThemeParks()
    {
        List<ParkDto> themeParkList = disneyService.findAllThemeParks();
        if(themeParkList == null || themeParkList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(themeParkList).build();
    }

    @GET
    @Path("/themeparks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThemeParkById(@PathParam("id") Integer id)
    {
        ParkDto themePark = disneyService.findThemeParkById(id);
        if(themePark == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(themePark).build();
    }

    @GET
    @Path("/themeparks/{id}/characters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharactersByParkId(@PathParam("id") Integer id) {
        List<CharacterDto> characterDtoList = disneyService.findCharacterByParkId(id);
        if(characterDtoList == null) {
            return Response.noContent().build();
        }
        return Response.ok(characterDtoList).build();
    }

    @GET
    @Path("/themeparks/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharactersByParkName(@QueryParam("name") String name) {
        List<ParkDto> parkDtoList = disneyService.findParkByCharacterName(name);
        if(parkDtoList == null) {
            return Response.noContent().build();
        }
        return Response.ok(parkDtoList).build();
    }
    @PUT
    @Path("/themeparks/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateThemeParkById(@PathParam("id") Integer id, ParkDto park, @Context UriInfo uriInfo){
        ParkDto themePark = disneyService.updateThemePark(id, park);
        if(themePark != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(themePark.getId()));
            return Response.ok(uriBuilder.build()).entity(themePark).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/characters/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharactersByNameAndGreeting(@QueryParam("name") String name, @QueryParam("greeting") String greeting) throws UnsupportedEncodingException {
        URLEncoder.encode(greeting, StandardCharsets.UTF_8);
        List<CharacterDto> characterDtoList = null;
        System.out.println(name + greeting);
//        if(name!=null && greeting != null){
//            characterDtoList = disneyService.findCharactersByCharacterNameAndGreeting(name, greeting);
//        }
//        else if(name==null && greeting != null){
//            characterDtoList = disneyService.findCharactersByCharacterGreeting(greeting);
//        }
//        else if(name!=null && greeting == null){
//            characterDtoList = disneyService.findCharactersByCharacterName(name);
//        }
        if(characterDtoList == null) {
            return Response.noContent().build();
        }
        return Response.ok(characterDtoList).build();
    }
}