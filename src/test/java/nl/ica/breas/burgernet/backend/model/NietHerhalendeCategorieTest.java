package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NietHerhalendeCategorieTest {

    /**
     * Met deze test wordt gekeken of de NietHerhalendeCategorie constructor werkt.
     */
    @Test
    public void testNietHerhalendeCategorie() {
        NietHerhalendeCategorie c = new NietHerhalendeCategorie("naam");
        assertEquals(true,c instanceof NietHerhalendeCategorie);
    }
    /**
     * Met deze test wordt gekeken of de NietHerhalendeCategorie constructor werkt.
     */
    @Test
    public void testLegeNietHerhalendeCategorie() {
        NietHerhalendeCategorie c = new NietHerhalendeCategorie();
        assertEquals(true,c instanceof NietHerhalendeCategorie);
    }
    /**
     * Test het kopieren van een categorie.
     */
    @Test
    public void testCopy() {
        NietHerhalendeCategorie oudeCategorie = new NietHerhalendeCategorie();
        oudeCategorie.setNaam("hoi");
        oudeCategorie.setStraal(3.7);
        oudeCategorie.setTimeToLive(9870);
        NietHerhalendeCategorie nieuweCategorie = (NietHerhalendeCategorie) oudeCategorie.copy();
        assertEquals(oudeCategorie.getNaam(), nieuweCategorie.getNaam());
        assertEquals(0.1, oudeCategorie.getStraal(), nieuweCategorie.getStraal());
        assertEquals(oudeCategorie.getTimeToLive(), nieuweCategorie.getTimeToLive());
        oudeCategorie.setNaam("hallo");
        assertEquals("hoi", nieuweCategorie.getNaam());
    }
}
