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
	 * Read a 4 line Residence from a scanner with the format "[address(2 lines)][\n][rooms] rooms[\n]price [askingPrice]"
	 * @param sc Scanner to read the Residence from. Active line must be the first line of the Residence
	 * @return Residence that was created using the values received from the Scanner.
	 */
	public Residence read(Scanner sc) {
		int rooms = 0, askingPrice = 0;
		String inputstr;
		Address nullAdr = new Address(null,null,null,null);
		
		Address adr = nullAdr.read(sc);
		
		inputstr = sc.nextLine();
		for(int i = 0; i < inputstr.length(); i++)
			if(inputstr.charAt(i) == ' ') {
				inputstr = inputstr.substring(0, i);
				break;
			}
		rooms = Integer.parseInt(inputstr);
		
		inputstr = sc.nextLine();
		for(int i = 0; i < inputstr.length(); i++)
			if(inputstr.charAt(i) == ' ') {
				inputstr = inputstr.substring(i + 1, inputstr.length());
				break;
			}
		askingPrice = Integer.parseInt(inputstr);
		
		Residence rsd = new Residence(adr, rooms, askingPrice);
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
	public boolean kostHooguit(int price) {
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

}
