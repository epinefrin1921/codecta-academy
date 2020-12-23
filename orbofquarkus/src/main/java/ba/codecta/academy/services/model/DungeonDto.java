package ba.codecta.academy.services.model;

public class DungeonDto {
    private Integer id;
    private MonsterDto monster;
    private String description;
    private String name;
    private PowerUpDto powerUp;

    public Integer getId() {
        return id;
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
