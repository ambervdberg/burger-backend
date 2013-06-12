package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdresTest {


	/**
	 * met deze test word de lege constructor getest 
	 */
	@Test
	public void testAdres() {
		Adres a = new Adres();
		assertEquals(true, a instanceof Adres);
	}
	/**
	 * Met deze test worden de getters en de constructor met argumenten getest
	 */
	@Test
	public void testAdresStringStringIntString() {
		Adres a = new Adres("Straatnaam","Barneveld",98,"5443GH");
		assertEquals(true, a instanceof Adres);
		assertEquals("Straatnaam",a.getStraat());
		assertEquals("Barneveld",a.getPlaats());
		assertEquals(98,a.getHuisnummer());
		assertEquals("5443GH",a.getPostcode());
	}
	/**
	 * met deze test word de setter getest voor de straat.
	 */
	@Test
	public void testSetStraat() {
		Adres a = new Adres();
		a.setStraat("straat5");
		assertEquals("straat5",a.getStraat());
	}
	/**
	 * 
	 */
	@Test
	public void testSetPlaats() {
		Adres a = new Adres();
		a.setPlaats("plaats1337");
		assertEquals("plaats1337",a.getPlaats());
	}

	@Test
	public void testSetHuisnummer() {
		Adres a = new Adres();
		a.setHuisnummer(5);
		assertEquals(5,a.getHuisnummer());
	}

	@Test
	public void testSetPostcode() {
		Adres a = new Adres();
		a.setPostcode("postcode");
		assertEquals("postcode",a.getPostcode());
	}

}
