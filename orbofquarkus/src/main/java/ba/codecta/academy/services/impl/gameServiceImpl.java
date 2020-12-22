package ba.codecta.academy.services.impl;

import ba.codecta.academy.repository.GameCharacterRepository;
import ba.codecta.academy.repository.GameRepository;
import ba.codecta.academy.repository.PowerUpsRepository;
import ba.codecta.academy.repository.entity.Game;
import ba.codecta.academy.repository.entity.GameCharacter;
import ba.codecta.academy.repository.entity.PowerUp;
import ba.codecta.academy.services.GameService;
import ba.codecta.academy.services.model.GameCharacterDto;
import ba.codecta.academy.services.model.GameDto;
import ba.codecta.academy.services.model.PowerUpDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class gameServiceImpl implements GameService {
    @Inject
    GameCharacterRepository gameCharacterRepository;

    @Inject
    GameRepository gameRepository;

    @Inject
    PowerUpsRepository powerUpsRepository;

    @Override
    public String welcome() {
        return "Welcome to the game Orb of Quarkus";
    }

    @Override
    public List<GameDto> findAllGames() {
        List<Game> gameList = gameRepository.findAll();
        if(gameList == null || gameList.isEmpty()){
            return null;
        }
        List<GameDto> gameDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Game game : gameList){
            gameDtoList.add(modelMapper.map(game, GameDto.class));
        }
        return gameDtoList;
    }

    @Override
    public GameDto findGameById(Integer id) {
        Game game = gameRepository.findById(id);
        if(game == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(game, GameDto.class);
    }

    @Override
    public GameDto addGame(GameDto game) {

        GameCharacter character = gameCharacterRepository.findById(game.getCharacterId());
        if(character == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();

        game.setCharacter(modelMapper.map(character, GameCharacterDto.class));
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Game gameInBase = modelMapper.map(game, Game.class);
        gameInBase.setLevel(game.getLevel());
        gameInBase.setCharacter(character);
        gameInBase.setPlayerNickname(game.getPlayerNickname());
        gameInBase.setHealth(character.getHealth());
        gameInBase = gameRepository.add(gameInBase);

        return modelMapper.map(gameInBase, GameDto.class);
    }

    @Override
    public GameDto updateGame(Integer id, GameDto game) {
        Game gameInBase = gameRepository.findById(id);
        if(gameInBase != null){
            gameInBase.setCharacter(gameCharacterRepository.findById(game.getId()));
            gameInBase.setLevel(game.getLevel());
            gameInBase.setPlayerNickname(game.getPlayerNickname());
            gameInBase = gameRepository.save(gameInBase);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(gameInBase, GameDto.class);
        }
        return null;
    }

    @Override
    public GameDto addPowerUpToGame(Integer gameId, Integer powerUpId) {
        PowerUp powerUp = powerUpsRepository.findById(powerUpId);
        Game game = gameRepository.findById(gameId);
        if(powerUp==null || game==null){
            return null;
        }
        game.addPowerUp(powerUp);
        gameRepository.save(game);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(game, GameDto.class);
    }

    @Override
    public List<GameCharacterDto> findAllGameCharacters() {
        List<GameCharacter> gameCharacterList = gameCharacterRepository.findAll();
        if(gameCharacterList == null || gameCharacterList.isEmpty()){
            return null;
        }
        List<GameCharacterDto> gameCharacterDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(GameCharacter gameCharacter : gameCharacterList){
            gameCharacterDtoList.add(modelMapper.map(gameCharacter, GameCharacterDto.class));
        }
        return gameCharacterDtoList;
    }

    @Override
    public GameCharacterDto findGameCharacterById(Integer id) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(id);
        if(gameCharacter == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(gameCharacter, GameCharacterDto.class);
    }

    @Override
    public GameCharacterDto addGameCharacter(GameCharacterDto gameCharacter) {
        ModelMapper modelMapper = new ModelMapper();
        GameCharacter gameCharacterInBase = modelMapper.map(gameCharacter, GameCharacter.class);
        gameCharacterInBase = gameCharacterRepository.add(gameCharacterInBase);
        return modelMapper.map(gameCharacterInBase, GameCharacterDto.class);
    }

    @Override
    public GameCharacterDto updateGameCharacter(Integer id, GameCharacterDto gameCharacter) {
        return null;
    }

    @Override
    public List<PowerUpDto> findAllPowerUps() {
        List<PowerUp> powerUpList = powerUpsRepository.findAll();
        if(powerUpList == null || powerUpList.isEmpty()){
            return null;
        }
        List<PowerUpDto> powerUpDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(PowerUp powerUp : powerUpList){
            powerUpDtos.add(modelMapper.map(powerUp, PowerUpDto.class));
        }
        return powerUpDtos;
    }

    @Override
    public PowerUpDto findPowerUpById(Integer id) {
        PowerUp powerUp = powerUpsRepository.findById(id);
        if(powerUp == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(powerUp, PowerUpDto.class);
    }

    @Override
    public PowerUpDto addPowerUp(PowerUpDto powerUp) {
        ModelMapper modelMapper = new ModelMapper();
        PowerUp powerUpInBase = modelMapper.map(powerUp, PowerUp.class);
        powerUpInBase = powerUpsRepository.add(powerUpInBase);
        return modelMapper.map(powerUpInBase, PowerUpDto.class);
    }

    @Override
    public PowerUpDto updatePowerUp(Integer id, PowerUpDto powerUp) {
        return null;
    }
}
