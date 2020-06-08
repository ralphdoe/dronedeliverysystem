package co.s4n.dronedeliverysystem.models;

public class Order {
    private long id;
    private String route;

    public Order() {
    }

    public Order(long id, String route) {
        this.id = id;
        this.route = route;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
