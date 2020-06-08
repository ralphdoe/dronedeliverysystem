package co.s4n.dronedeliverysystem.models;

import java.util.List;

public class Delivery {
    private Owner owner;
    private List<Order> orders;
    private Drone drone;

    public Delivery() {
    }

    public Delivery(final Owner owner, final List<Order> orders, final Drone drone) {
        this.owner = owner;
        this.orders = orders;
        this.drone = drone;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(final Owner owner) {
        this.owner = owner;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(final Drone drone) {
        this.drone = drone;
    }
}
