package flightBook;

public class Passenger {
	public int id;
	public String name;
	public int noOfSeatsBooked;
	public int price;
	
	public Passenger(int passId, String name, int noOfseats, int price) {
		this.id = passId;
		this.name = name;
		this.noOfSeatsBooked = noOfseats;
		this.price = price;
	}
}
