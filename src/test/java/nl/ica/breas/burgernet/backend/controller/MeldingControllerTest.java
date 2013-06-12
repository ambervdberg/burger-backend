package nl.ica.breas.burgernet.backend.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Adres;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.AbstractCategorie;
import nl.ica.breas.burgernet.backend.model.CategorieenMap;
import nl.ica.breas.burgernet.backend.model.DummyCategorie;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;
import nl.ica.breas.burgernet.backend.model.NietHerhalendeCategorie;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;
import nl.ica.breas.burgernet.backend.persistence.MongoDB;
import nl.ica.breas.burgernet.backend.push.PushServerVoorbeeld;
import static org.mockito.Mockito.*;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * 
 * @author Eric en Samuel
 * @version 0.05
 * @since 27-11-2012
 *
 */
public class MeldingControllerTest {
	/**
	 * De meldingcontroller
	 */
	static MeldingController mc;
	/**
	 * De lijst met burgers
	 */
	static List<Burger> lijst;
	/** Deze wordt gebruikt om de erros te loggen. */
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");
	/**
	 * initialiseer de lijst en de MeldingController
	 * @throws UnknownHostException 
	 */
	@BeforeClass
	public static void testMetMockTest() throws UnknownHostException {
		lijst = new ArrayList<Burger>();
		lijst.add(new Burger("Samuel", "blaat", 
				new Locatie(), new Adres(), "bramiejo@hotmail.com"));
		
		AbstractCategorie[] categorieLijst = new AbstractCategorie[1];
		AbstractCategorie testCat = new NietHerhalendeCategorie("test");
		testCat.setStraal(100);
		testCat.setTimeToLive(3600);
		categorieLijst[0] = testCat;
		CategorieenMap categorieenMap = new CategorieenMap(categorieLijst);
		AbstractPersistenceAdapter.setIpadres("149.5.47.170");
		AbstractPersistenceAdapter.setPoort(27017);
		AbstractPersistenceAdapter.setDatabaseNaam("breas");
		AbstractPersistenceAdapter.setDatabaseInlogWachtwoord("breas2012");
		mc = new MeldingController(categorieenMap, new MongoDB(), new PushServerVoorbeeld());
	}
	
