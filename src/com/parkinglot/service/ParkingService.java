package com.parkinglot.service;

import com.parkinglot.enums.SlotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.exceptions.ParkingException;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingSlot;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ParkingService {
    private ParkingLot parkingLot;
    private Map<String, Ticket> activeTickets;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.activeTickets = new HashMap<>();
    }

	public Ticket parkVehicle(Vehicle vehicle) throws ParkingException {
        SlotType slotType = mapVehicleToSlot(vehicle.getVehicleType());
        ParkingSlot slot = parkingLot.findAvailableSlot(slotType);
        slot.occupySlot();

        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, vehicle.getVehicleNumber(), slot.getSlotId());
        activeTickets.put(ticketId, ticket);

        return ticket;
    }

    public double unparkVehicle(String ticketId, VehicleType vehicleType) throws ParkingException {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new ParkingException("Invalid ticket ID");
        }

        parkingLot.releaseSlot(ticket.getSlotId());
        return FeeCalculator.calculateFee(ticket, vehicleType);
    }

    private SlotType mapVehicleToSlot(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
                return SlotType.SMALL;
            case CAR:
                return SlotType.MEDIUM;
            case TRUCK:
                return SlotType.LARGE;
            default:
                throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}
