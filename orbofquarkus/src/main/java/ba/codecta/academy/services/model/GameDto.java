package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Level;
import ba.codecta.academy.repository.entity.Outcome;

import java.util.ArrayList;
import java.util.List;

public class GameDto {
    private Integer id;
    private String playerNickname;
    private Level level;
    private GameCharacterDto character;
    private Integer score;
    private Outcome outcome;
    private List<PowerUpDto> powerUpList;
    private Integer health;
    private Integer strength;
    private Integer currentDungeonMonsterHealth;
    private Integer currentDungeonMonsterStrength;
    private Integer highestDungeonId;
    private Integer currentDungeonId;
    private GameMapDto gameMap;

    public Integer getCurrentDungeonMonsterHealth() {
        return currentDungeonMonsterHealth;
    }

    public void setCurrentDungeonMonsterHealth(Integer currentDungeonMonsterHealth) {
        this.currentDungeonMonsterHealth = currentDungeonMonsterHealth;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public Integer getCurrentDungeonMonsterStrength() {
        return currentDungeonMonsterStrength;
    }

    public void setCurrentDungeonMonsterStrength(Integer currentDungeonMonsterStrength) {
        this.currentDungeonMonsterStrength = currentDungeonMonsterStrength;
    }

    public Integer getHighestDungeonId() {
        return highestDungeonId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setHighestDungeonId(Integer highestDungeonId) {
        this.highestDungeonId = highestDungeonId;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Integer getCurrentDungeonId() {
        return currentDungeonId;
    }

    public void setCurrentDungeonId(Integer currentDungeonId) {
        this.currentDungeonId = currentDungeonId;
    }

    public List<DungeonDto> getGameMap() {
        List<DungeonDto> newMap = new ArrayList<>();
        for(int i=0;i<highestDungeonId+1;i++){
            newMap.add(gameMap.getDungeons().get(i));
        }
        return newMap;
    }

    public void setGameMap(GameMapDto gameMap) {
        this.gameMap = gameMap;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public GameCharacterDto getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacterDto character) {
        this.character = character;
    }

    public List<PowerUpDto> getPowerUpList() {
        return powerUpList;
    }

    public void setPowerUpList(List<PowerUpDto> powerUpList) {
        this.powerUpList = powerUpList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String name) {
        this.playerNickname = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
