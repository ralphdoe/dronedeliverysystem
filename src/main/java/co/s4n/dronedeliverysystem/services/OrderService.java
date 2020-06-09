package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.models.Order;

public class OrderService {
    private static OrderService orderService;

    private DroneService droneService;

    private OrderService() {
    }

    public synchronized static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }

    public Drone deliverOrder(final Order order, final Drone drone) {
        droneService = DroneService.getInstance();
        for (char routeMove : order.getRoute().toCharArray()) {
            droneService.moveDrone(drone, String.valueOf(routeMove));
        }
        return drone;
    }
}
