package realEstateManager;

/**
 * Exception for when a negative integer is given where a positive or neutral integer is expected.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class NegativeException extends Exception {

	/**
	 * asdf
	 */
	private static final long serialVersionUID = -1477314396534976400L;
	
	public NegativeException() {
		super();
	}
	
	public NegativeException(String s) {
		super(s);
	}

}
