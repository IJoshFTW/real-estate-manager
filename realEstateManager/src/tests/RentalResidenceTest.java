package tests;

import realEstateManager.*;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

/**
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class RentalResidenceTest {
	
	Address adr = new Address("Abc", "12", "1234AB", "Someplace");
	
	@Test
	public void testConstructor() {
		System.out.println();
		System.out.println("testConstructor()");
		
		RentalResidence rsd = new RentalResidence(adr, 5, 400000);
		assertTrue(rsd.getAddress().equals(adr));
		assertTrue(rsd.equals(rsd));
		assertTrue(rsd.getRooms() == 5);
		assertTrue(rsd.getAskingPrice() == 400000);
		assertTrue(rsd.getResidenceType() == 2);
		assertTrue(rsd.getStatus() == 1);
		System.out.println("+ PASSED");
	}
	
	@Test
	public void testRead() {
		System.out.println();
		System.out.println("testRead()");
		
		Scanner sc = new Scanner("FOR RENT:\nAbc 12\n1234AB Someplace\n2 rooms\nasking price 400");
		Residence rsd = Residence.read(sc);
		assertTrue(rsd.getAddress().equals(adr));
		assertTrue(rsd.equals(rsd));
		assertTrue(rsd.getRooms() == 2);
		assertTrue(rsd.getAskingPrice() == 400);
		assertTrue(rsd.getResidenceType() == 2);
		assertTrue(rsd.getStatus() == 1);
		System.out.println("+ PASSED");
	}

}
