package realEstateManager;

import java.util.Scanner;

/**
 * A variant of {@link Residence}. This variant is for resale residences, which also have an energy level.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class ResaleResidence extends Residence implements EnergyLevelResidence {
	
	private String energyLevel;
	
	public ResaleResidence(Address address, int rooms, int price, String energyLevel) {
		super(address, rooms, price);
		super.setResidenceType(1);
		
		this.energyLevel = energyLevel;
	}
	
	public static ResaleResidence read(Scanner sc, Residence rsd) {
		String energyLevel, inputstr;
		
		inputstr = sc.nextLine();
		String[] str = inputstr.split(" ");
		energyLevel = str[str.length - 1];
		
		ResaleResidence prsd = new ResaleResidence(rsd.getAddress(), rsd.getRooms(), rsd.getAskingPrice(), energyLevel);
		return prsd;
	}
	
	/**
	 * Returns a string representation of this instance.
	 */
	public String toString() {
		return "<" + this.getClass().getSimpleName() + "[" + super.getAddress().toString() + "," + super.getRooms() + "," + super.getAskingPrice() + "," + super.getResidenceType() + " " + super.getStatus() + "," + energyLevel + "]>";
	}
	
	public String getEnergyLevel() {
		return energyLevel;
	}

}
