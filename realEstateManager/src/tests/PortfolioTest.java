package tests;

import realEstateManager.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 *
 * @author <a href="http://www.joshuaslik.nl/" target="_blank">Joshua Slik</a>
 *
 */
public class PortfolioTest {
	
	Portfolio nullPort = new Portfolio();
	Address myaddress = new Address("Rijksstraatweg", "570", "2026 RB", "HAARLEM");
	Address hisaddress = new Address("Street of Sisters", "123", "1234 AB", "KINGS LANDING");
	Residence ResidenceA = new Residence(myaddress, 10, 450000);
	Residence ResidenceB = new Residence(hisaddress, 4, 240000);
	
	@Test
	public void test_voegToe() {
		System.out.println();
		System.out.println("test_voegToe()");
		Portfolio newport = new Portfolio();
		assertTrue(newport.getAllResidences().size() == 0);
		newport.addResidence(ResidenceA);
		newport.addResidence(ResidenceB);
		assertTrue(newport.getAllResidences().size() == 2);
		assertTrue(newport.getAllResidences().contains(ResidenceA));
		assertTrue(newport.getAllResidences().contains(ResidenceB));
		System.out.println("+ PASSED");
	}
	
	@Test
	public void test_ResidenceenTot() {
		System.out.println();
		System.out.println("test_ResidenceenTot()");
		Portfolio newport = new Portfolio();
		try {
			assertTrue(newport.residencesUpto(0).size() == 0);
			newport.addResidence(ResidenceA);
			newport.addResidence(ResidenceB);
			assertTrue(newport.residencesUpto(0).size() == 2);
			assertTrue(newport.residencesUpto(0).contains(ResidenceA));
			assertTrue(newport.residencesUpto(0).contains(ResidenceB));
			assertTrue(newport.residencesUpto(1).size() == 0);
			assertTrue(newport.residencesUpto(240000).size() == 1);
			assertTrue(newport.residencesUpto(240000).contains(ResidenceB));
			assertTrue(newport.residencesUpto(450000).size() == 2);
			assertTrue(newport.residencesUpto(450000).contains(ResidenceA));
			assertTrue(newport.residencesUpto(450000).contains(ResidenceB));
		} catch (NegativeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("+ PASSED");
	}
	
	@Test
	public void test_read() {
		System.out.println();
		System.out.println("test_read()");

		Portfolio newport = Portfolio.read("PortfolioTest.txt");
		ArrayList<Residence> al = newport.getAllResidences();
		assertTrue(al.size() == 2);
		assertTrue(al.get(0).getAddress().getStreet().equals("Emmalaan"));
		assertTrue(al.get(0).getAddress().getNumber().equals("23"));
		assertTrue(al.get(0).getAddress().getPostalCode().equals("3051JC"));
		assertTrue(al.get(0).getAddress().getPlace().equals("Rotterdam"));
		assertTrue(al.get(0).getRooms() == 7);
		assertTrue(al.get(0).getAskingPrice() == 300000);
		assertTrue(al.get(0).getResidenceType() == 1);
		assertTrue(al.get(0).getStatus() == 1);
		
		assertTrue(al.get(1).getAddress().getStreet().equals("Streetname with spaces"));
		assertTrue(al.get(1).getAddress().getNumber().equals("123"));
		assertTrue(al.get(1).getAddress().getPostalCode().equals("1234AB"));
		assertTrue(al.get(1).getAddress().getPlace().equals("Delft"));
		assertTrue(al.get(1).getRooms() == 2);
		assertTrue(al.get(1).getAskingPrice() == 12345);
		System.out.println("+ PASSED");
	}

}
