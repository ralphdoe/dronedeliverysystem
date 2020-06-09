package co.s4n.dronedeliverysystem;

import co.s4n.dronedeliverysystem.services.DeliveryService;

public class DroneDeliverySystem {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(String args[]) {
        final DeliveryService deliveryService = DeliveryService.getInstance();
        deliveryService.executeMassiveDeliveriesAndExport(RESOURCES);
    }
}
