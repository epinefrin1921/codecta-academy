package ba.codecta.academy.services;

import ba.codecta.academy.services.model.GameCharacterDto;
import ba.codecta.academy.services.model.GameDto;
import ba.codecta.academy.services.model.PowerUpDto;

import java.util.List;

public interface GameService {
    String welcome();

    List<GameDto> findAllGames();
    GameDto findGameById(Integer id);
    GameDto addGame(GameDto game);
    GameDto updateGame(Integer id, GameDto game);
    GameDto addPowerUpToGame(Integer gameIdInteger, Integer powerUpId);


    List<GameCharacterDto> findAllGameCharacters();
    GameCharacterDto findGameCharacterById(Integer id);
    GameCharacterDto addGameCharacter(GameCharacterDto gameCharacter);
    GameCharacterDto updateGameCharacter(Integer id, GameCharacterDto gameCharacter);

    List<PowerUpDto> findAllPowerUps();
    PowerUpDto findPowerUpById(Integer id);
    PowerUpDto addPowerUp(PowerUpDto powerUp);
    PowerUpDto updatePowerUp(Integer id, PowerUpDto powerUp);
}
