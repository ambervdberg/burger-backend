package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class HerhalendeCategorieTest {
	/**
	 * Met deze test wordt gekeken of de herhalendecategorie constructor werkt.
	 */
	@Test
	public void testHerhalendeCategorie() {
		HerhalendeCategorie h = new HerhalendeCategorie("naam");
		assertEquals(true,h instanceof HerhalendeCategorie);
	}
	/**
	 * Met deze test wordt gekeken of de herhalendecategorie constructor werkt.
	 */
	@Test
	public void testLegeHerhalendeCategorie() {
		HerhalendeCategorie h = new HerhalendeCategorie();
		assertEquals(true,h instanceof HerhalendeCategorie);
	}
	
	/**
	 * Met deze test wordt getest of de aantalKeerHerhalen getter en setter werkt 
	 */
	@Test
	public void testGetSetAantalKeerHerhalen() {
		HerhalendeCategorie categorie = new HerhalendeCategorie();
		int aantalKeerHerhalen = 2000;
		categorie.setAantalKeerHerhalen(aantalKeerHerhalen);
		assertEquals(aantalKeerHerhalen,categorie.getAantalKeerHerhalen());
	}
	/**
	 * Met deze test wordt getest of de herhaalTijd getter en setter werkt 
	 */
	@Test
	public void testGetSetHerhaalTijd() {
		HerhalendeCategorie categorie = new HerhalendeCategorie();
		int herhaalTijd = 2000;
		categorie.setHerhaalTijd(herhaalTijd);
		assertEquals(herhaalTijd,categorie.getHerhaalTijd());
	}
    /**
     * Test het kopieren van een categorie.
     */
    @Test
    public void testCopy() {
        HerhalendeCategorie oudeCategorie = new HerhalendeCategorie();
        oudeCategorie.setNaam("hoi");
        oudeCategorie.setStraal(3.7);
        oudeCategorie.setTimeToLive(9870);
        HerhalendeCategorie nieuweCategorie = (HerhalendeCategorie) oudeCategorie.copy();
        assertEquals(oudeCategorie.getNaam(), nieuweCategorie.getNaam());
        assertEquals(0.1, oudeCategorie.getStraal(), nieuweCategorie.getStraal());
        assertEquals(oudeCategorie.getTimeToLive(), nieuweCategorie.getTimeToLive());
        assertEquals(oudeCategorie.getAantalKeerHerhalen(), nieuweCategorie.getAantalKeerHerhalen());
        assertEquals(oudeCategorie.getHerhaalTijd(), nieuweCategorie.getHerhaalTijd());
        oudeCategorie.setNaam("hallo");
        assertEquals("hoi", nieuweCategorie.getNaam());
    }
}
