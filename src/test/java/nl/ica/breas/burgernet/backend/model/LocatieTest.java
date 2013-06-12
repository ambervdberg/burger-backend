package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;

import nl.ica.breas.burgernet.backend.model.Locatie;
import org.junit.Test;
/**
 * Dit is de tester voor de Locatie class.
 * @author bramiejo
 *
 */
public class LocatieTest {

	/**
	 * Dit test de getter voor longitude.
	 * verwachte uitvoer is 9.9 en de daadwerkelijke uitvoer is 9.9
	 */
	@Test
	public void testGetLongitude() {
		Locatie locatie = new Locatie(10.11, 9.9);
		assertEquals(9.9, locatie.getLongitude(), 0.01);
	}
	/**
	 * Dit is de setter voor longitude.
	 * Er wordt eerst een instantie van Locatie gemaakt.
	 * Daarna wordt de waarde van longitude geset op 3.3.
	 * De verwachte uitvoer is dan ook 3.3 en
	 * deze geeft bij deze test ook 3.3
	 */
	@Test
	public void testSetLongitude() {
		Locatie locatie = new Locatie(5.5, 6.6);
		locatie.setLongitude(3.3);
		assertEquals(3.3, locatie.getLongitude(), 0.01);
	}
	/**
	 * Dit test de getter voor longitude.
	 * verwachte uitvoer is 1.1 en de daadwerkelijke uitvoer is 1.1
	 */
	@Test
	public void testGetLatitude() {
		Locatie locatie = new Locatie(1.1, 7.8);
		assertEquals(1.1, locatie.getLatitude(), 0.01);		
	}
	/**
	 * Dit is de setter voor latitude.
	 * Er wordt eerst een instantie van Locatie gemaakt.
	 * Daarna wordt de waarde van latitude geset op 3.3001.
	 * De verwachte uitvoer is dan ook 3.3001
	 * en de daadwerkelijke uitvoer is 3.3001
	 */
	@Test
	public void testSetLatitude() {
		Locatie locatie = new Locatie(9.7, 5.4);
		locatie.setLatitude(3.3001);
		assertEquals(3.3001, locatie.getLatitude(), 0.1);
	}
	/**
	 * Dit is de lege constructor.
	 * Hier wordt een instantie gemaakt van Locatie
	 * en daarna wordt getest of het object bestaat.
	 */
	@Test
	public void testConstructorLocatie() {
		Locatie locatie = new Locatie();
		assertEquals("Locatie", locatie.getClass().getSimpleName());
	}
	/**
	 * Dit is de constructor met parameters.
	 * Hier wordt een instantie gemaakt van Locatie.
	 * en daarna wordt getest of het object bestaat.
	 * en worden de waarden getest met getLongitude en getLatitude.
	 */	
	@Test
	public void testConstructorLocatieWithPara() {
		Locatie locatie = new Locatie(3.3, 5.5);
		assertEquals("Locatie", locatie.getClass().getSimpleName());
		assertEquals(3.3, locatie.getLatitude(), 0.01);
		assertEquals(5.5, locatie.getLongitude(), 0.01);
	}
}
