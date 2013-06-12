package nl.ica.breas.burgernet.backend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.CategorieenMap;
import nl.ica.breas.burgernet.backend.model.HerhalendeCategorie;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;
import org.junit.Test;
import com.mongodb.MongoException;

public class HaalMeldingOpControllerTest {

    static HaalMeldingOpController hmc;
    static AbstractPersistenceAdapter adapter;
    HerhalendeCategorie[] categorieenLijst = {new HerhalendeCategorie("categorieenLijstTest"), new HerhalendeCategorie("test"), new HerhalendeCategorie("piet")};
    CategorieenMap categorieenMap;
    static IMelding im;
    
//    @Test
//    public void testHaalMeldingenOpLocatie() throws MongoException, IOException, PersistenceInternalException, PersistenceConnectionException, IllegalArgumentException, PersistenceNotFoundException {
//        categorieenMap = new CategorieenMap(categorieenLijst);
//        adapter = mock(AbstractPersistenceAdapter.class);
//        hmc = new HaalMeldingOpController(categorieenMap, adapter);
//        when(adapter.haalMeldingenop(any(Locatie.class), any(String.class), any(Double.class))).thenReturn(new ArrayList<Melding>());
//
//        assertEquals(true, hmc.haalMeldingenOp("burgerId", new Locatie()) instanceof ArrayList);
//        assertNotNull(hmc.haalMeldingenOp("burgerId"));
//    }
    
    /**
     * Hier wordt de constructor van HaalMeldingOpController getest.
     */
    @Test
    public void testHaalMeldingOpController() {
        hmc = new HaalMeldingOpController(mock(CategorieenMap.class), mock(AbstractPersistenceAdapter.class));
        HaalMeldingOpController hmc2 = new HaalMeldingOpController();
        adapter = mock(AbstractPersistenceAdapter.class);
        assertEquals(true, hmc instanceof HaalMeldingOpController);
        assertEquals(true, hmc2 instanceof HaalMeldingOpController);
    }

    /**
     * Hier wordt bij de methode HaalMeldingOp getest of er een Melding wordt teruggegeven bij het invoeren van een random string.
     * @throws PersistenceConnectionException 
     * @throws IOException 
     * @throws IllegalArgumentException 
     * @throws PersistenceNotFoundException 
     */
    @Test
    public void testHaalMeldingOp() throws IllegalArgumentException, IOException, PersistenceConnectionException, PersistenceNotFoundException {
        adapter = mock(AbstractPersistenceAdapter.class);
        when(adapter.haalMeldingOp(anyString())).thenReturn(new Melding());
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        assertEquals(true, hmc.haalMeldingOp("ss") instanceof Melding);
    }

    /**
     * Test een IOException.
     * @throws PersistenceConnectionException 
     * @throws IOException 
     * @throws IllegalArgumentException 
     * @throws PersistenceNotFoundException 
     */
    @Test
    public void testIOException() throws IllegalArgumentException, IOException, PersistenceConnectionException, PersistenceNotFoundException {
        adapter = mock(AbstractPersistenceAdapter.class);
        when(adapter.haalMeldingOp(anyString())).thenReturn(new Melding());
        when(adapter.haalMeldingOp(anyString())).thenThrow(new IOException("Test"));
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        assertEquals(false, hmc.haalMeldingOp("ss") instanceof Melding);
    }

    /**
     * Test een PersistenceInternalException.
     * @throws PersistenceConnectionException 
     * @throws IOException 
     * @throws IllegalArgumentException 
     * @throws PersistenceNotFoundException 
     */
    @Test
    public void testPersistenceConnectionException() throws IllegalArgumentException, IOException, PersistenceConnectionException, PersistenceNotFoundException {
        adapter = mock(AbstractPersistenceAdapter.class);
        when(adapter.haalMeldingOp(anyString())).thenReturn(new Melding());
        when(adapter.haalMeldingOp(anyString())).thenThrow(new PersistenceConnectionException("Test1"));			
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        assertEquals(false, hmc.haalMeldingOp("ss") instanceof Melding);
    }

