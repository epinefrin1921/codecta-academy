package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "qoq", name="game")
public class Game extends ModelObject{
    @SequenceGenerator(
            name = "gameSeq",
            sequenceName = "GAME_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    private String playerNickname;
    private Integer currentDungeonId;
    private Integer currentDungeonMonsterHealth;
    private Integer currentDungeonMonsterStrength;
    private Integer score;
    private Integer previousDungeon=-1;

    private Outcome outcome;
    @ElementCollection
    private List<Integer> dungeonsVisited = new ArrayList<>();
    @ElementCollection
    private List<Integer> dungeonsVisitedAndDefeated = new ArrayList<>();


    @ManyToOne
    private GameCharacter character;
    private Integer health;
    private Integer strength;

    private LocalDateTime finishedOn; //if null, game is still active and can be continued
    private Level level;

    @ManyToOne
    private GameMap gameMap;

    @ManyToMany
    @JoinTable(
            schema = "qoq",
            name = "GAME_CHARACTER_POWERUPS",
            joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "POWERUPS_ID")
    )
    private List<PowerUp> powerUpList = new ArrayList<>();

    public Integer getCurrentDungeonMonsterStrength() {
        return currentDungeonMonsterStrength;
    }

    public List<Integer> getDungeonsVisitedAndDefeated() {
        return dungeonsVisitedAndDefeated;
    }

    public void setDungeonsVisitedAndDefeated(List<Integer> dungeonsVisitedAndDefeated) {
        this.dungeonsVisitedAndDefeated = dungeonsVisitedAndDefeated;
    }

    public void setCurrentDungeonMonsterStrength(Integer getCurrentDungeonMonsterStrength) {
        this.currentDungeonMonsterStrength = getCurrentDungeonMonsterStrength;
    }

    public List<Integer> getDungeonsVisited() {
        return dungeonsVisited;
    }

    public void setDungeonsVisited(List<Integer> dungeonsVisited) {
        this.dungeonsVisited = dungeonsVisited;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPreviousDungeon() {
        return previousDungeon;
    }

    public void setPreviousDungeon(Integer previousDungeon) {
        this.previousDungeon = previousDungeon;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }


    public Integer getCurrentDungeonMonsterHealth() {
        return currentDungeonMonsterHealth;
    }

    public void setCurrentDungeonMonsterHealth(Integer currentDungeonMonsterHealth) {
        this.currentDungeonMonsterHealth = currentDungeonMonsterHealth;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Integer getHealth() {
        return this.health;
    }

    public Integer getCurrentDungeonId() {
        return currentDungeonId;
    }

    public void setCurrentDungeonId(Integer currentDungeon) {
        this.currentDungeonId = currentDungeon;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public List<PowerUp> getPowerUpList() {
        return powerUpList;
    }

    public void setPowerUpList(List<PowerUp> powerUpList) {
        this.powerUpList = powerUpList;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<PowerUp> getPowerUpsList() {
        return powerUpList;
    }

    public void setPowerUpsList(List<PowerUp> powerUpList) {
        this.powerUpList = powerUpList;
    }
    public void addPowerUp(PowerUp powerUp){
        powerUpList.add(powerUp);
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
