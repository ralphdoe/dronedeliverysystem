package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.models.Order;
import co.s4n.dronedeliverysystem.util.Cardinality;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {
    private Drone defaultDrone;
    private Order order;
    private OrderService orderService;

    @Before
    public void init() {
        defaultDrone = new Drone();
        order = new Order();
        orderService = new OrderService();
    }

    @Test
    public void deliverSampleFromDroneAtDefaultPositionBaseCaseTest() {
        order.setId(1);
        order.setRoute("AAAAIAA");
        String position = orderService.deliverOrderAndGetPosition(order, defaultDrone);
        assertEquals("(-2,4) dirección Occidente\n", position);
    }

    @Test
    public void deliverFromDroneAtDifferentPositionDDDAIADSecondBaseCaseTest() {
        order.setId(1);
        order.setRoute("DDDAIAD");
        final Drone drone = new Drone(1, -2, 4, Cardinality.NORTH);
        final String position = orderService.deliverOrderAndGetPosition(order, drone);
        assertEquals("(-3,3) dirección Occidente\n", position);
    }

    @Test
    public void deliverFromDroneAtDifferentPositionAAIADADThirdBaseCaseTest() {
        order.setId(1);
        order.setRoute("AAIADAD");
        final Drone drone = new Drone(1, -3, 3, Cardinality.SOUTH);
        final String position = orderService.deliverOrderAndGetPosition(order, drone);
        assertEquals("(-2,0) dirección Occidente\n", position);
    }
}
