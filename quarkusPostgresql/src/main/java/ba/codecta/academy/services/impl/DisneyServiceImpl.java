package ba.codecta.academy.services.impl;

import ba.codecta.academy.model.DisneyCharacter;
import ba.codecta.academy.model.WelcomeMessage;
import ba.codecta.academy.repository.DisneyCharacterRepository;
import ba.codecta.academy.services.DisneyService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class DisneyServiceImpl implements DisneyService {

    @Inject
    DisneyCharacterRepository disneyCharacterRepository;

    @Override
    public WelcomeMessage welcome() {
        return new WelcomeMessage("Welcome Lands of Disneyland!", "Here you can find....", "Everyday from 8 am to 10 pm");
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
        DisneyCharacter disneyCharacter = disneyCharacterRepository.findById(id);
        if(disneyCharacter != null) {
            disneyCharacter.setGreeting(character.getGreeting());
            disneyCharacter.setName(character.getName());
            return disneyCharacterRepository.save(disneyCharacter);
        }
        return null;
    }
}