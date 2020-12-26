package ba.codecta.academy.services;

import ba.codecta.academy.services.model.*;

import java.util.List;

public interface GameService {
    String welcome();

    //for game entity
    List<GameDto> findAllGames();
    GameDto findGameById(Integer id);
    GameDto addGame(GameStartDto game);
    GameDto updateGame(Integer id, GameStartDto game);
//    GameDto addPowerUpToGame(Integer gameIdInteger, Integer powerUpId);

    //for character entity
    List<GameCharacterDto> findAllGameCharacters();
    GameCharacterDto findGameCharacterById(Integer id);
    GameCharacterDto addGameCharacter(GameCharacterDto gameCharacter);
    GameCharacterDto updateGameCharacter(Integer id, GameCharacterDto gameCharacter);

    //for powerup entity
    List<PowerUpDto> findAllPowerUps();
    PowerUpDto findPowerUpById(Integer id);
    PowerUpDto addPowerUp(PowerUpDto powerUp);
    PowerUpDto updatePowerUp(Integer id, PowerUpDto powerUp);

    //for moster entity
    List<MonsterDto> findAllMonsters();
    MonsterDto findMonsterById(Integer id);
    MonsterDto addMonster(MonsterDto monster);
    MonsterDto updateMonster(Integer id, MonsterDto monster);

    //for dungeon entity
    List<DungeonDto> findAllDungeons();
    DungeonDto findDungeonById(Integer id);
    DungeonDto addDungeon(DungeonDto dungeon);
    DungeonDto updateDungeon(Integer id, DungeonDto dungeon);

    //for game map entity, no update functionalities
    List<GameMapDto> findAllGameMaps();
    GameMapDto findGameMapById(Integer id);
    GameMapDto addGameMap(GameMapDto gameMap);

    GameDto usePowerUp(Integer id, PowerUpDto powerUp);
    GameDto move(Integer id);
    GameDto goBack(Integer id);
    GameDto fight(Integer id);
    GameDto flee(Integer id);
    GameDto collect(Integer id);

}
