package ba.codecta.academy.services.model;

import java.util.List;

public class GameMapDto {
    private Integer id;
    private String description;
    private String name;
    private List<DungeonDto> dungeons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<DungeonDto> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<DungeonDto> dungeonList) {
        this.dungeons = dungeonList;
    }
}
