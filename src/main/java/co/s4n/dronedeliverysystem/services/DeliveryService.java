package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Delivery;

public class DeliveryService {
    private OrderService orderService;

    public String executeDelivery(final Delivery delivery) {
        final StringBuilder fullPath = new StringBuilder();
        orderService = new OrderService();
        delivery.getOrders().forEach(order -> {
            fullPath.append(orderService.deliverOrderAndGetPosition(order, delivery.getDrone()));
        });
        return fullPath.toString();
    }
}
