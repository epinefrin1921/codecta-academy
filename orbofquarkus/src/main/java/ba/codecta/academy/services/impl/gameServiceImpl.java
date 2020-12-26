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
        return "Welcome to the game Orb of Quarkus. Go to POST /game and enter your information to start.";
    }

    @Override
    public List<GameDto> findAllGames() {
        List<Game> gameList = gameRepository.findAll();
        if(gameList == null || gameList.isEmpty()){
            return null;
        }
        List<GameDto> gameDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

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
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

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
        Game gameInBase = modelMapper.map(gameStart, Game.class);

       if(gameStart.getMapId()==null) {
           Random rand = new Random();
           List<GameMap> mapList = gameMapRepository.findAll();
           int random = rand.nextInt(mapList.size());
           gameInBase.setGameMap(mapList.get(random));
       } else {
           gameInBase.setGameMap(gameMapRepository.findById(gameStart.getMapId()));
       }
        gameInBase.setOutcome(Outcome.PLAYING);
        gameInBase.setLevel(gameStart.getLevel());
        gameInBase.setCharacter(character);
        gameInBase.setCurrentDungeonId(0);
        gameInBase.setHighestDungeonId(0);
        gameInBase.setScore(0);
        gameInBase.setCurrentDungeonMonsterHealth(gameInBase.getGameMap().getDungeons().get(0).getMonster().getHealth());
        gameInBase.setCurrentDungeonMonsterStrength(gameInBase.getGameMap().getDungeons().get(0).getMonster().getStrength());

        gameInBase.setPlayerNickname(gameStart.getPlayerNickname());
        gameInBase.setHealth(character.getHealth());
        gameInBase.setStrength(character.getStrength());


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

//    @Override
//    public GameDto addPowerUpToGame(Integer gameId, Integer powerUpId) {
//        PowerUp powerUp = powerUpsRepository.findById(powerUpId);
//        Game game = gameRepository.findById(gameId);
//        if(powerUp==null || game==null){
//            return null;
//        }
//        game.addPowerUp(powerUp);
//        gameRepository.save(game);
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(game, GameDto.class);
//    }

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
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Dungeon dungeonInBase = modelMapper.map(dungeon, Dungeon.class);

        Random rand = new Random();

        if(dungeon.monsterNumber()==null) {
            List<Monster> monsterList = monsterRepository.findAll();
            int random = rand.nextInt(monsterList.size());
            dungeonInBase.setMonster(monsterList.get(random));
        }
        else{
            dungeonInBase.setMonster(monsterRepository.findById(dungeon.monsterNumber()));
        }

        if(dungeon.powerNumber()==null){
            List<PowerUp> powerUpList = powerUpsRepository.findAll();
            int random = rand.nextInt(powerUpList.size());
            dungeonInBase.setPowerUp(powerUpList.get(random));
        } else {
            dungeonInBase.setPowerUp(powerUpsRepository.findById(dungeon.powerNumber()));
        }

        dungeonInBase = dungeonRepository.add(dungeonInBase);
        return modelMapper.map(dungeonInBase, DungeonDto.class);
    }

    @Override
    public DungeonDto updateDungeon(Integer id, DungeonDto dungeon) {
        Dungeon dungeonInBase = dungeonRepository.findById(id);
        if(dungeonInBase != null){
           dungeonInBase.setName(dungeon.getName());
           dungeonInBase.setDescription(dungeon.getDescription());
           if(dungeon.powerNumber()!=null){
               dungeonInBase.setPowerUp(powerUpsRepository.findById(dungeon.powerNumber()));
           }
           if(dungeon.getMonster()!=null){
               dungeonInBase.setMonster(monsterRepository.findById(dungeon.monsterNumber()));
           }
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
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        GameMap gameMapInBase = modelMapper.map(gameMap, GameMap.class);

        List<Dungeon> dungeonList = dungeonRepository.getDungeonsWithoutOrb();
        List<Dungeon> dungeonOrbList = dungeonRepository.getDungeonsWithOrb();
        Random rand = new Random();
        gameMapInBase.setDungeons(new ArrayList<>());

        if(gameMap.getDungeonsIds()==null || gameMap.getDungeonsIds().isEmpty()){
            for(int i=0;i<9;i++){
                int random = rand.nextInt(dungeonList.size());
                Dungeon randomDungeon = dungeonList.get(random);
                gameMapInBase.getDungeons().add(randomDungeon);
            }
            int random = rand.nextInt(dungeonOrbList.size());
            Dungeon randomDungeon = dungeonOrbList.get(random);
            gameMapInBase.getDungeons().add(randomDungeon);
        }
        else{
            for (Integer i:gameMap.getDungeonsIds()
                 ) {
                gameMapInBase.addDungeon(dungeonRepository.findById(i));
            }
        }

        gameMapInBase = gameMapRepository.add(gameMapInBase);
        return modelMapper.map(gameMapInBase, GameMapDto.class);
    }
    //ACTIONS
    @Override
    public GameDto usePowerUp(Integer id, PowerUpDto powerUp) {
        Game currentGame = gameRepository.findById(id);
        PowerUp powerUpUsing = powerUpsRepository.findById(powerUp.getId());

        if(currentGame.getFinishedOn()==null && currentGame.getPowerUpList().contains(powerUpUsing)){
            switch (powerUpUsing.getPurpose()){
                case HEAL:
                    Integer newHealth = currentGame.getHealth()+powerUpUsing.getStrength();
                    currentGame.setHealth(newHealth);
                    break;
                case ATTACK:
                    Integer newStrength = currentGame.getStrength()+powerUpUsing.getStrength();
                    currentGame.setStrength(newStrength);
                    break;
                case ORB:
                    currentGame.setFinishedOn(LocalDateTime.now());
                    currentGame.setOutcome(Outcome.WON);
                    currentGame.setScore(currentGame.getScore()+500); //getting 500 when level passed
                    break;
            }
            currentGame.getPowerUpList().remove(powerUpUsing);
            currentGame = gameRepository.save(currentGame);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(currentGame, GameDto.class);
        }
       else
           return null;
    }

    @Override
    public GameDto move(Integer id) {
        Game currentGame = gameRepository.findById(id);
        if(currentGame.getFinishedOn()==null && currentGame.getCurrentDungeonMonsterHealth()<=0){
            Integer currentDungeonId = currentGame.getCurrentDungeonId();

            if(currentDungeonId+1 >= currentGame.getGameMap().getDungeons().size()){
                return null;
            }
            currentGame.setCurrentDungeonId(currentDungeonId+1);

            if(currentGame.getCurrentDungeonId()>currentGame.getHighestDungeonId()){
                //we are in this dungeon first time
                //if we change dungeons, we have to change current dungeon monster's health and strength
                Dungeon newDungeon = currentGame.getGameMap().getDungeons().get(currentGame.getCurrentDungeonId());

                currentGame.setCurrentDungeonMonsterStrength(newDungeon.getMonster().getStrength());
                currentGame.setCurrentDungeonMonsterHealth(newDungeon.getMonster().getHealth());
                currentGame.setHighestDungeonId(currentGame.getCurrentDungeonId());
            } else {
                currentGame.setCurrentDungeonMonsterStrength(0);
                currentGame.setCurrentDungeonMonsterHealth(0);
            }

            currentGame = gameRepository.save(currentGame);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(currentGame, GameDto.class);
        }
        else return null;
    }

    @Override
    public GameDto goBack(Integer id) {
        Game currentGame = gameRepository.findById(id);

        if(currentGame.getFinishedOn()==null && currentGame.getCurrentDungeonMonsterHealth()<=0){
            Integer currentDungeonId = currentGame.getCurrentDungeonId();

            if(currentDungeonId<1){
                return null;
            }
            currentGame.setCurrentDungeonId(currentDungeonId-1);
            currentGame.setCurrentDungeonMonsterHealth(0);
            currentGame.setCurrentDungeonMonsterStrength(0);

            currentGame = gameRepository.save(currentGame);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(currentGame, GameDto.class);
        }
        else return null;
    }

    @Override
    public GameDto fight(Integer id) {
        Game currentGame = gameRepository.findById(id);
        Random rand = new Random();
        int playerKickBound=0;
        int monsterKickBound=0;
        switch (currentGame.getLevel()){
            case LOW:
                playerKickBound=5;
                monsterKickBound=20;
                break;
            case MEDIUM:
                playerKickBound=10;
                monsterKickBound=10;
                break;
            case HIGH:
                playerKickBound=15;
                monsterKickBound=10;
                break;
        }

        while(currentGame.getHealth()>0 && currentGame.getCurrentDungeonMonsterHealth()>0){
            //players kick
            int random = rand.nextInt(playerKickBound)+1;
            int kick = currentGame.getStrength()/random;
            currentGame.setScore(currentGame.getScore()+kick);
            currentGame.setCurrentDungeonMonsterHealth(currentGame.getCurrentDungeonMonsterHealth()-kick);

            //monster kick
            random = rand.nextInt(monsterKickBound)+1;
            currentGame.setHealth(currentGame.getHealth()-currentGame.getCurrentDungeonMonsterStrength()/random);
        }
        if(currentGame.getHealth()<=0){
            currentGame.setFinishedOn(LocalDateTime.now());
            currentGame.setOutcome(Outcome.LOST);
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(currentGame, GameDto.class);
    }

    @Override
    public GameDto flee(Integer id) {
        Game currentGame = gameRepository.findById(id);

        if(currentGame.getFinishedOn()==null && currentGame.getCurrentDungeonMonsterHealth()>0){
            Integer currentDungeonId = currentGame.getCurrentDungeonId();

            if(currentDungeonId<1){
                return null;
            }
            currentGame.setCurrentDungeonId(currentDungeonId-1);
            currentGame.setHealth(currentGame.getHealth()/2);
            currentGame.setPowerUpList(new ArrayList<>()); //resetting powerups due to fleeing
            currentGame.setScore(currentGame.getScore()-100); //taking 100 from total score when fleeing
            currentGame.setCurrentDungeonMonsterHealth(0);
            currentGame.setCurrentDungeonMonsterStrength(0);

            currentGame = gameRepository.save(currentGame);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(currentGame, GameDto.class);
        }
        else return null;    }

    @Override
    public GameDto collect(Integer id) {
        Game currentGame = gameRepository.findById(id);
        Dungeon currentDungeon = currentGame.getGameMap().getDungeons().get(currentGame.getCurrentDungeonId());
        PowerUp powerUp = currentDungeon.getPowerUp();

        if(currentGame.getFinishedOn()==null && !currentGame.getPowerUpList().contains(powerUp) && currentGame.getCurrentDungeonMonsterHealth()<=0){
            currentGame.getPowerUpList().add(powerUp);
            currentGame = gameRepository.save(currentGame);

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(currentGame, GameDto.class);
        }
        else
            return null;
    }
}
