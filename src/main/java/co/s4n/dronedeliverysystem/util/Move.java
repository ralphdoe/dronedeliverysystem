package co.s4n.dronedeliverysystem.util;

public enum Move {
    RIGHT("D"),
    LEFT("I"),
    FORWARD("A");

    private String id;

    Move(final String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
