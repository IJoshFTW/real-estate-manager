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
public class ResaleResidenceTest {
	
	Address adr = new Address("Abc", "12", "1234AB", "Someplace");
	
	@Test
	public void testConstructor() {
		System.out.println();
		System.out.println("testConstructor()");
		
		ResaleResidence rsd = new ResaleResidence(adr, 5, 400000, "A");
		assertTrue(rsd.getAddress().equals(adr));
		assertTrue(rsd.equals(rsd));
		assertTrue(rsd.getRooms() == 5);
		assertTrue(rsd.getAskingPrice() == 400000);
		assertTrue(rsd.getEnergyLevel() == "A");
		assertTrue(rsd.getResidenceType() == 1);
		assertTrue(rsd.getStatus() == 1);
		System.out.println("+ PASSED");
	}
	
	@Test
	public void testRead() {
		System.out.println();
		System.out.println("testRead()");
		
		Scanner sc = new Scanner("FOR SALE:\nAbc 12\n1234AB Someplace\n5 rooms\nasking price 400000\nenergy level A");
		Residence rsd = Residence.read(sc);
		assertTrue(rsd.getAddress().equals(adr));
		assertTrue(rsd.equals(rsd));
		assertTrue(rsd.getRooms() == 5);
		assertTrue(rsd.getAskingPrice() == 400000);
		assertTrue(((ResaleResidence) rsd).getEnergyLevel().equals("A"));
		assertTrue(rsd.getResidenceType() == 1);
		assertTrue(rsd.getStatus() == 1);
		System.out.println("+ PASSED");
	}

}
