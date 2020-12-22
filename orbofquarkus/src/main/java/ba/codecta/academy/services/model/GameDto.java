package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Level;

import java.util.List;

public class GameDto {
    private Integer id;
    private String playerNickname;
    private Level level;
    private Integer characterId;
    private GameCharacterDto character=null;
    private List<PowerUpDto> powerUpList;


    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer character) {
        this.characterId = character;
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
