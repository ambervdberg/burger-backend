package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractCategorieTest {

	/**
	 * Met deze test word de constructor van categorie getest.
	 */
	@Test
	public void testConstructor() {
		AbstractCategorie c = new HerhalendeCategorie("test");
		assertEquals(true, c instanceof AbstractCategorie);
	}
	/**
	 * Met deze test word de constructor van categorie getest.
	 */
	@Test
	public void testEmptyConstructor() {
		AbstractCategorie c = new HerhalendeCategorie();
		assertEquals(true, c instanceof AbstractCategorie);
	}
	/**
	 * Met deze test word het ophalen van de naam uit een categorie object getest.
	 */
	@Test
	public void testGetNaam() {
		AbstractCategorie c = new HerhalendeCategorie("test");
		assertEquals("test",c.getNaam());
	}
	/**
	 * Met deze tester word de naam setter getest.
	 */
	@Test
	public void testSetNaam() {
		AbstractCategorie c = new HerhalendeCategorie("test");
		c.setNaam("test2");
		assertEquals("test2",c.getNaam());
	}
	/**
	 * Met deze test wordt getest of de straal getter en setter werkt 
	 */
	@Test
	public void testGetSetStraal() {
		AbstractCategorie categorie = new NietHerhalendeCategorie();
		double straal = 20;
		categorie.setStraal(straal);
		assertEquals(straal, categorie.getStraal(), 0.01);
	}
	/**
	 * Met deze test wordt getest of de time to live getter en setter werkt 
	 */
	@Test
	public void testGetSetTimeToLive() {
		AbstractCategorie categorie = new NietHerhalendeCategorie();
		int timeToLive = 2000;
		categorie.setTimeToLive(timeToLive);
		assertEquals(timeToLive, categorie.getTimeToLive());
	}

}
