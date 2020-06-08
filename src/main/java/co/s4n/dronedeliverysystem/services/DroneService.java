package co.s4n.dronedeliverysystem.services;

import co.s4n.dronedeliverysystem.models.Drone;
import co.s4n.dronedeliverysystem.util.Cardinality;
import co.s4n.dronedeliverysystem.util.Move;

public class DroneService {
    public void moveDrone(final Drone drone, final String move) {
        if (move.equals(Move.FORWARD.id())) {
            if (drone.getCardinality().equals(Cardinality.NORTH)) {
                drone.setY(drone.getY() + 1);
            } else if (drone.getCardinality().equals(Cardinality.EAST)) {
                drone.setX(drone.getX() + 1);
            } else if (drone.getCardinality().equals(Cardinality.SOUTH)) {
                drone.setY(drone.getY() - 1);
            } else if (drone.getCardinality().equals(Cardinality.WEST)) {
                drone.setX(drone.getX() - 1);
            }
        } else if (move.equals(Move.RIGHT.id())) {
            if (drone.getCardinality().equals(Cardinality.NORTH)) {
                drone.setCardinality(Cardinality.EAST);
            } else if (drone.getCardinality().equals(Cardinality.EAST)) {
                drone.setCardinality(Cardinality.SOUTH);
            } else if (drone.getCardinality().equals(Cardinality.SOUTH)) {
                drone.setCardinality(Cardinality.WEST);
            } else if (drone.getCardinality().equals(Cardinality.WEST)) {
                drone.setCardinality(Cardinality.NORTH);
            }
        } else if (move.equals(Move.LEFT.id())) {
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
    }

    public void moveDroneToDefaultPosition(final Drone drone) {
        drone.setX(0);
        drone.setY(0);
        drone.setCardinality(Cardinality.NORTH);
    }
}