package ba.codecta.academy.model;

import javax.persistence.*;

@Entity
@Table(schema = "codecta", name = "DISNEY_CHARACTER")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class DisneyCharacter extends  ModelObject{
    @SequenceGenerator(
            name = "disneyCharacterSeq",
            sequenceName = "DISNEY_CHARACTER_SEQ",
            schema = "codecta",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disneyCharacterSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    private String name;
    private String greeting;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}