package realEstateManager;

import java.util.Scanner;

/**
 * A residence with an address, information about the number of rooms it has and the price for this Woning.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 * @version 1.0
 */
public class Residence {
	
	private int rooms;
	private int askingPrice;
	private Address address;
	
	/**
	 * Create a Woning object with an address, information about the number of rooms and the price for this Woning. 
	 * @param adres Address of this Woning
	 * @param kamers Number of rooms in this Woning
	 * @param vraagPrijs The price for which this Woning sells
	 * @since 1.0
	 */
	public Residence(Address address, int rooms, int askingPrice) {
		this.address = address;
		this.rooms = rooms;
		this.askingPrice = askingPrice;
		
	}
	
	/**
	 * Read a 4 line Woning from a scanner with the format "[Adres(2 lines)]\n[kamerAmount] kamers\nprijs [vraagPrijs]"
	 * @param sc Scanner to read the Woning from. Active line must be the first line of the Woning
	 * @return Woning that was created using the values received from the Scanner.
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
	 * Test if obj is a Woning and contains the same values as this instance.
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
	 * Checks if the price of this Woning is less than a price you are willing to pay
	 * @param prijs Price to check against
	 * @return true if the price of the Woning is less than your price<br>false otherwise.
	 * @since 1.0
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
