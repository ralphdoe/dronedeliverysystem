package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.util.FileManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeliveryServiceIntegrationTest {

    private static final String BASE_CASE_RESOURCES = "src/test/resources/baseCase/";
    private static final String BASE_CASE_OUTPUT = "src/test/resources/baseCase/output/";
    private static final String TWO_DELIVERIES_RESOURCES = "src/test/resources/twoDeliveries/";
    private static final String TWO_DELIVERIES_OUTPUT = "src/test/resources/twoDeliveries/output/";
    private static final String FULL_DELIVERIES_RESOURCE = "src/test/resources/fullDeliveries/";
    private static final String FULL_DELIVERIES_OUTPUT = "src/test/resources/fullDeliveries/output/";
    private static final String FOUR_ORDERS_RESOURCE = "src/test/resources/fourOrders/";
    private static final String FOUR_ORDERS_OUTPUT = "src/test/resources/fourOrders/output/";
    private static final String OUT_OF_BOUNDARIES = "src/test/resources/outOfBoundaries/";
    private static final String OUT_OF_BOUNDARIES_OUTPUT = "src/test/resources/outOfBoundaries/output/";

    private DeliveryService deliveryService;

    @Before
    public void init() {
        deliveryService = DeliveryService.getInstance();
    }

    @Test
    public void executeOneDeliveryWithBaseSampleCaseAndExportOutputFilesTest() throws IOException {
        deliveryService.executeMassiveDeliveriesAndExport(BASE_CASE_RESOURCES);
        final List<String> filesFromFolder = FileManager.getFilesFromFolder(BASE_CASE_OUTPUT);
        final String filePath = filesFromFolder.get(0);
        assertEquals(1, filesFromFolder.size());
        assertEquals("src/test/resources/baseCase/output/out01.txt", filePath);
    }

    @Test
    public void executeTwoDeliveriesAndExportOutputFilesTest() {
        deliveryService.executeMassiveDeliveriesAndExport(TWO_DELIVERIES_RESOURCES);
        final List<String> filesFromFolder = FileManager.getFilesFromFolder(TWO_DELIVERIES_OUTPUT);
        assertEquals(2, filesFromFolder.size());
        assertEquals("src/test/resources/twoDeliveries/output/out01.txt", filesFromFolder.get(1));
        assertEquals("src/test/resources/twoDeliveries/output/out02.txt", filesFromFolder.get(0));
    }

    @Test
    public void executeDeliveryWithFourOrdersTestTest() throws IOException {
        long initTime = System.currentTimeMillis();
        deliveryService.executeMassiveDeliveriesAndExport(FOUR_ORDERS_RESOURCE);
        long endTime = System.currentTimeMillis();
        long time = (endTime - initTime)/1000;

        final List<String> filesFromFolder = FileManager.getFilesFromFolder(FOUR_ORDERS_OUTPUT);
        final String filePath = filesFromFolder.get(0);
        final List<String> droneReport = FileManager.readLinesFromFile(filePath);
        assertEquals(1, filesFromFolder.size());
        assertEquals(4, droneReport.size());
        assertEquals("(-2,4) dirección Occidente", droneReport.get(0));
        assertEquals("(-1,3) dirección Sur", droneReport.get(1));
        assertEquals("(0,0) dirección Occidente", droneReport.get(2));
        assertEquals("(-4,-2) dirección Sur", droneReport.get(3));
        assertTrue(time < 1);
    }

    @Test
    public void executeFullCapacityOfDeliveryWithBaseSampleCaseAndExportOutputFiles() throws IOException {
        long initTime = System.currentTimeMillis();
        deliveryService.executeMassiveDeliveriesAndExport(FULL_DELIVERIES_RESOURCE);
        long endTime = System.currentTimeMillis();
        long time = (endTime - initTime)/1000;

        final List<String> filesFromFolder = FileManager.getFilesFromFolder(FULL_DELIVERIES_OUTPUT);
        final String filePath = filesFromFolder.get(19);
        final List<String> drone20Results = FileManager.readLinesFromFile(filePath);
        assertEquals(20, filesFromFolder.size());
        assertEquals(3, drone20Results.size());
        assertEquals("(-2,4) dirección Occidente", drone20Results.get(0));
        assertEquals("(-4,4) dirección Sur", drone20Results.get(1));
        assertEquals("(-4,3) dirección Sur", drone20Results.get(2));
        assertTrue(time < 1);
    }

    @Test
    public void executeOutOfBoundariesTest() throws IOException {
        long initTime = System.currentTimeMillis();
        deliveryService.executeMassiveDeliveriesAndExport(OUT_OF_BOUNDARIES);
        long endTime = System.currentTimeMillis();
        long time = (endTime - initTime)/1000;

        final List<String> filesFromFolder = FileManager.getFilesFromFolder(OUT_OF_BOUNDARIES_OUTPUT);
        final String filePath = filesFromFolder.get(0);
        final List<String> drone20Results = FileManager.readLinesFromFile(filePath);
        assertEquals(1, filesFromFolder.size());
        assertEquals(3, drone20Results.size());
        assertEquals("(0,7) dirección Norte", drone20Results.get(0));
        assertEquals("(0,7) dirección Oriente", drone20Results.get(1));
        assertEquals("Fuera de Cobertura", drone20Results.get(2));
        assertTrue(time < 1);
    }

    @After
    public void cleanUpOutputs() {
        FileManager.cleanUpFolder(BASE_CASE_OUTPUT);
        FileManager.cleanUpFolder(TWO_DELIVERIES_OUTPUT);
        FileManager.cleanUpFolder(FULL_DELIVERIES_OUTPUT);
        FileManager.cleanUpFolder(FOUR_ORDERS_OUTPUT);
        FileManager.cleanUpFolder(OUT_OF_BOUNDARIES_OUTPUT);
    }
}
