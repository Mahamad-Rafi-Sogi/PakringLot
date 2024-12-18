package com.parkinglot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.parkinglot.enums.SlotType;
import com.parkinglot.exceptions.ParkingException;

public class ParkingLot {
    private String parkingLotId;
    private List<ParkingSlot> slots;

    public ParkingLot(String parkingLotId) {
        this.parkingLotId = parkingLotId;
        this.slots = new ArrayList<>();
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public ParkingSlot findAvailableSlot(SlotType slotType) throws ParkingException {
        Optional<ParkingSlot> slot = slots.stream()
                .filter(s -> s.getSlotType() == slotType && !s.isOccupied())
                .findFirst();

        return slot.orElseThrow(() -> new ParkingException("No available slots for type: " + slotType));
    }

    public void releaseSlot(String slotId) {
        slots.stream()
                .filter(s -> s.getSlotId().equals(slotId))
                .findFirst()
                .ifPresent(ParkingSlot::freeSlot);
    }
}