    /**
     * Test een IllegalArgumentException.
     * @throws PersistenceConnectionException 
     * @throws IOException 
     * @throws IllegalArgumentException 
     * @throws PersistenceNotFoundException 
     */
    @Test
    public void testIllegalArgumentException() throws IllegalArgumentException, IOException, PersistenceConnectionException, PersistenceNotFoundException {
        adapter = mock(AbstractPersistenceAdapter.class);
        when(adapter.haalMeldingOp(anyString())).thenReturn(new Melding());
        when(adapter.haalMeldingOp(anyString())).thenThrow(new IllegalArgumentException("Test1"));			
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        assertEquals(false, hmc.haalMeldingOp("ss") instanceof Melding);
    }

    /**
     * Test het ophalen van alle meldingen voor één burger.
     * Er wordt een ArrayList teruggegeven.
     * 
     * @throws IOException 
     * @throws MongoException 
     * @throws PersistenceInternalException 
     * @throws PersistenceConnectionException 
     * @throws PersistenceNotFoundException 
     * @throws IllegalArgumentException 
     */
    @Test
    public void testHaalMeldingenOp() throws MongoException, IOException, PersistenceInternalException, PersistenceConnectionException, IllegalArgumentException, PersistenceNotFoundException {
        categorieenMap = new CategorieenMap(categorieenLijst);
        adapter = mock(AbstractPersistenceAdapter.class);
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        when(adapter.getBurger(any(String.class))).thenReturn(new Burger());
        when(adapter.haalMeldingenOp(any(Locatie.class), any(String.class), any(Double.class))).thenReturn(new ArrayList<Melding>());

        assertEquals(true, hmc.haalMeldingenOp("burgerId") instanceof ArrayList);
        verify(adapter).getBurger(any(String.class));
        assertNotNull(hmc.haalMeldingenOp("burgerId"));
    }
    
    /**
     * Test de exception.
     * 
     * @throws IOException 
     * @throws MongoException 
     * @throws PersistenceInternalException 
     * @throws PersistenceConnectionException 
     * @throws PersistenceNotFoundException 
     * @throws IllegalArgumentException 
     */
    @Test
    public void testHaalMeldingenOpException() throws MongoException, IOException, PersistenceInternalException, PersistenceConnectionException, IllegalArgumentException, PersistenceNotFoundException {
        categorieenMap = new CategorieenMap(categorieenLijst);
        adapter = mock(AbstractPersistenceAdapter.class);
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        when(adapter.getBurger(any(String.class))).thenThrow(new IllegalArgumentException());
        assertEquals(true, hmc.haalMeldingenOp("burgerId") == null);
    }

    /**
     * Test de getter en setter van de CategorieenMap.
     */
    @Test
    public final void testGetEnSetCategorieenMap() {
        categorieenMap = new CategorieenMap(categorieenLijst);
        adapter = mock(AbstractPersistenceAdapter.class);
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        hmc.setCategorieenMap(categorieenMap);
        assertEquals(categorieenMap,  hmc.getCategorieenMap());
    }

    /**
     * Test de adapter setter
     */
    @Test
    public final void testSetAdapter() {
        categorieenMap = new CategorieenMap(categorieenLijst);
        adapter = mock(AbstractPersistenceAdapter.class);
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        hmc.setAdapter(adapter);
        assertEquals(adapter,  hmc.getAdapter());  
    }
    

    
    /**
     * Test de excepties voor meldingen voor een bepaalde locatie
     * 
     * @throws MongoException
     * @throws IOException
     * @throws PersistenceInternalException
     * @throws PersistenceConnectionException
     * @throws IllegalArgumentException
     * @throws PersistenceNotFoundException
     */
    @Test
    public void TestMeldingenVoorLocatieException() throws MongoException, IOException, PersistenceInternalException, PersistenceConnectionException, IllegalArgumentException, PersistenceNotFoundException {
        categorieenMap = new CategorieenMap(categorieenLijst);
        adapter = mock(AbstractPersistenceAdapter.class);
        hmc = new HaalMeldingOpController(categorieenMap, adapter);
        when(adapter.haalMeldingenOp(any(Locatie.class), any(String.class), any(Double.class))).thenThrow(new PersistenceInternalException());
        assertEquals(true,  hmc.haalMeldingenOp("burgerId", new Locatie()) instanceof List);
    }

}
