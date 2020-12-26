package ba.codecta.academy.services.model;

public class DungeonDto {
    private Integer id;
    private MonsterDto monster;
    private Integer monsterId;
    private String description;
    private String name;
    private PowerUpDto powerUp;
    private Integer powerUpId;

    public Integer powerNumber() {
        return powerUpId;
    }

    public void setPowerUpId(Integer powerUpId) {
        this.powerUpId = powerUpId;
    }

    public Integer getId() {
        return id;
    }

    public Integer monsterNumber() {
        return monsterId;
    }

    public void setMonsterId(Integer monsterId) {
        this.monsterId = monsterId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MonsterDto getMonster() {
        return monster;
    }

    public void setMonster(MonsterDto monster) {
        this.monster = monster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PowerUpDto getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUpDto powerUp) {
        this.powerUp = powerUp;
    }
}
