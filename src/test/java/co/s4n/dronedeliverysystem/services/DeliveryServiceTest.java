package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Delivery;
import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.models.Order;
import co.s4n.dronedeliverysystem.models.Owner;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeliveryServiceTest {
    private Drone defaultDrone;
    private Delivery delivery;
    private Owner owner;
    private DeliveryService deliveryService;

    @Before
    public void init() {
        defaultDrone = new Drone();
        deliveryService = new DeliveryService();
    }

    @Test
    public void executeDeliverySampleCaseTest() {
        final List<Order> orders = new ArrayList<>();
        final Order firstOrder = new Order(1, "AAAAIAA");
        final Order secondOrder = new Order(2, "DDDAIAD");
        final Order thirdOrder = new Order(3, "AAIADAD");
        orders.add(firstOrder);
        orders.add(secondOrder);
        orders.add(thirdOrder);
        delivery = new Delivery();
        delivery.setDrone(defaultDrone);
        delivery.setOwner(new Owner());
        delivery.setOrders(orders);

        String fullPath = deliveryService.executeDelivery(delivery);
        assertEquals("(-2,4) dirección Occidente\n" +
                "(-1,3) dirección Sur\n" +
                "(0,0) dirección Occidente\n", fullPath);
    }
}
