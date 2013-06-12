/**
 * 
 */
package nl.ica.breas.burgernet.backend.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import nl.ica.breas.burgernet.backend.adresomvormers.AbstractAdresNaarLocatieAdapter;
import nl.ica.breas.burgernet.backend.adresomvormers.GoogleAdresNaarLocatie;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Adres;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author bramiejo
 *
 */
public class BurgerControllerTest {

    /** De persistence adapter. */
    private AbstractPersistenceAdapter peristenceAdapter = mock(AbstractPersistenceAdapter.class);
    /** De adres adapter. */
    private AbstractAdresNaarLocatieAdapter adresAdapter = new GoogleAdresNaarLocatie();
    /** de BurgerController */
    private BurgerController bc = new BurgerController(peristenceAdapter, adresAdapter);
    /** De LOGGER. */
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");
    
    /**
     * Before.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws PersistenceConnectionException de persistence connection exception
     */
    @Before
    public void before() throws IOException, PersistenceConnectionException {
        String t = "";
        try {
            when(peristenceAdapter.slaBurgerOp(any(Burger.class))).thenReturn(t);
        } catch (IllegalArgumentException | PersistenceDuplicateKeyException e) {
             LOGGER.trace(e);
        }
        java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.FINEST);
        java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.FINEST);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");
    }

    @Test
    public void registreerFail() 
        throws  IllegalArgumentException, PersistenceDuplicateKeyException, 
        IOException, PersistenceConnectionException {
        when(peristenceAdapter.slaBurgerOp(any(Burger.class))).thenThrow(new IOException("Test"));
        Burger burger = new Burger();
        burger.setVoornaam("test");
        burger.setAchternaam("test");
        burger.setAdres(new Adres("t", "t", 4, "t"));
        burger.setEmailAdres("t");
        bc.registreer(burger);
    }   
    @Test
    public void testRegistreer() {
        Burger burger = new Burger();
        burger.setVoornaam("Bram");
        burger.setAchternaam("Arts");
        burger.setAdres(new Adres("Harrie van den berg straat", "Sint Anthonis", 4, "5845HE"));
        burger.setEmailAdres("bramiejo@hotmail.com");
        Status s = bc.registreer(burger);
        assertEquals(true, s.isBevestiging());
        assertEquals(true, burger.getLocatie() != null);
    }
    @Test
    public void testRegistreerFaalVerkeerdAdres() {
        Burger burger = new Burger();
        burger.setVoornaam("Bram");
        burger.setAchternaam("Arts");
        burger.setAdres(new Adres("Harrie van den berg straat", "Sint Anis", 4, "5845HE"));
        burger.setEmailAdres("bramiejo@hotmail.com");
        Status s = bc.registreer(burger);
        assertEquals(false, s.isBevestiging());
        assertEquals(true, burger.getLocatie() == null);
    }
}
