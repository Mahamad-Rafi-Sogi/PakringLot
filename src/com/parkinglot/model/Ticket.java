package com.parkinglot.model;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private String vehicleNumber;
    private String slotId;
    private LocalDateTime entryTime;

    public Ticket(String ticketId, String vehicleNumber, String slotId) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.slotId = slotId;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getSlotId() {
        return slotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
