package co.s4n.dronedeliverysystem.util;

public enum Cardinality {
    NORTH("Norte"),
    SOUTH("Sur"),
    EAST("Oriente"),
    WEST("Occidente");

    private String name;

    Cardinality(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
