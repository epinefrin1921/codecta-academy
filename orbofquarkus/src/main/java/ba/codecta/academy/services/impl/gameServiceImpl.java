package ba.codecta.academy.services.impl;

import ba.codecta.academy.repository.*;
import ba.codecta.academy.repository.entity.*;
import ba.codecta.academy.services.GameService;
import ba.codecta.academy.services.model.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@Transactional
public class gameServiceImpl implements GameService {
    @Inject
    GameCharacterRepository gameCharacterRepository;

    @Inject
    GameRepository gameRepository;

    @Inject
    PowerUpsRepository powerUpsRepository;

    @Inject
    DungeonRepository dungeonRepository;

    @Inject
    GameMapRepository gameMapRepository;

    @Inject
    MonsterRepository monsterRepository;

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
    public GameDto addGame(GameStartDto gameStart) {

        GameCharacter character = gameCharacterRepository.findById(gameStart.getCharacterId());
        if(character == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Random rand = new Random();

        List<GameMap> mapList = gameMapRepository.findAll();

        int random = rand.nextInt(mapList.size());

        Game gameInBase = modelMapper.map(gameStart, Game.class);
        gameInBase.setLevel(gameStart.getLevel());
        gameInBase.setCharacter(character);
        gameInBase.setGameMap(mapList.get(random));
        gameInBase.setPlayerNickname(gameStart.getPlayerNickname());
        gameInBase.setHealth(character.getHealth());
        gameInBase = gameRepository.add(gameInBase);

        return modelMapper.map(gameInBase, GameDto.class);
    }

    @Override
    public GameDto updateGame(Integer id, GameStartDto game) {
        Game gameInBase = gameRepository.findById(id);
        if(gameInBase != null){
            gameInBase.setCharacter(gameCharacterRepository.findById(game.getCharacterId()));
            gameInBase.setLevel(game.getLevel());
            gameInBase.setPlayerNickname(game.getPlayerNickname());
            gameInBase.setModifiedOn(LocalDateTime.now());
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
        GameCharacter gameCharacterInBase = gameCharacterRepository.findById(id);
        if(gameCharacterInBase != null){
            gameCharacterInBase.setName(gameCharacter.getName());
            gameCharacterInBase.setHealth(gameCharacter.getHealth());
            gameCharacterInBase.setDescription(gameCharacter.getDescription());
            gameCharacterInBase.setModifiedOn(LocalDateTime.now());
            gameCharacterInBase.setStrength(gameCharacter.getStrength());
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(gameCharacterInBase, GameCharacterDto.class);
        }
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
        PowerUp powerUpInBase = powerUpsRepository.findById(id);
        if(powerUpInBase != null){
            powerUpInBase.setDescription(powerUp.getDescription());
            powerUpInBase.setName(powerUp.getName());
            powerUpInBase.setStrength(powerUp.getStrength());
            powerUpInBase.setPurpose(powerUp.getPurpose());
            powerUpInBase.setModifiedOn(LocalDateTime.now());
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(powerUpInBase, PowerUpDto.class);
        }
        return null;
    }

    @Override
    public List<MonsterDto> findAllMonsters() {
        List<Monster> monsterList = monsterRepository.findAll();

        if(monsterList == null || monsterList.isEmpty()){
            return null;
        }
        List<MonsterDto> monsterDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Monster monster : monsterList){
            monsterDtoList.add(modelMapper.map(monster, MonsterDto.class));
        }
        return monsterDtoList;
    }

    @Override
    public MonsterDto findMonsterById(Integer id) {
        Monster monster = monsterRepository.findById(id);
        if(monster == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(monster, MonsterDto.class);
    }

    @Override
    public MonsterDto addMonster(MonsterDto monster) {
        ModelMapper modelMapper = new ModelMapper();
        Monster monsterInBase = modelMapper.map(monster, Monster.class);
        monsterInBase = monsterRepository.add(monsterInBase);
        return modelMapper.map(monsterInBase, MonsterDto.class);
    }

    @Override
    public MonsterDto updateMonster(Integer id, MonsterDto monster) {
        Monster monsterInBase = monsterRepository.findById(id);
        if(monsterInBase != null){
           monsterInBase.setStrength(monster.getStrength());
           monsterInBase.setHealth(monster.getHealth());
           monsterInBase.setDescription(monster.getDescription());
           monsterInBase.setName(monster.getName());
           monsterInBase.setModifiedOn(LocalDateTime.now());

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(monsterInBase, MonsterDto.class);
        }
        return null;
    }

    @Override
    public List<DungeonDto> findAllDungeons() {
        List<Dungeon> dungeonList = dungeonRepository.findAll();
        if(dungeonList == null || dungeonList.isEmpty()){
            return null;
        }
        List<DungeonDto> dungeonDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Dungeon dungeon : dungeonList){
            dungeonDtoList.add(modelMapper.map(dungeon, DungeonDto.class));
        }
        return dungeonDtoList;
    }

    @Override
    public DungeonDto findDungeonById(Integer id) {
        Dungeon dungeon =dungeonRepository.findById(id);
        if(dungeon == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dungeon, DungeonDto.class);
    }

    @Override
    public DungeonDto addDungeon(DungeonDto dungeon) {
        ModelMapper modelMapper = new ModelMapper();
        Dungeon dungeonInBase = modelMapper.map(dungeon, Dungeon.class);
        Random rand = new Random();

        List<Monster> monsterList =monsterRepository.findAll();
        List<PowerUp> powerUpList = powerUpsRepository.findAll();
        int random = rand.nextInt(monsterList.size());
        dungeonInBase.setMonster(monsterList.get(random));
        random = rand.nextInt(powerUpList.size());
        dungeonInBase.setPowerUp(powerUpList.get(random));
        dungeonInBase = dungeonRepository.add(dungeonInBase);
        return modelMapper.map(dungeonInBase, DungeonDto.class);
    }

    @Override
    public DungeonDto updateDungeon(Integer id, DungeonDto dungeon) {
        Dungeon dungeonInBase = dungeonRepository.findById(id);
        if(dungeonInBase != null){
           dungeonInBase.setName(dungeon.getName());
           dungeonInBase.setDescription(dungeon.getDescription());
           dungeonInBase.setModifiedOn(LocalDateTime.now());

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(dungeonInBase, DungeonDto.class);
        }
        return null;
    }

    @Override
    public List<GameMapDto> findAllGameMaps() {
        List<GameMap> gameMapList = gameMapRepository.findAll();
        if(gameMapList == null || gameMapList.isEmpty()){
            return null;
        }
        List<GameMapDto> gameMapDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(GameMap gameMap : gameMapList){
            gameMapDtoList.add(modelMapper.map(gameMap, GameMapDto.class));
        }
        return gameMapDtoList;
    }

    @Override
    public GameMapDto findGameMapById(Integer id) {
        GameMap gameMap = gameMapRepository.findById(id);
        if(gameMap == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(gameMap, GameMapDto.class);
    }

    @Override
    public GameMapDto addGameMap(GameMapDto gameMap) {
        ModelMapper modelMapper = new ModelMapper();
        GameMap gameMapInBase = modelMapper.map(gameMap, GameMap.class);
        List<Dungeon> dungeonList = dungeonRepository.getDungeonsWithoutOrb();
        Random rand = new Random();
        gameMapInBase.setDungeons(new ArrayList<>());

        for(int i=0;i<9;i++){
            int random = rand.nextInt(dungeonList.size());
            Dungeon randomDungeon = dungeonList.get(random);
            gameMapInBase.getDungeons().add(randomDungeon);
        }
        List<Dungeon> dungeonOrbList = dungeonRepository.getDungeonsWithOrb();
        int random = rand.nextInt(dungeonOrbList.size());
        Dungeon randomDungeon = dungeonOrbList.get(random);
        gameMapInBase.getDungeons().add(randomDungeon);

        gameMapInBase = gameMapRepository.add(gameMapInBase);
        return modelMapper.map(gameMapInBase, GameMapDto.class);
    }
}