	/**
	 * Met deze test word de constructor van controller getest.
	 */
	@Test
	public void testConstructor() {
		assertEquals(true, mc instanceof MeldingController);
	}
	/**
	 * De verwerkingtest. Hiermee wordt de werking van de verwerking van een melding getest. 
	 */
	@Test
	public void testVerwerkMeldingBestaandeCategorie() {
		try {
			AbstractPersistenceAdapter adapter = mock(AbstractPersistenceAdapter.class);
			when(adapter.selecteerBurgers(any(Melding.class))).thenReturn(lijst);
			when(adapter.slaMeldingOp(any(Melding.class))).thenReturn("50be0b91596bf2cc4ae1826f");
			mc.setAdapter(adapter);
		} catch (Exception e) {
			LOGGER.trace(e);
		} 
		Melding m = new Melding();
		AbstractCategorie c = new DummyCategorie();
		c.setNaam("test");
		m.setCategorie(c);
		Status s = mc.verwerkMeldingBurger(m);
		assertEquals(true, s instanceof Status);
	}
	   /**
     * De verwerkingtest. Hiermee wordt de werking van de verwerking van een melding getest. 
     */
    @Test
    public void testVerwerkMeldingAangepasteStraal() {
        try {
            AbstractPersistenceAdapter adapter = mock(AbstractPersistenceAdapter.class);
            when(adapter.selecteerBurgers(any(Melding.class))).thenReturn(lijst);
            when(adapter.slaMeldingOp(any(Melding.class))).thenReturn("50be0b91596bf2cc4ae1826f");
            mc.setAdapter(adapter);
        } catch (Exception e) {
            LOGGER.trace(e);
        } 
        Melding m = new Melding();
        AbstractCategorie c = new DummyCategorie();
        c.setNaam("test");
        c.setStraal(200.0);
        m.setCategorie(c);
        Status s = mc.verwerkMeldingOverheid(m);
        assertEquals(true, s instanceof Status);
        assertEquals(200.0, m.getCategorie().getStraal(), 0.01);
        assertEquals(3600, m.getCategorie().getTimeToLive());
    }
    /**
  * De verwerkingtest. Hiermee wordt de werking van de verwerking van een melding getest. 
  */
 @Test
 public void testVerwerkMeldingAangepasteTTL() {
     try {
         AbstractPersistenceAdapter adapter = mock(AbstractPersistenceAdapter.class);
         when(adapter.selecteerBurgers(any(Melding.class))).thenReturn(lijst);
         when(adapter.slaMeldingOp(any(Melding.class))).thenReturn("50be0b91596bf2cc4ae1826f");
         mc.setAdapter(adapter);
     } catch (Exception e) {
         LOGGER.trace(e);
     } 
     Melding m = new Melding();
     AbstractCategorie c = new DummyCategorie();
     c.setNaam("test");
     c.setTimeToLive(4000);
     m.setCategorie(c);
     Status s = mc.verwerkMeldingOverheid(m);
     assertEquals(true, s instanceof Status);
     assertEquals(100.0, m.getCategorie().getStraal(), 0.01);
     assertEquals(4000,m.getCategorie().getTimeToLive());
 }
	/**
	 * De verwerkingtest. Hiermee wordt de werking van de verwerking van een melding met een onjuiste categorie getest. 
	 */
	@Test
	public void testVerwerkMeldingNietBestaandeCategorieBurger() {
		Melding m = new Melding();
		AbstractCategorie c = new DummyCategorie();
        c.setNaam("ksdhjfkjhkjha");
        m.setCategorie(c);
		assertEquals(false, mc.verwerkMeldingBurger(m).isBevestiging());	
	}
	   /**
     * De verwerkingtest. Hiermee wordt de werking van de verwerking van een melding met een onjuiste categorie getest. 
     */
    @Test
    public void testVerwerkMeldingNietBestaandeCategorieOverheid() {
        Melding m = new Melding();
        AbstractCategorie c = new DummyCategorie();
        c.setNaam("ksdhjfkjhkjha");
        m.setCategorie(c);
        assertEquals(false, mc.verwerkMeldingOverheid(m).isBevestiging());    
    }
	/**
	 * Test een PersistenceConnectionException.
	 */
	@Test
	public void testPersistenceConnectionException() {
		try {
			AbstractPersistenceAdapter adapter = mock(AbstractPersistenceAdapter.class);
			when(adapter.selecteerBurgers(any(Melding.class))).thenReturn(lijst);
			when(adapter.slaMeldingOp(any(Melding.class))).thenThrow(
					new PersistenceConnectionException("Test"));
			mc.setAdapter(adapter);
		} catch (Exception e) {
			LOGGER.trace(e);
		}
		Melding m = new Melding();
		AbstractCategorie c = new DummyCategorie();
        c.setNaam("test");
        m.setCategorie(c);
		assertEquals(false, mc.verwerkMeldingBurger(m).isBevestiging());
	}
	/**
	 * Test een PersistenceDuplicateKeyException.
	 */
	@Test
	public void testPersistenceDuplicateKeyException() {
		try {
			AbstractPersistenceAdapter adapter = mock(AbstractPersistenceAdapter.class);
			when(adapter.selecteerBurgers(any(Melding.class))).thenReturn(lijst);
			when(adapter.slaMeldingOp(any(Melding.class))).thenThrow(
					new PersistenceDuplicateKeyException("Test2"));
			mc.setAdapter(adapter);
		} catch (Exception e) {
			LOGGER.trace(e);
		}
		Melding m = new Melding();
		AbstractCategorie c = new DummyCategorie();
        c.setNaam("test");
        m.setCategorie(c);
		assertEquals(false, mc.verwerkMeldingBurger(m).isBevestiging());
	}
	/**
	 * Test een IOException.
	 */
	@Test
	public void testIOException() {
		try {
			AbstractPersistenceAdapter adapter = mock(AbstractPersistenceAdapter.class);
			when(adapter.selecteerBurgers(any(Melding.class))).thenReturn(lijst);
			when(adapter.slaMeldingOp(any(Melding.class))).thenThrow(new IOException("Test4"));
			mc.setAdapter(adapter);
		} catch (Exception e) {
			LOGGER.trace(e);
		}
		Melding m = new Melding();
		AbstractCategorie c = new DummyCategorie();
        c.setNaam("test");
        m.setCategorie(c);
		assertEquals(false, mc.verwerkMeldingBurger(m).isBevestiging());
	}
	/**
	 * Test een PersistenceInternalException.
	 */
	@Test
	public void testPersistenceInternalException() {
		try {
			AbstractPersistenceAdapter adapter = mock(AbstractPersistenceAdapter.class);
		 	when(adapter.selecteerBurgers(any(Melding.class))).thenThrow(new PersistenceInternalException());
			mc.setAdapter(adapter);
		} catch (Exception e) {
			LOGGER.trace(e);
		}
		Melding m = new Melding();
		AbstractCategorie c = new DummyCategorie();
        c.setNaam("test");
        m.setCategorie(c);
		assertEquals(false, mc.verwerkMeldingBurger(m).isBevestiging());
	}
}