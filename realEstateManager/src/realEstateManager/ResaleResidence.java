package realEstateManager;

import java.util.Scanner;

/**
 * A variant of {@link Residence}. This variant is for resale residences, which also have an energy level.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 * @see Residence, EnergyLevelResidence
 */
public class ResaleResidence extends Residence implements EnergyLevelResidence {
	
	private String energyLevel;
	
	/**
	 * Constructor
	 * @param address Address of this resale residence
	 * @param rooms Number of rooms it has
	 * @param askingPrice The price wanted for this residence
	 * @param energyLevel Energy level of this residence (On a scale of A to E)
	 */
	public ResaleResidence(Address address, int rooms, int askingPrice, String energyLevel) {
		super(address, rooms, askingPrice);
		super.setResidenceType(1);
		
		this.energyLevel = energyLevel;
	}
	
	/**
	 * Only to be called by {@link Residence#read(Scanner)} itself, to get the energy level
	 * @param sc Scanner with the energy level being the next line to read
	 * @param rsd Current residence as constructed by {@link Residence#read(Scanner)}
	 * @return A ResaleResidence with the properties of <b>rsd</b> and the energy level read from <b>sc</b>
	 */
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
