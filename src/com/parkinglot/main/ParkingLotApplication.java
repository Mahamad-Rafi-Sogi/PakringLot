package com.parkinglot.main;

import java.time.LocalDateTime;

import com.parkinglot.enums.SlotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.exceptions.ParkingException;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingSlot;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingService;


public class ParkingLotApplication {

	public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot("PARK001");
        parkingLot.addSlot(new ParkingSlot("SLOT1", SlotType.SMALL));
        parkingLot.addSlot(new ParkingSlot("SLOT2", SlotType.MEDIUM));
        parkingLot.addSlot(new ParkingSlot("SLOT3", SlotType.LARGE));

        ParkingService parkingService = new ParkingService(parkingLot);
        

        try {
            Vehicle bike = new Vehicle("KA01AB1234", VehicleType.TRUCK);
            Ticket bikeTicket = parkingService.parkVehicle(bike);
            
            String message1 = """
                    Vehicle parked successfully, please check details below:
                    Vehicle No   : %s
                    Unparked Time: %s
                    
                    
                    
                    """.formatted(bikeTicket.getVehicleNumber(),  LocalDateTime.now());
            System.out.println(message1);
            
            //System.out.println("DETAILS : " + bikeTicket.getTicketId() + " Vehicle No: " + bikeTicket.getVehicleNumber() + " parcked at: " + LocalDateTime.now());
            
            double fee = parkingService.unparkVehicle(bikeTicket.getTicketId(), bike.getVehicleType());
            String message = """
                    Vehicle unparked successfully, please check details below:
                    Vehicle No   : %s
                    Unparked Time: %s
                    Total Fee    : %.2f
                    
                    NOTE: minimum and hourly charges will be based on catagory
                    		SMALL	: RS 10.0
                    		MEDIUM	: RS 20.0
                    		LARGE	: RS 50.0
                    """.formatted(bikeTicket.getVehicleNumber(),  LocalDateTime.now(), fee);
            System.out.println(message);
          //  System.out.println("Bike unparked succefully: DETAILS:  vehicle No: "+ bikeTicket.getVehicleNumber() + "unparked at: " + LocalDateTime.now() + " Fee: " + fee);

        } catch (ParkingException e) {
            System.err.println(e.getMessage());
        }
	}

}
