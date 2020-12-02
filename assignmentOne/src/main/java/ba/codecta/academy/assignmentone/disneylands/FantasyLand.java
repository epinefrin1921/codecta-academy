package ba.codecta.academy.assignmentone.disneylands;

import ba.codecta.academy.assignmentone.characters.DisneyCharacter;

import java.util.ArrayList;
import java.util.List;

public class FantasyLand implements IDisneyland {
    private List<DisneyCharacter> karakteri;
    public FantasyLand(){
        karakteri = new ArrayList<DisneyCharacter>();
    }
    public void addCharacter(DisneyCharacter karakter){
        this.karakteri.add(karakter);
    }
    @Override
    public List<DisneyCharacter> getCharacters(){
        return  karakteri;
    }
    @Override
    public String getName(){
        return "FantasyLand";
    }
    @Override
    public String getDescription(){
        return "Fantasyland is one of the \"themed lands\" at all of the Magic Kingdom-style parks run by The Walt Disney "
                + "Company around the world. Each Fantasyland has a castle, as well as several gentle rides themed after Disney movies.";
    }
}
