package co.s4n.dronedeliverysystem.models;

public class Owner {
    private long id;
    private String name;

    public Owner() {
        this.id = 1;
        this.name = "Owner";
    }

    public Owner(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
