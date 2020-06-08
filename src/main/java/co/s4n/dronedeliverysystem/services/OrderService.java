package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.models.Order;

public class OrderService {
    private DroneService droneService;

    public String deliverOrderAndGetPosition(final Order order, final Drone drone) {
        droneService = new DroneService();
        for (char aChar : order.getRoute().toCharArray()) {
            droneService.moveDrone(drone, String.valueOf(aChar));
        }
        return drone.getCurrentPosition();
    }
}
