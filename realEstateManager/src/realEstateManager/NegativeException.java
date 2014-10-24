package realEstateManager;

/**
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class NegativeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1477314396534976400L;

	/**
	 * @param args
	 */
	public NegativeException() {
		super();
	}
	
	public NegativeException(String s) {
		super(s);
	}

}
