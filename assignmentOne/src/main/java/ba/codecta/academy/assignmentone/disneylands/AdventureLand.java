package ba.codecta.academy.assignmentone.disneylands;

import ba.codecta.academy.assignmentone.characters.DisneyCharacter;

import java.util.ArrayList;
import java.util.List;

public class AdventureLand implements IDisneyland {
    private List<DisneyCharacter> karakteri;
    public AdventureLand(){
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
        return "AdventureLand";
    }
    @Override
    public String getDescription(){
        return "Adventureland is one of the \"themed lands\" at the many Disneyland-style theme parks run by the Walt Disney Company around the world."
                + " It is themed to resemble the remote jungles in Africa, Asia, South America, the South Pacific and the Caribbean.";
    }
}
