package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "qoq", name="game_map")
public class GameMap extends ModelObject{

    @SequenceGenerator(
            name = "gameMapSeq",
            sequenceName = "GAME_MAP_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameMapSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "gameMap", fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            schema = "qoq",
            name = "GAME_MAP_DUNGEON",
            joinColumns = @JoinColumn(name = "GAME_MAP_ID"),
            inverseJoinColumns = @JoinColumn(name = "DUNGEON_ID")
    )
    private List<Dungeon> dungeons = new ArrayList<>();


    public void setId(Integer id) {
        this.id = id;
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Dungeon> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }

    public void addDungeon(Dungeon dungeon){
        this.dungeons.add(dungeon);
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
