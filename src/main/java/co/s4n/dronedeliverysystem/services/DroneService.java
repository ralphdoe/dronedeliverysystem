package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.util.Cardinality;
import co.s4n.dronedeliverysystem.util.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DroneService {
    private static DroneService droneService;

    private static final String INPUT_IN_TEXT = "/input/in";
    private static final List<Drone> drones = new ArrayList<>();
    private static final int NUMBER_OF_DRONES = 20;
    private static final int CURRENT_SCOPE_RANGE = 10;

    private DroneService() {
    }

    public static DroneService getInstance() {
        if (droneService == null ) {
            droneService = new DroneService();
        }
        return droneService;
    }

    static {
        for (int i = 0; i < NUMBER_OF_DRONES; i++) {
            final Drone drone = new Drone();
            drone.setId(i + 1);
            drones.add(drone);
        }
    }

    public void moveDrone(final Drone drone, final String move) {
        if (move.equals(Move.FORWARD.id())) {
            moveForward(drone);
        } else if (move.equals(Move.RIGHT.id())) {
            moveRight(drone);
        } else if (move.equals(Move.LEFT.id())) {
            moveLeft(drone);
        }
    }

    private void moveForward(final Drone drone) {
        if (drone.getCardinality().equals(Cardinality.NORTH)) {
            drone.setY(drone.getY() + 1);
        } else if (drone.getCardinality().equals(Cardinality.EAST)) {
            drone.setX(drone.getX() + 1);
        } else if (drone.getCardinality().equals(Cardinality.SOUTH)) {
            drone.setY(drone.getY() - 1);
        } else if (drone.getCardinality().equals(Cardinality.WEST)) {
            drone.setX(drone.getX() - 1);
        }
    }

    private void moveRight(final Drone drone) {
        if (drone.getCardinality().equals(Cardinality.NORTH)) {
            drone.setCardinality(Cardinality.EAST);
        } else if (drone.getCardinality().equals(Cardinality.EAST)) {
            drone.setCardinality(Cardinality.SOUTH);
        } else if (drone.getCardinality().equals(Cardinality.SOUTH)) {
            drone.setCardinality(Cardinality.WEST);
        } else if (drone.getCardinality().equals(Cardinality.WEST)) {
            drone.setCardinality(Cardinality.NORTH);
        }
    }

    private void moveLeft(final Drone drone) {
        if (drone.getCardinality().equals(Cardinality.NORTH)) {
            drone.setCardinality(Cardinality.WEST);
        } else if (drone.getCardinality().equals(Cardinality.WEST)) {
            drone.setCardinality(Cardinality.SOUTH);
        } else if (drone.getCardinality().equals(Cardinality.SOUTH)) {
            drone.setCardinality(Cardinality.EAST);
        } else if (drone.getCardinality().equals(Cardinality.EAST)) {
            drone.setCardinality(Cardinality.NORTH);
        }
    }

    public boolean isDroneInBoundaries(final Drone drone) {
        return (drone.getX() <= CURRENT_SCOPE_RANGE && drone.getX() >= CURRENT_SCOPE_RANGE * -1)
                && (drone.getY() <= CURRENT_SCOPE_RANGE && drone.getY() >= CURRENT_SCOPE_RANGE * -1) ;

    }

    public Optional<Drone> getDroneFromFilePath(final String filePath) {
        int beginIndex = filePath.indexOf(INPUT_IN_TEXT) + INPUT_IN_TEXT.length();
        int endIndex = beginIndex + 2;
        int id = Integer.parseInt(filePath.substring(beginIndex, endIndex));
        return getDroneById(id);
    }

    public Optional<Drone> getDroneById(final int id) {
        for (final Drone drone : drones) {
            if (drone.getId() == id) {
                return Optional.of(drone);
            }
        }
        return Optional.empty();
    }

    public void moveDroneToDefaultPosition(final Drone drone) {
        drone.setX(0);
        drone.setY(0);
        drone.setCardinality(Cardinality.NORTH);
    }
}