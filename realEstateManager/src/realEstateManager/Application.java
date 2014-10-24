package realEstateManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Text User Interface for the realEstate Application.
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class Application {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Portfolio port = new Portfolio();
	static String savefile = "residences.txt";
	
	/**
	 * Main method containing the main menu
	 * 
	 * Reads a user input and will redirect to other methods in this class to.
	 * 
	 * @param args Commandline arguments (There are none)
	 */
	public static void main(String[] args) {
		
		port = Portfolio.read(savefile);
		
		String inputstr = null;
		int inputint = 0;
		writeLines();
		
		while(true) {
			System.out.println("Please select one of the following options:");
			System.out.println("    1. Add Resale Residence");
			System.out.println("    2. Add Rental Residence");
			System.out.println("    3. View Resale Residences");
			System.out.println("    4. View Rental Residences");
			System.out.println("    5. Exit program");
			System.out.print("\nYour choice: ");
			
			inputint = 0;
			try { inputstr = br.readLine(); }
			catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try { inputint = Integer.parseInt(inputstr); }
			catch (NumberFormatException e) {
				System.out.println("Invalid input, returning to main menu\n");
			}
			
			writeLines();
						
			switch(inputint) {
			case 1:
				addResale();
				break;
			case 2:
				addRental();
				break;
			case 3:
				readResale();
				break;
			case 4:
				readRental();
				break;
			case 5:
				exit();
				break;
			default:
				System.out.println("Invalid input, returning to main menu\n");
				break;
			}
		}			
	}
	
	/**
	 * Method for adding a resale residence to the {@link Portfolio} <b>port</b> and saving them to file
	 */
	public static void addResale() {
		String inputstr, street, number, postalCode, place, energyLevel;
		int rooms, price, status;
		System.out.println("You are about to add a new resale residence.\n");
		
		// Get status: FOR SALE or SOLD
		System.out.println("Is this residence still for sale? [Y/N]");
		inputstr = readLine();
		if(inputstr.equals("Y")) {
			status = 1;
		} else if(inputstr.equals("N")) {
			status = 0;
		} else {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		
		// Enter streetname and number
		System.out.println("Please enter a streetname and house number, seperated by a space. It is OK if your streetname has spaces.");
		String[] strarr;
		try { strarr = readLineSplitMin(" ", 2); }
		catch (IOException e) {
			return;
		}
		street = strarr[0];
		for(int i = 1; i < strarr.length - 1; i++)
			street = street + " " + strarr[i];
		number = strarr[strarr.length - 1];
		
		// Enter postalcode and place
		System.out.println("Please enter a postal code and a place, seperated by a space.");
		try { strarr = readLineSplitEquals(" ", 2); }
		catch (IOException e) {
			return;
		}
		postalCode = strarr[0];
		place = strarr[1];
		for(int i = 2; i < strarr.length; i++)
			place = place + " " + strarr[i];
		Address adr = new Address(street, number, postalCode, place);
		
		// Enter number of rooms
		System.out.println("Please enter a number of rooms.");
		try { rooms = Integer.parseInt(readLine()); }
		catch (NumberFormatException e) {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		
		// Enter price
		System.out.println("Please enter a price.");
		try { price = Integer.parseInt(readLine()); }
		catch (NumberFormatException e) {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		
		// Enter energy level
		System.out.println("Please enter an energy level.");
		energyLevel = readLine();
		if(energyLevel.length() == 0) {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		
		Residence rsd = new ResaleResidence(adr, rooms, price, energyLevel);
		rsd.setStatus(status);
		port.addResidence(rsd, savefile);
		System.out.println(port.getAllResidences().toString());
		writeLines();
	}
	
	/**
	 * Method for adding a rental residence to the {@link Portfolio} <b>port</b> and saving them to file
	 */
	public static void addRental() {
		String street, number, postalCode, place, inputstr;
		int rooms, price, status;
		System.out.println("You are about to add a new rental residence.\n");
		
		// Get status: FOR RENT or RENTED
		System.out.println("Is this residence still for rent? [Y/N]");
		inputstr = readLine();
		if(inputstr.equals("Y")) {
			status = 1;
		} else if(inputstr.equals("N")) {
			status = 0;
		} else {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		
		// Enter streetname and number
		System.out.println("Please enter a streetname and house number, seperated by a space. It is OK if your streetname has spaces.");
		String[] strarr;
		try { strarr = readLineSplitMin(" ", 2); }
		catch (IOException e) {
			return;
		}
		street = strarr[0];
		for(int i = 1; i < strarr.length - 1; i++)
			street = street + " " + strarr[i];
		number = strarr[strarr.length - 1];
		
		// Enter postalcode and place
		System.out.println("Please enter a postal code and a place, seperated by a space.");
		try { strarr = readLineSplitEquals(" ", 2); }
		catch (IOException e) {
			return;
		}
		postalCode = strarr[0];
		place = strarr[1];
		Address adr = new Address(street, number, postalCode, place);
		
		// Enter number of rooms
		System.out.println("Please enter a number of rooms.");
		try { rooms = Integer.parseInt(readLine()); }
		catch (NumberFormatException e) {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		
		// Enter price
		System.out.println("Please enter a price.");
		try { price = Integer.parseInt(readLine()); }
		catch (NumberFormatException e) {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}

		Residence rsd = new RentalResidence(adr, rooms, price);
		rsd.setStatus(status);
		port.addResidence(rsd, savefile);
		System.out.println(port.getAllResidences().toString());
		writeLines();
	}
	
	/**
	 * Method for receiving resale residences from the {@link Portfolio} <b>port</b> depending on a user selected filter
	 */
	public static void readResale() {
		String inputstr;
		ArrayList<Residence> rsdlist;
		int price, status;
		System.out.println("You are about to view resale residences.\n");
		System.out.println("Are you only interested in residences for sale? No will also display sold residences. [Y/N]");
		inputstr = readLine();
		if(inputstr.equals("Y")) {
			status = 1;
		} else if(inputstr.equals("N")) {
			status = 0;
		} else {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		System.out.println("Enter a maximum price for the residences. 0 will skip this step.");
		price = Integer.parseInt(readLine());
		
		Portfolio resaleport = port.copyAllResidences(1);
		try {
			rsdlist = resaleport.residencesUpto(price, status);
		}
		catch (NegativeException e) {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		
		System.out.println("\n\n" + rsdlist.size() + " residences match your filter:\n");
		
		for(int i = 0; i < rsdlist.size(); i++)
			rsdlist.get(i).printPretty();
	}
	
	/**
	 * Method for receiving rental residences from the {@link Portfolio} <b>port</b> depending on a user selected filter
	 */
	public static void readRental() {
		String inputstr;
		ArrayList<Residence> rsdlist;
		int price, status;
		System.out.println("You are about to view rental residences.\n");
		System.out.println("Are you only interested in residences for rent? No will also display rented residences. [Y/N]");
		inputstr = readLine();
		if(inputstr.equals("Y")) {
			status = 1;
		} else if(inputstr.equals("N")) {
			status = 0;
		} else {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		System.out.println("Enter a maximum price for the residences. 0 will skip this step.");
		price = Integer.parseInt(readLine());
		
		Portfolio rentalport = port.copyAllResidences(2);
		try {
			rsdlist = rentalport.residencesUpto(price, status);
		}
		catch (NegativeException e) {
			System.out.println("Invalid input, returning to main menu\n");
			return;
		}
		
		System.out.println("\n\n" + rsdlist.size() + " residences match your filter:\n");
		
		for(int i = 0; i < rsdlist.size(); i++)
			rsdlist.get(i).printPretty();
	}
	
	/**
	 * Method to exit this application after closing open readers.
	 */
	public static void exit() {
		try { br.close(); }
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/**
	 * Primitive clearConsole() method
	 */
	public static void writeLines() {
		for(int i = 0; i < 50; i++)
			System.out.println();
	}
	
	/**
	 * Method to clear the console of any text (not working)
	 * @deprecated
	 */
	public static void clearConsole() {
		try {
			//String os = System.getProperty("os.name");
			
			//if (os.contains("Windows"))
				Runtime.getRuntime().exec("CLS");
			//else
			//	Runtime.getRuntime().exec("clear");
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Read a line from the {@link BufferedReader}
	 * @return String with the line read
	 */
	public static String readLine() {
		String s = "";
		try { s = br.readLine(); }
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * Read a line from the {@link BufferedReader}, split it around <b>regex</b> and check if the amount of {@link String}s is at least <b>min</b>
	 * @param regex regular expression to split around
	 * @param min minimum amount of resulting Strings
	 * @return String[] with the line read split at <b>regex</b>
	 * @throws IOException throws if the number of resulting Strings is less than <b>min</b>
	 */
	public static String[] readLineSplitMin(String regex, int min) throws IOException {
		String s = "";
		try { s = br.readLine(); }
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		String[] temparr = s.split(regex);
		if(temparr.length < min) {
			System.out.println("Invalid input, returning to main menu\n");
			throw new IOException();
		}
		return temparr;
	}
	
	/**
	 * Read a line from the {@link BufferedReader}, split it around <b>regex</b> and check if the amount of {@link String}s is equal to <b>equal</b>
	 * @param regex regular expression to split around
	 * @param equal amount of resulting Strings
	 * @return String[] with the line read split at <b>regex</b>
	 * @throws IOException throws if the number of resulting Strings is not equal to <b>equal</b>
	 */
	public static String[] readLineSplitEquals(String regex, int equal) throws IOException {
		String s = "";
		try { s = br.readLine(); }
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		String[] temparr = s.split(regex);
		if(temparr.length < equal) {
			System.out.println("Invalid input, returning to main menu\n");
			throw new IOException();
		}
		return temparr;
	}

}
