package realEstateManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class Application {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Portfolio port = new Portfolio();
	static String savefile = "residences.txt";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		port = Portfolio.read(savefile);
		
		/*try {
		Scanner sc = new Scanner(new File(savefile));
		System.out.println(sc.nextLine());
		System.out.println(sc.nextLine());
		System.out.println(sc.nextLine());
		}
		catch (Exception e) {
			
		}*/
		
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
			
			try { inputstr = br.readLine(); }
			catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try { inputint = Integer.parseInt(inputstr); }
			catch (NumberFormatException e) {
				System.out.println("Invalid input.");
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
				System.out.println("Invalid input\n");
			}
		}			
	}
	
	public static void addResale() {
		String street, number, postalCode, place, energyLevel;
		int rooms, price;
		System.out.println("You are about to add a new resale residence.\n");
		
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
		try { strarr = readLineSplitNotEquals(" ", 2); }
		catch (IOException e) {
			return;
		}
		postalCode = strarr[0];
		place = strarr[1];
		Address adr = new Address(street, number, postalCode, place);
		
		// Enter number of rooms
		System.out.println("Please enter a number of rooms.");
		rooms = Integer.parseInt(readLine());
		
		// Enter price
		System.out.println("Please enter a price.");
		price = Integer.parseInt(readLine());
		
		// Enter energy level
		System.out.println("Please enter an energy level.");
		energyLevel = readLine();
		
		Residence rsd = new ResaleResidence(adr, rooms, price, energyLevel);
		port.addResidence(rsd, savefile);
		System.out.println(port.getAllResidences().toString());
		writeLines();
	}
	
	public static void addRental() {
		String street, number, postalCode, place;
		int rooms, price;
		System.out.println("You are about to add a new rental residence.\n");
		
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
		try { strarr = readLineSplitNotEquals(" ", 2); }
		catch (IOException e) {
			return;
		}
		postalCode = strarr[0];
		place = strarr[1];
		Address adr = new Address(street, number, postalCode, place);
		
		// Enter number of rooms
		System.out.println("Please enter a number of rooms.");
		rooms = Integer.parseInt(readLine());
		
		// Enter price
		System.out.println("Please enter a price.");
		price = Integer.parseInt(readLine());
		
		Residence rsd = new RentalResidence(adr, rooms, price);
		port.addResidence(rsd, savefile);
		System.out.println(port.getAllResidences().toString());
		writeLines();
	}
	
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
			System.out.println("Invalid input\n");
			return;
		}
		System.out.println("Enter a maximum price for the residences. 0 will skip this step.");
		price = Integer.parseInt(readLine());
		
		Portfolio resaleport = port.copyAllResidences(1);
		try {
			rsdlist = resaleport.residencesUpto(price, status);
		}
		catch (NegativeException e) {
			System.out.println("Invalid input\n");
			return;
		}
		
		System.out.println("\n\n" + rsdlist.size() + " residences match your filter:\n");
		
		for(int i = 0; i < rsdlist.size(); i++)
			rsdlist.get(i).printPretty();
	}
	
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
			System.out.println("Invalid input\n");
			return;
		}
		System.out.println("Enter a maximum price for the residences. 0 will skip this step.");
		price = Integer.parseInt(readLine());
		
		Portfolio rentalport = port.copyAllResidences(2);
		try {
			rsdlist = rentalport.residencesUpto(price, status);
		}
		catch (NegativeException e) {
			System.out.println("Invalid input\n");
			return;
		}
		
		System.out.println("\n\n" + rsdlist.size() + " residences match your filter:\n");
		
		for(int i = 0; i < rsdlist.size(); i++)
			rsdlist.get(i).printPretty();
	}
	
	public static void exit() {
		try { br.close(); }
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public static void writeLines() {
		for(int i = 0; i < 50; i++)
			System.out.println();
	}
	
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
	
	public static String readLine() {
		String s = "";
		try { s = br.readLine(); }
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return s;
	}
	
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
	
	public static String[] readLineSplitNotEquals(String regex, int notEqual) throws IOException {
		String s = "";
		try { s = br.readLine(); }
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		String[] temparr = s.split(regex);
		if(temparr.length < notEqual) {
			System.out.println("Invalid input, returning to main menu\n");
			throw new IOException();
		}
		return temparr;
	}

}
