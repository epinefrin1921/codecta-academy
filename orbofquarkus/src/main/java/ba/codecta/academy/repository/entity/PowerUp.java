package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "qoq", name="power_ups")
public class PowerUp extends ModelObject{
    @SequenceGenerator(
            name = "powerUpSeq",
            sequenceName = "POWERUP_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "powerUpSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private Integer strength;
    private Purpose purpose;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "powerUpList")
    private List<Game> games = new ArrayList<>();

    @OneToMany(mappedBy = "powerUp", fetch = FetchType.LAZY)
    private List<Dungeon> dungeons = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
