package com.parkinglot.model;

import com.parkinglot.enums.SlotType;

public class ParkingSlot {
    private String slotId;
    private SlotType slotType;
    private boolean isOccupied;
    
	public ParkingSlot(String slotId, SlotType slotType) {
		super();
		this.slotId = slotId;
		this.slotType = slotType;
		this.isOccupied = false;
	}

	public String getSlotId() {
		return slotId;
	}

	public SlotType getSlotType() {
		return slotType;
	}
	
    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupySlot() {
        this.isOccupied = true;
    }

    public void freeSlot() {
        this.isOccupied = false;
    }
    
    
}
