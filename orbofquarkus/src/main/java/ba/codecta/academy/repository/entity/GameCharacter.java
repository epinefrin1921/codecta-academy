package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "qoq", name="game_characters")
public class GameCharacter extends ModelObject{
    @SequenceGenerator(
            name = "gameCharacterSeq",
            sequenceName = "GAME_CHARACTER_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameCharacterSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();

    private String name;
    private String description;
    private Integer health;
    private Integer strength;

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

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
}
