package realEstateManager;

/**
 * A variant of {@link Residence}. This variant is for rental residences.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 * @see Residence
 */
public class RentalResidence extends Residence {
	
	/**
	 * Construct the instance and set the residenceType to 2 (which stands for rental)
	 * @param address Address for this residence
	 * @param rooms Number of rooms it has
	 * @param price Price per month to rent this residence
	 */
	public RentalResidence(Address address, int rooms, int price) {
		super(address, rooms, price);
		super.setResidenceType(2);
	}
	
}