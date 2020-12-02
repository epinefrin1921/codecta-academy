package ba.codecta.academy.assignmentone.disneylands;

import ba.codecta.academy.assignmentone.characters.DisneyCharacter;

import java.util.ArrayList;
import java.util.List;

public class MickeyTown implements IDisneyland {
    private List<DisneyCharacter> karakteri;
    public MickeyTown(){
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
    public String getName() {
        return "Mickey Town";
    }

    @Override
    public String getDescription() {
        return "Mickey's Toontown is a themed land at Disneyland and Tokyo Disneyland, two theme parks operated by Walt Disney Parks & Resorts."
                + " At Tokyo Disneyland, this land is named Toontown";
    }
}
