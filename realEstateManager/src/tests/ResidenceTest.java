package tests;

import realEstateManager.*;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

/**
 * JUnit Class for testing Residence integration.
 * 
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 */
public class ResidenceTest {

	Address myaddress = new Address("Rijksstraatweg", "570", "2026 RB", "HAARLEM");
	Address youraddress = myaddress;
	Address hisaddress = new Address("Street of Sisters", "123", "1234 AB", "KINGS LANDING");
	Residence ResidenceA = new Residence(myaddress, 10, 450000);
	Residence ResidenceAA = ResidenceA;
	Residence ResidenceB = new Residence(hisaddress, 4, 240000);
	
	@Test
	public void test_equals() {
		System.out.println();
		System.out.println("test_equals()");
		assertTrue(ResidenceA.equals(ResidenceAA) == true);
		assertTrue(ResidenceA.equals(ResidenceB) == false);
		assertTrue(ResidenceB.equals(ResidenceB) == true);
		assertTrue(ResidenceA.equals(null) == false);
		System.out.println("+ PASSED");
	}
	
	@Test
	public void test_toString() {
		System.out.println();
		System.out.println("test_toString()");
		assertTrue(ResidenceA.toString().equals("<Residence[<Address[Rijksstraatweg,570,2026 RB,HAARLEM]>,10,450000,0 1]>") == true);
		assertTrue(ResidenceB.toString().equals("<Residence[<Address[Street of Sisters,123,1234 AB,KINGS LANDING]>,4,240000,0 1]>") == true);
		System.out.println("+ PASSED");
	}
	
	@Test
	public void test_kostHooguit() {
		System.out.println();
		System.out.println("test_kostHooguit()");
		assertTrue(ResidenceA.maxCost(0) == false);
		assertTrue(ResidenceA.maxCost(449999) == false);
		assertTrue(ResidenceA.maxCost(450000) == true);
		assertTrue(ResidenceA.maxCost(1000000) == true);
		System.out.println("+ PASSED");
	}
	
	@Test
	public void test_read() {
		System.out.println();
		System.out.println("test_read()");
		
		Scanner sc = new Scanner("FOR SALE:\nEmmalaan 23\n3051JC Rotterdam\n7 rooms\nprice 300000\nenergy level A\nSOLD:\nStreetname with spaces 123\n1234AB Someplace\n2 rooms\nprice 12345\nenergy level C");
		
		Residence rsd = Residence.read(sc);
		assertTrue(rsd.getAddress().getStreet().equals("Emmalaan"));
		assertTrue(rsd.getAddress().getNumber().equals("23"));
		assertTrue(rsd.getAddress().getPostalCode().equals("3051JC"));
		assertTrue(rsd.getAddress().getPlace().equals("Rotterdam"));
		assertTrue(rsd.getRooms() == 7);
		assertTrue(rsd.getAskingPrice() == 300000);
		assertTrue(rsd.getResidenceType() == 1);
		assertTrue(rsd.getStatus() == 1);
		
		rsd = Residence.read(sc);
		assertTrue(rsd.getAddress().getStreet().equals("Streetname with spaces"));
		assertTrue(rsd.getAddress().getNumber().equals("123"));
		assertTrue(rsd.getAddress().getPostalCode().equals("1234AB"));
		assertTrue(rsd.getAddress().getPlace().equals("Someplace"));
		assertTrue(rsd.getRooms() == 2);
		assertTrue(rsd.getAskingPrice() == 12345);
		assertTrue(rsd.getResidenceType() == 1);
		assertTrue(rsd.getStatus() == 0);
		System.out.println("+ PASSED");
	}

}
