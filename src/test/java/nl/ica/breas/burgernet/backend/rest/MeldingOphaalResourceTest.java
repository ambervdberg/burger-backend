package nl.ica.breas.burgernet.backend.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import nl.ica.breas.burgernet.backend.controller.IHaalMeldingOpController;
import nl.ica.breas.burgernet.backend.model.Bijlage;
import nl.ica.breas.burgernet.backend.model.HerhalendeCategorie;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class MeldingOphaalResourceTest {

    @Test
    public void testMeldingOphaalResource() {
        IHaalMeldingOpController imc = mock(IHaalMeldingOpController.class);
        MeldingOphaalResource meldingOphaalResource = new MeldingOphaalResource(imc);
        assertEquals(true, meldingOphaalResource instanceof MeldingOphaalResource);
    }

    @Test
    public void testHaalMeldingOp() {
        IHaalMeldingOpController imc = mock(IHaalMeldingOpController.class);
        when(imc.haalMeldingOp(any(String.class))).thenReturn(new Melding());
        
        MeldingOphaalResource meldingOphaalResource = new MeldingOphaalResource(imc);
        IMelding melding = meldingOphaalResource.haalMeldingOp("123");
        
        assertEquals(true, melding instanceof IMelding);
    }

    @Test
    public void testHaalMeldingenOp() {
        IHaalMeldingOpController imc = mock(IHaalMeldingOpController.class);
        List<IMelding> meldingList = new ArrayList<IMelding>();
        when(imc.haalMeldingenOp(any(String.class))).thenReturn(meldingList);
        
        MeldingOphaalResource meldingOphaalResource = new MeldingOphaalResource(imc);
        meldingList = meldingOphaalResource.haalMeldingenOp("123");
        
        assertEquals(true, meldingList instanceof ArrayList<?>);
    }
    
    @Test
    public void testHaalMeldingenOp2() {
        IHaalMeldingOpController imc = mock(IHaalMeldingOpController.class);
        List<IMelding> meldingList = new ArrayList<IMelding>();
        meldingList.add(new Melding("pietje", new HerhalendeCategorie("vermissing"),
                new Locatie(50.3, 5.123),
                new Bijlage("test", "test"), null));
        meldingList.add(new Melding("pietje is dood", new HerhalendeCategorie("moord"),
                new Locatie(50.3, 5.123),
                new Bijlage("test", "test"), null));
        when(imc.haalMeldingenOp(any(String.class), any(Locatie.class))).thenReturn(meldingList);
        
        MeldingOphaalResource meldingOphaalResource = new MeldingOphaalResource(imc);
        meldingList = meldingOphaalResource.haalMeldingenOp("123", 2.0, 3.0);
        
        assertEquals(true, meldingList instanceof ArrayList<?>);
        assertEquals("pietje", meldingList.get(0).getBeschrijving());
        assertEquals("pietje is dood", meldingList.get(1).getBeschrijving());
    }
    @Test
    public void testHaalMeldingenOpIncompleet() {
        IHaalMeldingOpController imc = mock(IHaalMeldingOpController.class);
        List<IMelding> meldingList = new ArrayList<IMelding>();
        meldingList.add(new Melding("pietje", new HerhalendeCategorie("vermissing"),
                new Locatie(50.3, 5.123),
                new Bijlage("test", "test"), null));
        meldingList.add(new Melding("pietje is dood", new HerhalendeCategorie("moord"),
                new Locatie(50.3, 5.123),
                new Bijlage("test", "test"), null));
        when(imc.haalMeldingenOp(any(String.class), any(Locatie.class))).thenReturn(meldingList);
        
        MeldingOphaalResource meldingOphaalResource = new MeldingOphaalResource(imc);
        meldingList = meldingOphaalResource.haalMeldingenOp("123", null, 3.0);
        assertEquals(true, meldingList == null);
        
    }
}
