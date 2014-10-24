package tests;

import realEstateManager.*;
import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;

/**
 * JUnit Class for testing Address integration.
 * 
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class AddressTest {
	
	Address myaddress = new Address("Rijksstraatweg", "570", "2026RB", "HAARLEM");
	Address youraddress = myaddress;
	Address hisaddress = new Address("Street of Sisters", "123", "1234AB", "KINGS LANDING");
	
	@Test
	public void testEquals() {
		System.out.println();
		System.out.println("test_equals()");
		assertTrue(myaddress.equals(youraddress) == true);
		assertTrue(myaddress.equals(hisaddress) == false);
		assertTrue(hisaddress.equals(hisaddress) == true);
		assertTrue(myaddress.equals(null) == false);
		System.out.println("+ PASSED");
	}
	
	@Test
	public void testToString() {
		System.out.println();
		System.out.println("test_toString()");
		assertTrue(myaddress.toString().equals("<Address[Rijksstraatweg,570,2026RB,HAARLEM]>"));
		assertTrue(youraddress.toString().equals("<Address[Rijksstraatweg,570,2026RB,HAARLEM]>"));
		assertTrue(hisaddress.toString().equals("<Address[Street of Sisters,123,1234AB,KINGS LANDING]>"));
		System.out.println("+ PASSED");
	}
	
	@Test
	public void testRead() {
		System.out.println();
		System.out.println("test_read()");
		
		Scanner sc = new Scanner("Rijksstraatweg 570\n2026RB Haarlem\nStreetname with spaces 123\n1234AB Someplace");

		Address newaddress = Address.read(sc);
		assertTrue(newaddress.getStreet().equals("Rijksstraatweg"));
		assertTrue(newaddress.getNumber().equals("570"));
		assertTrue(newaddress.getPostalCode().equals("2026RB"));
		assertTrue(newaddress.getPlace().equals("Haarlem"));
		
		newaddress = Address.read(sc);
		assertTrue(newaddress.getStreet().equals("Streetname with spaces"));
		assertTrue(newaddress.getNumber().equals("123"));
		assertTrue(newaddress.getPostalCode().equals("1234AB"));
		assertTrue(newaddress.getPlace().equals("Someplace"));
		System.out.println("+ PASSED");
	}

}
