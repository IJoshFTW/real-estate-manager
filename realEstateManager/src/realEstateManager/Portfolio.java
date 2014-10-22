package realEstateManager;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Collection of {@link Residence}s
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class Portfolio {
	
	ArrayList<Residence> residences = new ArrayList<Residence>();

	public Portfolio() {}
	
	/**
	 * Read a number of {@link Residence} Objects from a file and add them to a {@link Portfolio} Object.
	 * 
	 * @param filename File to read from
	 * @return <b>Portfolio</b> Object with all <b>Residence</b> Objects found in the file
	 */
	public static Portfolio read(String filename) {
		Portfolio port = new Portfolio();
		try{
			Scanner sc = new Scanner(new File(filename));
			String inputstr;
			inputstr = sc.nextLine();
			int woningAmount = Integer.parseInt(inputstr);
			for(int i = 0; i < woningAmount; i++)
				port.addResidence(Residence.read(sc));
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Exception " + e.getClass() + " says: " + e.getMessage());
			e.printStackTrace();
		}
		return port;
	}
	
	/**
	 * Add a {@link Residence} to this collection of <b>Residence</b> Objects.
	 * @param rsd <b>Residence</b> to add
	 */
	public void addResidence(Residence rsd) {
		residences.add(rsd);
	}
	
	/**
	 * Used to get the {@link Residence} Objects which prices are below a certain amount
	 * @param price Price to check against
	 * @return {@link ArrayList} with all <b>Residence</b> Objects in this {@link Portfolio} with a price less than <i>price</i>
	 */
	public ArrayList<Residence> residencesUpto(int price) throws NegativeException {
		ArrayList<Residence> residences = new ArrayList<Residence>();
		if(price > 0) {
			for(int i = 0; i < this.residences.size(); i++) {
				if(this.residences.get(i).maxCost(price) == true)
					residences.add(this.residences.get(i));
			}
		} else if(price == 0) {
			for(int i = 0; i < this.residences.size(); i++) {
				residences.add(this.residences.get(i));
			}
		} else {
			throw new NegativeException("Nice try.");
		}
		return residences;
	}
	
	/**
	 * Getter
	 * @return All {@link Residence} Objects in this object.
	 */
	public ArrayList<Residence> getAllResidences() {
		return this.residences;
	}

}
