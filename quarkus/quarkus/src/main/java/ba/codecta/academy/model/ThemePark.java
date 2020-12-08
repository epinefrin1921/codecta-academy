package ba.codecta.academy.model;

public class ThemePark extends ModelObject{
    private String location;
    private String attractions;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttractions() {
        return attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }
}
