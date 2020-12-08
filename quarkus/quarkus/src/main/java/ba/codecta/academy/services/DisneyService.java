package ba.codecta.academy.services;

import ba.codecta.academy.model.DisneyCharacter;
import ba.codecta.academy.model.WelcomeMessage;

import java.util.List;

public interface DisneyService {
    WelcomeMessage welcome();

    List<DisneyCharacter> findAllCharacters();
    DisneyCharacter findCharacterById(Integer id);
    DisneyCharacter addCharacter(DisneyCharacter character);
    DisneyCharacter updateCharacter(Integer id, DisneyCharacter character);
}
