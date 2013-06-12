/**
 * 
 */
package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author bramiejo
 *
 */
public class BurgerTest {

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#Burger()}.
	 */
	@Test
	public void testBurger() {
		Burger b = new Burger();
		assertEquals(true, b instanceof Burger);
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#Burger(java.lang.String, java.lang.String, nl.ica.breas.burgernet.backend.model.OntvangstLocatie, nl.ica.breas.burgernet.backend.model.Adres)}.
	 */
	@Test
	public void testBurgerStringStringLocatieAdres() {
	    Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
                "bramiejo@hotmail.com");
		
		assertEquals(true, b instanceof Burger);
		assertEquals("Bram", b.getVoornaam());
		assertEquals("Arts", b.getAchternaam());
		assertEquals(2.0, b.getLocatie().getLatitude(), 0.01);
		assertEquals(3.0, b.getLocatie().getLongitude(), 0.01);
		assertEquals("harrie van den berg straat", b.getAdres().getStraat());
		assertEquals("Sint Anthonis", b.getAdres().getPlaats());
		assertEquals(4, b.getAdres().getHuisnummer());
		assertEquals("5845HE", b.getAdres().getPostcode());
		
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#getVoornaam()}.
	 */
	@Test
	public void testGetVoornaam() {
	    Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
                "bramiejo@hotmail.com");
		assertEquals("Bram", b.getVoornaam());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#setVoornaam(java.lang.String)}.
	 */
	@Test
	public void testSetVoornaam() {
		Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
				new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
				"bramiejo@hotmail.com");
		b.setVoornaam("Eric");
		assertEquals("Eric", b.getVoornaam());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#getAchternaam()}.
	 */
	@Test
	public void testGetAchternaam() {
	    Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
                "bramiejo@hotmail.com");
		assertEquals("Arts", b.getAchternaam());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#setAchternaam(java.lang.String)}.
	 */
	@Test
	public void testSetAchternaam() {
	    Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
                "bramiejo@hotmail.com");
		b.setAchternaam("Bonestroo");
		assertEquals("Bonestroo", b.getAchternaam());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#getLocatie()}.
	 */
	@Test
	public void testGetLocatie() {
	    Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
                "bramiejo@hotmail.com");
		assertEquals(2.0, b.getLocatie().getLatitude(), 0.01);
		assertEquals(3.0, b.getLocatie().getLongitude(), 0.01);
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#setLocatie(nl.ica.breas.burgernet.backend.model.OntvangstLocatie)}.
	 */
	@Test
	public void testSetLocatie() {
	    Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
                "bramiejo@hotmail.com");
		b.setLocatie(new Locatie(3.0, 2.0));
		assertEquals(3.0, b.getLocatie().getLatitude(), 0.01);
		assertEquals(2.0, b.getLocatie().getLongitude(), 0.01);
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#getAdres()}.
	 */
	@Test
	public void testGetAdres() {
	    Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
                "bramiejo@hotmail.com");
		assertEquals("harrie van den berg straat", b.getAdres().getStraat());
		assertEquals("Sint Anthonis", b.getAdres().getPlaats());
		assertEquals(4, b.getAdres().getHuisnummer());
		assertEquals("5845HE", b.getAdres().getPostcode());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Burger#setAdres(nl.ica.breas.burgernet.backend.model.Adres)}.
	 */
	@Test
	public void testSetAdres() {
	    Burger b = new Burger("Bram", "Arts", new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"),
                "bramiejo@hotmail.com");
		b.setAdres(new Adres("Ruitenlaan", "Arnhem", 26, "1234AB"));
		assertEquals("Ruitenlaan", b.getAdres().getStraat());
		assertEquals("Arnhem", b.getAdres().getPlaats());
		assertEquals(26, b.getAdres().getHuisnummer());
		assertEquals("1234AB", b.getAdres().getPostcode());
	}	
}
