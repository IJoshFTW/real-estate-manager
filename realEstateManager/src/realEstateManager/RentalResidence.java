package realEstateManager;

/**
 * A variant of {@link Residence}. This variant is for rental residences
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class RentalResidence extends Residence {
	
	public RentalResidence(Address address, int rooms, int price) {
		super(address, rooms, price);
		super.setResidenceType(2);
	}
	
}