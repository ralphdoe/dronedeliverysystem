package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Delivery;
import co.s4n.dronedeliverysystem.models.Order;
import co.s4n.dronedeliverysystem.models.Owner;
import co.s4n.dronedeliverysystem.util.FileManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService {
    private OrderService orderService;
    private final static Logger LOGGER = Logger.getLogger(DeliveryService.class);

    private static DeliveryService deliveryService;

    private DeliveryService() {
    }

    public static DeliveryService getDeliveryService() {
        if (deliveryService == null) {
            deliveryService = new DeliveryService();
        }
        return deliveryService;
    }

    public void executeMassiveDeliveriesAndExport(final String resources) {
        final List<String> filesFromFolder = FileManager.getFilesFromFolder(resources + "/input");
        for (final String fileName : filesFromFolder) {
            LOGGER.info("Starting Orchestration Process!");
            final Delivery delivery = generateDeliveryFromFile(fileName);
            LOGGER.info("Starting Delivery!");
            final String result = executeDelivery(delivery);
            long droneId = delivery.getDrone().getId();
            LOGGER.info("Generting Files!");
            FileManager.writeFiles(result, resources + "output/out" + (droneId < 10 ? "0" + droneId : droneId) + ".txt");
            LOGGER.info("Orchestration Process Finished!");
        }
    }

    public Delivery generateDeliveryFromFile(final String filePath) {
        final Delivery delivery = new Delivery();
        final List<String> routes = FileManager.readLinesFromFile(filePath);
        int orderCount = 1;
        final List<Order> orders = new ArrayList<>();
        for (final String route : routes) {
            final Order orderObject = new Order();
            orderObject.setRoute(route);
            orderObject.setId(orderCount);
            orderCount++;
            orders.add(orderObject);
        }

        delivery.setOrders(orders);
        delivery.setOwner(new Owner());
        delivery.setDrone(DroneService.getDroneService().getDroneFromFilePath(filePath).get());

        return delivery;
    }

    public String executeDelivery(final Delivery delivery) {
        orderService = OrderService.getOrderService();
        final StringBuilder fullPath = new StringBuilder();
        delivery.getOrders().forEach(order -> fullPath.append(orderService.deliverOrderAndGetPosition(order, delivery.getDrone())));
        return fullPath.toString();
    }
}
