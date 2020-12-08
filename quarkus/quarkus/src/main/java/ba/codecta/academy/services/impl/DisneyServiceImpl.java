package ba.codecta.academy.services.impl;

import ba.codecta.academy.model.DisneyCharacter;
import ba.codecta.academy.model.WelcomeMessage;
import ba.codecta.academy.repository.DisneyCharacterRepository;
import ba.codecta.academy.services.DisneyService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class DisneyServiceImpl implements DisneyService {

    @Inject
    DisneyCharacterRepository disneyCharacterRepository;

    @Override
    public WelcomeMessage welcome() {
        return new WelcomeMessage("Welcome to the DisneyLand!", "Here you can have so much fun!", "From 8 to 16");
    }

    @Override
    public List<DisneyCharacter> findAllCharacters() {
        return disneyCharacterRepository.findAll();
    }

    @Override
    public DisneyCharacter findCharacterById(Integer id) {
        return disneyCharacterRepository.findById(id);
    }

    @Override
    public DisneyCharacter addCharacter(DisneyCharacter character) {
        return disneyCharacterRepository.add(character);
    }

    @Override
    public DisneyCharacter updateCharacter(Integer id, DisneyCharacter character) {
        return disneyCharacterRepository.update(id, character);
    }
}
