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
			int residenceAmount = Integer.parseInt(inputstr);
			for(int i = 0; i < residenceAmount; i++)
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
	 * Add a {@link Residence} to this collection of <b>Residence</b> Objects and save it to a file <b>filename</b>
	 * @param rsd <b>Residence</b> to add
	 */
	public void addResidence(Residence rsd, String filename) {
		residences.add(rsd);
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename, false)));
			out.println(residences.size());
			for(int i = 0; i < residences.size(); i++) {
				Residence currsd = residences.get(i);				
				currsd.write(out);
			}
				
		}
		catch (IOException e) {
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		    Application.exit();
		}
		finally {
			out.close();
		}
	}
	
	/**
	 * Get the {@link Residence} Objects which prices are below or equal to a certain amount
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
	 * Get the {@link Residence} Objects which prices are below or equal to a certain amount and of a certain type
	 * @param price Price to check against
	 * @return {@link ArrayList} with all <b>Residence</b> Objects in this {@link Portfolio} with a price less than <i>price</i>
	 */
	public ArrayList<Residence> residencesUpto(int price, int status) throws NegativeException {
		ArrayList<Residence> residences = new ArrayList<Residence>();
		if(price > 0) {
			for(int i = 0; i < this.residences.size(); i++) {
				if(this.residences.get(i).maxCost(price) == true)
					if(this.residences.get(i).getStatus() == status)
						residences.add(this.residences.get(i));
			}
		} else if(price == 0) {
			for(int i = 0; i < this.residences.size(); i++) {
				if(this.residences.get(i).getStatus() == status)
					residences.add(this.residences.get(i));
			}
		} else {
			throw new NegativeException("Nice try.");
		}
		return residences;
	}
	
	/**
	 * Getter for all the {@link Residence} Objects
	 * @return All {@link Residence} Objects in this object.
	 */
	public ArrayList<Residence> getAllResidences() {
		return this.residences;
	}
	
	/**
	 * Getter for all the {@link Residence} Objects of a certain <b>type</b>
	 * @return All {@link Residence} Objects in this object of type <b>type</b>.
	 */
	public ArrayList<Residence> getAllResidences(int type) {
		ArrayList<Residence> list = new ArrayList<Residence>();
		for(int i = 0; i < residences.size(); i++)
			if(residences.get(i).getResidenceType() == type)
				list.add(residences.get(i));
		return list;
	}
	
	public Portfolio copyAllResidences(int type) {
		Portfolio port = new Portfolio();
		
		for(int i = 0; i < residences.size(); i++)
			if(residences.get(i).getResidenceType() == type || type == 0)
				port.addResidence(residences.get(i));
		return port;
	}

}
