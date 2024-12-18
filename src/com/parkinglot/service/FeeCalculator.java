package com.parkinglot.service;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;



public class FeeCalculator {
    private static final double BIKE_HOURLY_RATE = 10.0;
    private static final double CAR_HOURLY_RATE = 20.0;
    private static final double TRUCK_HOURLY_RATE = 50.0;

    public static double calculateFee(Ticket ticket, VehicleType vehicleType) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        long hours = Duration.between(entryTime, exitTime).toHours();
        switch (vehicleType) {
		case BIKE:  
			return  Math.max(1, hours) * BIKE_HOURLY_RATE;
		case CAR:
			return  Math.max(1, hours) * CAR_HOURLY_RATE;
		case TRUCK:
			return Math.max(1, hours) * TRUCK_HOURLY_RATE;
        }
        return 0;
    }
}
