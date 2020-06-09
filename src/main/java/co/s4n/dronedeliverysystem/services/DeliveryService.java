package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Delivery;
import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.models.Order;
import co.s4n.dronedeliverysystem.models.Owner;
import co.s4n.dronedeliverysystem.util.FileManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService {
    private OrderService orderService;

    private static final Logger LOGGER = Logger.getLogger(DeliveryService.class);
    private static final String OUT_OF_BOUNDARIES = "Fuera de Cobertura";
    private static DeliveryService deliveryService;

    private DeliveryService() {
    }

    public synchronized static DeliveryService getInstance() {
        if (deliveryService == null) {
            deliveryService = new DeliveryService();
        }
        return deliveryService;
    }

    public void executeMassiveDeliveriesAndExport(final String resources) {
        final List<String> filesFromFolder = FileManager.getFilesFromFolder(resources + "/input");
        for (final String filePath : filesFromFolder) {
            final Delivery delivery = generateDelivery(getRoutesFromFile(filePath), getDroneFromPath(filePath));
            final String result = executeDelivery(delivery);
            writeFile(result, delivery.getDrone(), resources);
        }
    }

    public void writeFile(final String result, final Drone drone, final String resources) {
        long droneId = drone.getId();
        FileManager.writeFiles(result, resources + "output/out" + (droneId < 10 ? "0" + droneId : droneId) + ".txt");
    }

    public Drone getDroneFromPath(final String filePath) {
        return DroneService.getInstance().getDroneFromFilePath(filePath).get();
    }

    public List<String> getRoutesFromFile(final String filePath) {
        return FileManager.readLinesFromFile(filePath);
    }


    public Delivery generateDelivery(final List<String> routes, final Drone drone) {
        final Delivery delivery = new Delivery();
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
        delivery.setDrone(drone);

        return delivery;
    }

    public String executeDelivery(final Delivery delivery) {
        orderService = OrderService.getInstance();
        final StringBuilder fullPath = new StringBuilder();
        delivery.getOrders().forEach(order ->
        {
            final Drone drone = orderService.deliverOrder(order, delivery.getDrone());
            fullPath.append(DroneService.getInstance().isDroneInBoundaries(drone) ? drone.getCurrentPosition() : OUT_OF_BOUNDARIES);
        });
        return fullPath.toString();
    }
}
