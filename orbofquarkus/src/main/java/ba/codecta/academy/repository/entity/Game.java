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

    @ManyToOne
    private GameCharacter character;
    private Integer health;


    private LocalDateTime finishedOn; //if null, game is still active and can be continued
    private Level level;

    @ManyToMany
    @JoinTable(
            schema = "qoq",
            name = "GAME_CHARACTER_POWERUPS",
            joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "POWERUPS_ID")
    )
    private List<PowerUp> powerUpList = new ArrayList<>();


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHealth() {
        return this.health;
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
