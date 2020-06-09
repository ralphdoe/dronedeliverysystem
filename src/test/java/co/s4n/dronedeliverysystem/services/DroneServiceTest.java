package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.util.Cardinality;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DroneServiceTest {
    private Drone defaultDrone;
    private DroneService droneService;

    @Before
    public void init() {
        defaultDrone = new Drone();
        droneService = DroneService.getInstance();
    }

    @Test
    public void moveRightTest() {
        droneService.moveDrone(defaultDrone, "D");
        assertEquals(0, defaultDrone.getX());
        assertEquals(0, defaultDrone.getY());
        assertEquals(Cardinality.EAST, defaultDrone.getCardinality());
    }

    @Test
    public void moveLeftTest() {
        droneService.moveDrone(defaultDrone, "I");
        assertEquals(0, defaultDrone.getX());
        assertEquals(0, defaultDrone.getY());
        assertEquals(Cardinality.WEST, defaultDrone.getCardinality());
    }

    @Test
    public void moveForwardAndNorthTest() {
        final Drone drone = new Drone(1, 5, 5, Cardinality.NORTH);
        droneService.moveDrone(drone, "A");
        assertEquals(5, drone.getX());
        assertEquals(6, drone.getY());
        assertEquals(Cardinality.NORTH, drone.getCardinality());
    }

    @Test
    public void moveRightToEastAndForwardTest() {
        final Drone drone = new Drone(1, 1, 1, Cardinality.NORTH);
        droneService.moveDrone(drone, "D");
        assertEquals(1, drone.getX());
        assertEquals(1, drone.getY());
        assertEquals(drone.getCardinality(), Cardinality.EAST);
        droneService.moveDrone(drone, "A");
        assertEquals(2, drone.getX());
        assertEquals(1, drone.getY());
        assertEquals(Cardinality.EAST, drone.getCardinality());
    }

    @Test
    public void moveLeftThenForwardThenLeftAndThenForwardTest() {
        final Drone drone = new Drone(1, 0, 0, Cardinality.NORTH);
        droneService.moveDrone(drone, "I");
        droneService.moveDrone(drone, "A");
        droneService.moveDrone(drone, "I");
        droneService.moveDrone(drone, "A");
        assertEquals(-1, drone.getX());
        assertEquals(-1, drone.getY());
        assertEquals(Cardinality.SOUTH, drone.getCardinality());
    }

    @Test
    public void moveLeftThenForwardThenLeftAndThenForwardAndSetDefaultTest() {
        final Drone drone = new Drone(1, 0, 0, Cardinality.NORTH);
        droneService.moveDrone(drone, "I");
        droneService.moveDrone(drone, "A");
        droneService.moveDrone(drone, "I");
        droneService.moveDrone(drone, "A");
        droneService.moveDroneToDefaultPosition(drone);
        assertEquals(0, drone.getX());
        assertEquals(0, drone.getY());
        assertEquals(Cardinality.NORTH, drone.getCardinality());
    }

    @Test
    public void getDroneIdFromPathTest() {
        final Optional<Drone> drone = droneService.getDroneFromFilePath("src/test/resources/baseCase/input/in04.txt");
        assertTrue(drone.isPresent());
        assertEquals(4, drone.get().getId());

    }

    @Test
    public void searchForEmptyDrone() {
        final Optional<Drone> drone = droneService.getDroneFromFilePath("src/test/resources/baseCase/input/in24.txt");
        assertTrue(drone.isEmpty());

    }
}
