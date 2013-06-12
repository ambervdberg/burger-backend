package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;

import java.util.Calendar;

import nl.ica.breas.burgernet.backend.exceptions.OnvolledigeMeldingException;
import nl.ica.breas.burgernet.backend.model.Melding;

import org.junit.Test;

public class MeldingTest {
	
	/**
	 * Hier wordt de constructor van Melding getest.
	 * We maken een nieuwe instantie van Melding aan.
	 * Vervolgens wordt gecontroleerd of het aangemaakte object een Melding is.
	 */
	@Test
	public void testMeldingConsturctor() {
		Melding melding = new Melding();
		assertEquals("Melding", melding.getClass().getSimpleName());
	}
	
	/**
	 * Hier wordt de constructor van Melding getest.
	 * We maken een nieuwe instantie van Melding aan met een parameter.
	 * Vervolgens wordt gecontroleerd of het aangemaakte object een Melding is.
	 */
	@Test
	public void testMelding() {
		Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34), new Bijlage(), Calendar.getInstance());
		assertEquals("Melding", melding.getClass().getSimpleName());
	}
	/**
	 * Hier wordt de getter van Melding getest.
	 * We maken een nieuwe instantie van Melding aan met een meegegeven beschrijving.
	 * Vervolgens wordt de getter getest, 
	 * door middel van de verwachte output en de werkelijke output.
	 */
	@Test
	public void testGetBeschrijving() {
		Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34),  new Bijlage(), Calendar.getInstance());
		assertEquals("test", melding.getBeschrijving());
	}
	
	/**
	 * Hier wordt de setter van Melding getest.
	 * Wij maken een nieuwe instantie van Melding aan, zonder beschrijving,
	 * voegen vervolgens een beschrijving toe m.b.v. de setter,
	 * en vergelijken dan de werkelijke output met de verwachte output. 
	 */
	@Test
	public void testSetBeschrijving() {
		Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34),  new Bijlage(), Calendar.getInstance());
		melding.setBeschrijving("testje");
		assertEquals("testje", melding.getBeschrijving());
	}
	/**
	 * Met deze test word getest of de categorie getter werkt 
	 */
	@Test
	public void testGetCategorie() {
		Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34), new Bijlage(), Calendar.getInstance());
		AbstractCategorie c = melding.getCategorie();
		assertEquals(true,c instanceof HerhalendeCategorie);
	}
	/**
	 * Met deze test word getest of de categorie setter werkt 
	 */
	@Test
	public void testSetCategorie() {
		Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34), new Bijlage(), Calendar.getInstance());
		melding.setCategorie(new HerhalendeCategorie("poep"));
		AbstractCategorie c = melding.getCategorie();
		assertEquals(true,c instanceof HerhalendeCategorie);
	}
	/**
	 * Met deze test wordt getest of de locatie getter en setter werkt 
	 */
	@Test
	public void testGetSetLocatie() {
		Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34), new Bijlage(), Calendar.getInstance());
		Locatie locatie = new Locatie();
		melding.setLocatie(locatie);
		assertEquals(locatie,melding.getLocatie());
	}
	/**
	 * MEt deze test worden de getters en setters van bijlage getest
	 */
	@Test
	public void testGetSetBijlage(){
		Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34), new Bijlage(),Calendar.getInstance());
		Bijlage b = new Bijlage();
		melding.setBijlage(b);
		assertEquals(b,melding.getBijlage());
	}
	/**
     * Met deze test worden de getters en setters van aangepasteStraal getest. er wordt ook getest of aangepasteStraal standaart op false staat.
     */
    @Test
    public void testGetSetAangepasteStraal(){
        Melding melding = new Melding();
        assertEquals(false,melding.isAangepasteStraal());
        melding.setAangepasteStraal(true);
        assertEquals(true,melding.isAangepasteStraal());
    }
    @Test(expected=OnvolledigeMeldingException.class)
    public void testZetVerloopDatumFout() throws OnvolledigeMeldingException{
        Melding m = new Melding("test", new DummyCategorie(), new Locatie(437.23,234.34), new Bijlage(), Calendar.getInstance());
        m.zetVerloopDatumVast();
    }
    /**
     * Met deze test worden de getters en setters van burgerID getest. er wordt ook getest of aangepasteStraal standaart op false staat.
     */
    @Test
    public void testGetSetburgerID(){
        Melding melding = new Melding();
        melding.setBurgerId("50d07c53aa65773f10584000");
        assertEquals("50d07c53aa65773f10584000", melding.getBurgerId());
    }
}
