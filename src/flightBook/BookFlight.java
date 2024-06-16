package flightBook;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookFlight {
	
	public static int noOfFlys = 2;
	public static HashMap<Integer, Flight> flightMap = new HashMap<>();
	public static int passId = 1;
	
	
	// create flights
	private static void createFlights() {
		for(int i = 0; i < noOfFlys; i++) {
			Flight fly = new Flight();
			flightMap.put(fly.flightId, fly);
		}
	}
	
	// print flights
		private static void printDetails() {
			for(Map.Entry<Integer, Flight> f : flightMap.entrySet()) {
				System.out.println();
				System.out.println("Flight Id :"+f.getValue().flightId+" Flight seats :"+f.getValue().noOfSeats+" Flight price :"+f.getValue().ticketPrice);
				System.out.println("<-------Passenger Details------->");
				printPassengers(f.getValue());
			}
			System.out.println("----------------------------------------------------------------------------------");
		}
		
	// print passengers
	private static void printPassengers(Flight fly) {
		HashMap<Integer, Passenger> pass = fly.passMap;
		System.out.println("Id     Name     NoOfSeats     Price");
		for(Map.Entry<Integer, Passenger> p : pass.entrySet()) {
			System.out.println(+p.getValue().id+"      "+p.getValue().name+"        "+p.getValue().noOfSeatsBooked+"         "+p.getValue().price);
		}
		
	}
	
	// MAIN METHOD
	public static void main(String[] args) {
		
		createFlights();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1) Book 2) Cancel 3) Print Flight");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				//book
				bookTicket();
				break;
			case 2:
				// cancel
				cancelTicket();
				break;
			case 3:
				//print flight
				printDetails();
				break;
			default:
				flag = false;
				break;
			}
		}
	}
	// cancel ticket
	private static void cancelTicket() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Flight Id :");
		int flightId = sc.nextInt();//<------------------------flight Id
		
		if(!flightMap.containsKey(flightId)) {
			System.out.println("No such flight Id exist..");
			return;
		}
		
		System.out.println("Enter Pssenger Id to Cancel :");
		int passId = sc.nextInt();//<--------------------------Passenger Id
		
		HashMap<Integer, Passenger> passList = flightMap.get(flightId).passMap;
		if(!passList.containsKey(passId)) {
			System.out.println("No such Passenger id Exist..");
			return;
		}

		// now increase the seat and decrease the price of flight
		changeFlightDetails(flightId, passId);
		// remove passenger from pass list
		flightMap.get(flightId).passMap.remove(passId); 
		System.out.println("Cancelled Successfully...");
	}
	
	// After cancelling change the flight price and seats
	// now increase the seat and decrease the price of flight
	private static void changeFlightDetails(int flightId, int passId2) {
		int actualSeatsInFlight = 0;
		int noOfSeatsBooked = 0;
		int finalSeats = 0;
		int pricetoReduce = 0;
		int actualPrice = 0;
		int finalPrice = 0;
		actualSeatsInFlight = flightMap.get(flightId).noOfSeats;
		HashMap<Integer, Passenger> passMap = flightMap.get(flightId).passMap;
		noOfSeatsBooked = passMap.get(passId2).noOfSeatsBooked;
		finalSeats = actualSeatsInFlight + noOfSeatsBooked;
		
		actualPrice = flightMap.get(flightId).ticketPrice;
		pricetoReduce = 200 * noOfSeatsBooked;
		finalPrice = actualPrice - pricetoReduce;
		
		// now change
		flightMap.get(flightId).noOfSeats = finalSeats;
		flightMap.get(flightId).ticketPrice = finalPrice;
	}

//********************************************************************
	// book tickets
	private static void bookTicket() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter flight Id :");
		// from user input - flight Id
		int flightId = sc.nextInt();
		
		if(!flightMap.containsKey(flightId)) {
			System.out.println("No such flight Id is Available");
			return;
		}
		// flight
		Flight fly = flightMap.get(flightId);
		
		//name and  no of seats
		System.out.println("Enter name :");
		String name = sc.next();
		System.out.println("Enter no of Seats :");
		int noOfseats = sc.nextInt();
		
		//check if seats available
		if(noOfseats > fly.noOfSeats) {
			System.out.println("No seats Available..");
			return;
		}
		// passenger id
		int id = passId;
		int totalPrice = calculatePrice(flightId, noOfseats);
		Passenger p = new Passenger(id, name, noOfseats, totalPrice);
		fly.passMap.put(id, p);
		System.out.println("Booked Sucessfully..");
		
		// now decrement the seats and increse price of tht flight
		changetheFlight(flightId,noOfseats);
		passId++;
	}
	
	// After Booking changing the seats and price of flight
	private static void changetheFlight(int flightId, int noOfseats) {
		int actualSeats = 0;
		int finalSeats = 0;
		int finalPrice = 0;
		int actualPrice = 0;
		int amountToincrease = 0;
		amountToincrease = noOfseats * 200;
		
		actualPrice = flightMap.get(flightId).ticketPrice;
		actualSeats = flightMap.get(flightId).noOfSeats;
		
		finalPrice = actualPrice + amountToincrease;
		finalSeats = actualSeats - noOfseats;
		
		// now changing for seats
		flightMap.get(flightId).noOfSeats = finalSeats;
		//now changing for Price
		flightMap.get(flightId).ticketPrice = finalPrice;
	}

	// calculate the cost to book fly
	private static int calculatePrice(int flightId, int noOfseats) {
		int totalrice = 0;
		Flight f = flightMap.get(flightId);
		totalrice = noOfseats * f.ticketPrice;
		return totalrice;
	}

}
