package realEstateManager;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * An address with a street, house number, postal code and place.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class Address {
	
	private String street;
	private String number;
	private String postalCode;
	private String place;
	
	/**
	 * Create an {@link Address} with a street, house number, postal code and place.
	 * @param street Street string
	 * @param number House number string
	 * @param postalCode Postal code string
	 * @param place Place string
	 */
	public Address(String street, String number, String postalCode, String place) {
		this.street = street;
		this.number = number;
		this.postalCode = postalCode;
		this.place = place;
	}
	
	/**
	 * Read a 2 line address in the format "[street] [number][newline][postalCode] [place]"
	 * @param sc {@link java.util.Scanner} with the next line being the first line of the address.
	 * @return {@link Address} with the properties read from the <b>Scanner</b>
	 */
	public static Address read(Scanner sc) {
		String straat, huisnummer, postcode, plaats, inputstr, str1 = null, str2 = null;
		int lastspace = 0;
		
		inputstr = sc.nextLine();
		for(int i = 0; i < inputstr.length(); i++) {
			if(inputstr.charAt(i) == ' ') {									// Numbers and spaces in street name allowed, no spaces after number allowed
				lastspace = i;
			}
		}
		str1 = inputstr.substring(0, lastspace);
		str2 = inputstr.substring(lastspace + 1, inputstr.length());
		straat = str1;
		huisnummer = str2;
		
		// str1 = inputstr.split(" "); Kan ook
		
		inputstr = sc.nextLine();
		str1 = inputstr.substring(0, 6);
		str2 = inputstr.substring(7, inputstr.length());
		postcode = str1;
		plaats = str2;
		
		Address adr = new Address(straat, huisnummer, postcode, plaats);
		return adr;
	}
	
	/**
	 * Write this {@link Address} in pretty format to a {@link PrintWriter}
	 * @param out <b>PrintWriter</b> to write to
	 */
	public void write(PrintWriter out) {
		out.println(this.getStreet() + " " + this.getNumber());
		out.println(this.getPostalCode() + " " + this.getPlace());
	}
	
	/**
	 * Returns a string representation of this instance.
	 */
	public String toString() {
		return "<" + this.getClass().getSimpleName() + "[" + street + "," + number + "," + postalCode + "," + place + "]>";
	}
	
	/**
	 * Test if <b>obj</b> is an {@link Address} and contains the same values as this instance.
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Address) {
			Address that = (Address)obj;
			if(this.getStreet().equals(that.getStreet()))
				if(this.getNumber().equals(that.getNumber()))
					if(this.getPostalCode().equals(that.getPostalCode()))
						if(this.getPlace().equals(that.getPlace()))
							return true; 
		}
		return false;
	}

	/**
	 * @return The value of <B>street</b>
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return The value of <b>number</b>
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return The value of <b>postalCode</b>
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @return The value of <b>place</b>
	 */
	public String getPlace() {
		return place;
	}
	
}
