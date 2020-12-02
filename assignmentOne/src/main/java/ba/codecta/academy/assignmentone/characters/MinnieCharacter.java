package ba.codecta.academy.assignmentone.characters;

public class MinnieCharacter extends DisneyCharacter {
    public MinnieCharacter()
    {
        imageFilename = "minnie.png";
    }
    @Override
    public String getName() {
        return "Mini";
    }

    @Override
    public String greetings() {
        return "Hallo my beautiful girl! Happy you have choose " + this.getName() + " to play with! Let's start...!";
    }

}
