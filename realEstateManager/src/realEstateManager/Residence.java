package realEstateManager;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A residence with an address, information about the number of rooms it has and the price for this Residence.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class Residence {
	
	private int rooms;
	private int askingPrice;
	private Address address;
	private int residenceType;	// 0 = Normal, 1 = Resale, 2 = Rental
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
		this.residenceType = 0;
		this.status = 1;
	}
	
	/**
	 * Read a 5 line Residence from a scanner with the format "[type and status][\n][address(2 lines)][\n][rooms] rooms[\n]price [askingPrice]"
	 * @param sc Scanner to read the Residence from. Active line must be the first line of the Residence
	 * @return Residence that was created using the values received from the Scanner.
	 */
	public static Residence read(Scanner sc) {
		int rooms = 0, askingPrice = 0, type, status;
		String inputstr;
		String[] strarr;
		
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
			type = 0; status = 1;
		}
		
		Address adr = Address.read(sc);							// Process the address
		
		inputstr = sc.nextLine();								// Read and process the number of rooms
		strarr = inputstr.split(" ");
		rooms = Integer.parseInt(strarr[0]);
		
		inputstr = sc.nextLine();								// Read and process the price
		strarr = inputstr.split(" ");
		askingPrice = Integer.parseInt(strarr[strarr.length - 1]);
		
		Residence rsd = new Residence(adr, rooms, askingPrice);		// Depending on the residence type, construct the correct object
		if(type == 1)
			rsd = ResaleResidence.read(sc, rsd);
		else if (type == 2)
			rsd = new RentalResidence(adr, rooms, askingPrice);
		else
			rsd = new Residence(adr, rooms, askingPrice);
		
		rsd.setStatus(status);
		return rsd;
	}
	
	/**
	 * Write this {@link Residence} in pretty format to a {@link PrintWriter}
	 * @param out <b>PrintWriter</b> to write to
	 */
	public void write(PrintWriter out) {
		// Write status
		if(this.getResidenceType() == 1)
			if(this.getStatus() == 1)
				out.println("FOR SALE:");
			else
				out.println("SOLD:");
		else if(this.getResidenceType() == 2)
			if(this.getStatus() == 1)
				out.println("FOR RENT:");
			else
				out.println("RENTED:");
		else
			if(this.getStatus() == 1)
				out.println("DEFAULT TYPE, UNPURCHASED");
			else
				out.println("DEFAULT TYPE, PURCHASED");
		
		// Write address
		this.getAddress().write(out);
		
		// Write rooms
		out.println(this.getRooms() + " rooms");
		
		// Write price with correct format and energy level if applicable
		if(this.getResidenceType() == 1) {
			if(this.getStatus() == 1)
				out.println("asking price " + this.getAskingPrice());
			else
				out.println("final price " + this.getAskingPrice());
			out.println("energy level " + ((ResaleResidence)this).getEnergyLevel());
		} else {
			out.println("rental price " + this.getAskingPrice());
		}
	}
	
	/**
	 * Returns a string representation of this instance.
	 */
	public String toString() {
		return "<" + this.getClass().getSimpleName() + "[" + address.toString() + "," + rooms + "," + askingPrice + "," + residenceType + " " + status + "]>";
	}
	
	public void printPretty() {
		// Write status
		if(this.getResidenceType() == 1)
			if(this.getStatus() == 1)
				System.out.println("FOR SALE:");
			else
				System.out.println("SOLD:");
		else
			if(this.getStatus() == 1)
				System.out.println("FOR RENT:");
			else
				System.out.println("RENTED:");
		
		// Write address
		System.out.println(this.getAddress().getStreet() + " " + this.getAddress().getNumber());
		System.out.println(this.getAddress().getPostalCode() + " " + this.getAddress().getPlace());
		
		// Write rooms
		System.out.println(this.getRooms() + " rooms");
		
		// Write price with correct format and energy level if applicable
		if(this.getResidenceType() == 1) {
			if(this.getStatus() == 1)
				System.out.println("asking price " + this.getAskingPrice());
			else
				System.out.println("final price " + this.getAskingPrice());
			System.out.println("energy level " + ((ResaleResidence)this).getEnergyLevel());
		} else {
			System.out.println("rental price " + this.getAskingPrice());
		}
		
		System.out.println();
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
						if(this.getResidenceType() == rsd.getResidenceType())
							if(this.getStatus() == rsd.getStatus())
									return true;
		}
		return false;
	}
	
	/**
	 * Checks if the price of this {@link Residence} is less than or equal to a price
	 * @param price Price to check against
	 * @return true if the price of the <b>Residence</b> is less than your price<br>false otherwise.
	 */
	public boolean maxCost(int price) {
		if(this.askingPrice <= price)
			return true;
		return false;
	}
	
	/**
	 * Getter
	 * @return The value of <b>rooms</b>
	 */
	public int getRooms() {
		return rooms;
	}

	/**
	 * Getter
	 * @return The value of <b>askingPrice</b>
	 */
	public int getAskingPrice() {
		return askingPrice;
	}

	/**
	 * Getter
	 * @return The value of <b>address</b>
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * Getter
	 * @return The value of <b>residenceType</b>
	 */
	public int getResidenceType() {
		return residenceType;
	}
	
	/**
	 * Setter
	 * Set the value of <b>residenceType</b>
	 * @param type type to make this {@link Residence}
	 */
	public void setResidenceType(int type) {
		this.residenceType = type;
	}
	
	/**
	 * Getter
	 * @return The value of <b>status</b>
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Setter
	 * Set the value of <b>status</b>
	 * @param status status to make this {@link Residence}
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
