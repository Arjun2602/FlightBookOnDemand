package flightBook;

import java.util.HashMap;

public class Flight {
	public static int flightCount = 1;
	public int flightId;
	public int noOfSeats;
	public int ticketPrice;
	public HashMap<Integer, Passenger> passMap;
	
	public Flight() {
		this.flightId = flightCount++;
		this.noOfSeats = 50;
		this.ticketPrice = 5000;
		this.passMap = new HashMap<>();
	}
}
