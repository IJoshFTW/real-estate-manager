package realEstateManager;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * 
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class Portfolio {
	
	ArrayList<Residence> residences = new ArrayList<Residence>();

	public Portfolio() {}
	
	/**
	 * Read a number of Woning Objects from a file and add them to a Portefeuille Object.
	 * 
	 * @param filename File to read from
	 * @return Portefeuille Object with all Woning Objects found in the file
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
	 * Add a {@link Residence} to this collection of Residence Objects.
	 * @param won Woning to add
	 */
	public void addResidence(Residence rsd) {
		residences.add(rsd);
	}
	
	/**
	 * Used to get the Woning Objects which prices are below a certain amount
	 * @param prijs Price to check against
	 * @return ArrayList with Woning Objects with all Woning Objects in this Portefeuille with a price less than prijs
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
	 * 
	 * @return All Woning Objects in this object.
	 */
	public ArrayList<Residence> getAllResidences() {
		return this.residences;
	}

}
