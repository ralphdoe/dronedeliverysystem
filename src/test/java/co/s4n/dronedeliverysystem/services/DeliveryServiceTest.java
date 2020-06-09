package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Delivery;
import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.models.Order;
import co.s4n.dronedeliverysystem.models.Owner;
import co.s4n.dronedeliverysystem.util.FileManager;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeliveryServiceTest {

    private static final String BASECASE_RESOURCES = "src/test/resources/baseCase/";
    private static final String BASECASE_OUTPUT = "src/test/resources/baseCase/output/";
    private static final String TWO_DELIVERIES_RESOURCES = "src/test/resources/twoDeliveries/";
    private static final String TWO_DELIVERIES_OUTPUT = "src/test/resources/twoDeliveries/output/";

    private Drone defaultDrone;
    private DeliveryService deliveryService;

    @Before
    public void init() {
        defaultDrone = new Drone();
        deliveryService = DeliveryService.getDeliveryService();
    }

    @Test
    public void generateBaseCaseDeliveryTest() {
        final String filePath = BASECASE_RESOURCES + "input/in01.txt";
        final Delivery delivery = deliveryService.generateDeliveryFromFile(filePath);
        assertEquals(3, delivery.getOrders().size());
        assertEquals("AAAAIAA", delivery.getOrders().get(0).getRoute());
        assertEquals("DDDAIAD", delivery.getOrders().get(1).getRoute());
        assertEquals("AAIADAD", delivery.getOrders().get(2).getRoute());
        assertEquals(1, delivery.getDrone().getId());
    }

    @Test
    public void executeOneDeliveryBaseCaseAndExportTest() throws IOException {
        deliveryService.executeMassiveDeliveriesAndExport(BASECASE_RESOURCES);
        final List<String> filesFromFolder = FileManager.getFilesFromFolder(BASECASE_OUTPUT);
        final String filePath = filesFromFolder.get(0);
        FileManager.readLinesFromFile(filePath);
        assertEquals(1, filesFromFolder.size());
        assertEquals("src/test/resources/baseCase/output/out01.txt", filePath);
        FileManager.cleanUpFolder(BASECASE_OUTPUT);
    }

    @Test
    public void executeTwoDeliveriesAndExportTest() {
        deliveryService.executeMassiveDeliveriesAndExport(TWO_DELIVERIES_RESOURCES);
        final List<String> filesFromFolder = FileManager.getFilesFromFolder(TWO_DELIVERIES_OUTPUT);
        FileManager.readLinesFromFile(filesFromFolder.get(0));
        assertEquals(2, filesFromFolder.size());
        assertEquals("src/test/resources/twoDeliveries/output/out01.txt", filesFromFolder.get(1));
        assertEquals("src/test/resources/twoDeliveries/output/out02.txt", filesFromFolder.get(0));
        FileManager.cleanUpFolder(TWO_DELIVERIES_OUTPUT);
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
        final Delivery delivery = new Delivery();
        delivery.setDrone(defaultDrone);
        delivery.setOwner(new Owner());
        delivery.setOrders(orders);

        String fullPath = deliveryService.executeDelivery(delivery);
        assertEquals("(-2,4) dirección Occidente\n" +
                "(-1,3) dirección Sur\n" +
                "(0,0) dirección Occidente\n", fullPath);
    }
}
