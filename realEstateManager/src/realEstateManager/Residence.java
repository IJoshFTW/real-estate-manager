package realEstateManager;

import java.util.Scanner;

/**
 * A residence with an address, information about the number of rooms it has and the price for this Residence.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class Residence {
	
	private int rooms;
	private int askingPrice;
	private Address address;
	private int residenceType;
	private int status;			// 0 = SOLD/RENTED, 1 = FOR SALE/FOR RENT
	
	/**
	 * Create a Residence object with an address, information about the number of rooms and the price for this Residence. 
	 * @param address Address of this Residence
	 * @param rooms Number of rooms in this Residence
	 * @param askingPrice The price for which this Residence sells
	 */
	public Residence(Address address, int rooms, int askingPrice) {
		this.address = address;
		this.rooms = rooms;
		this.askingPrice = askingPrice;
	}
	
	/**
	 * Read a 5 line Residence from a scanner with the format "[type and status][\n][address(2 lines)][\n][rooms] rooms[\n]price [askingPrice]"
	 * @param sc Scanner to read the Residence from. Active line must be the first line of the Residence
	 * @return Residence that was created using the values received from the Scanner.
	 */
	public static Residence read(Scanner sc) {
		int rooms = 0, askingPrice = 0, type, status;
		String inputstr;
		
		inputstr = sc.nextLine();								// Read and process the first line, which indicates the residence type and status
		switch (inputstr) {
		case "FOR SALE:":
			type = 1; status = 1;
			break;
		case "SOLD:":
			type = 1; status = 0;
			break;
		case "FOR RENT:":
			type = 2; status = 1;
			break;
		case "RENTED:":
			type = 2; status = 0;
			break;
		default:
			type = -1; status = -1;
		}
		
		Address adr = Address.read(sc);							// Process the address
		
		inputstr = sc.nextLine();								// Read and process the number of rooms
		for(int i = 0; i < inputstr.length(); i++)
			if(inputstr.charAt(i) == ' ') {
				inputstr = inputstr.substring(0, i);
				break;
			}
		rooms = Integer.parseInt(inputstr);
		
		inputstr = sc.nextLine();								// Read and process the price
		for(int i = 0; i < inputstr.length(); i++)
			if(inputstr.charAt(i) == ' ') {
				inputstr = inputstr.substring(i + 1, inputstr.length());
				break;
			}
		askingPrice = Integer.parseInt(inputstr);
		
		Residence rsd = new Residence(adr, rooms, askingPrice);		// Depending on the residence type, construct the correct object
		if(type == 1)
			rsd = ResaleResidence.read(sc, rsd);
		else
			rsd = new RentalResidence(adr, rooms, askingPrice);
		
		rsd.setStatus(status);
		return rsd;
	}
	
	/**
	 * Returns a string representation of this instance.
	 */
	public String toString() {
		return "<" + this.getClass().getSimpleName() + "[" + address.toString() + "," + rooms + "," + askingPrice + "]>";
	}
	
	/**
	 * Test if obj is a Residence and contains the same values as this instance.
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Residence) {
			Residence rsd = (Residence) obj;
			if(this.getAddress().equals(rsd.getAddress()))
				if(this.getRooms() == rsd.getRooms())
					if(this.getAskingPrice() == rsd.getAskingPrice())
						return true;
		}
		return false;
	}
	
	/**
	 * Checks if the price of this Residence is less than a price you are willing to pay
	 * @param price Price to check against
	 * @return true if the price of the Residence is less than your price<br>false otherwise.
	 */
	public boolean maxCost(int price) {
		if(this.askingPrice <= price)
			return true;
		return false;
	}
	
	/**
	 * @return The value of <em>rooms</em>
	 */
	public int getRooms() {
		return rooms;
	}

	/**
	 * @return The value of <em>askingPrice</em>
	 */
	public int getAskingPrice() {
		return askingPrice;
	}

	/**
	 * @return The value of <em>address</em>
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * @return The value of <em>residenceType</em>
	 */
	public int getResidenceType() {
		return residenceType;
	}
	
	/**
	 * Set the value of <em>residenceType</em>
	 */
	public void setResidenceType(int type) {
		this.residenceType = type;
	}
	
	/**
	 * @return The value of <em>status</em>
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Set the value of <em>status</em>
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
