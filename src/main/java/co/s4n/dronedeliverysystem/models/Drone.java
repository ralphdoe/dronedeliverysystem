package co.s4n.dronedeliverysystem.models;

import co.s4n.dronedeliverysystem.util.Cardinality;

public class Drone {
    private long id;
    private int x;
    private int y;
    private Cardinality cardinality;

    public Drone(long id, int x, int y, Cardinality cardinality) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.cardinality = cardinality;
    }

    public Drone() {
        this.id = 1;
        this.x = 0;
        this.y = 0;
        this.cardinality = Cardinality.NORTH;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getId() {
        return id;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrentPosition() {
        return "(" + x +
                "," + y +
                ") dirección " + cardinality.getName() + "\n";
    }
}
