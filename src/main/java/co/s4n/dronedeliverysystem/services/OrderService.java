package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.models.Order;

public class OrderService {
    private static OrderService orderService;

    private DroneService droneService;

    private OrderService() {
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }

    public String deliverOrderAndGetPosition(final Order order, final Drone drone) {
        droneService = DroneService.getDroneService();
        for (char aChar : order.getRoute().toCharArray()) {
            droneService.moveDrone(drone, String.valueOf(aChar));
        }
        return drone.getCurrentPosition();
    }
}
