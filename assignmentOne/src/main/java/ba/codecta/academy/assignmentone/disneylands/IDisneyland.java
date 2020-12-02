package ba.codecta.academy.assignmentone.disneylands;

import ba.codecta.academy.assignmentone.characters.DisneyCharacter;

import java.util.List;

public interface IDisneyland {
    public String getName();
    public String getDescription();
    public List<DisneyCharacter> getCharacters();
}
