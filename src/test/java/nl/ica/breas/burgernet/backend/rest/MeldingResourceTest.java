package nl.ica.breas.burgernet.backend.rest;

import java.net.UnknownHostException;
import java.util.Calendar;

import org.junit.Test;

import nl.ica.breas.burgernet.backend.controller.IMeldingController;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Bijlage;
import nl.ica.breas.burgernet.backend.model.AbstractCategorie;
import nl.ica.breas.burgernet.backend.model.DummyCategorie;
import nl.ica.breas.burgernet.backend.model.HerhalendeCategorie;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;
import junit.framework.TestCase;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Rick de Weerd
 * @since 26/11/12
 * @version 0.1
 */

public class MeldingResourceTest extends TestCase {

    /**Er wordt een nieuwe Melding aangemaakt met een bijhorende beschrijving.
     * Er wordt een nieuwe MeldingController aangemaakt.
     * MeldingController wordt meegegeven aan de nieuwe instantie van MeldingResource.
     * Vervolgens wordt de test gerunt, met het Melding object als input
     * en de verwachte boolean als output.
     * @throws UnknownHostException 
     */
    @Test
    public void testMeldMeldingAlsBurger() throws UnknownHostException {
        Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34), new Bijlage(), Calendar.getInstance());

        IMeldingController imc = mock(IMeldingController.class);
        when(imc.verwerkMeldingBurger(any(Melding.class))).thenReturn(new Status(true, "23432dgyu3g4d32q2d"));

        MeldingResource meldingResource = new MeldingResource(imc);
        Status status = meldingResource.meldMelding(melding);

        assertEquals(true, status.isBevestiging());
        assertEquals("23432dgyu3g4d32q2d", status.getId());
    }
    /**
     * test wat er gebeurt als de controller false teruggeeft.
     * @throws UnknownHostException
     */
    @Test
    public void testMeldMeldingFout() throws UnknownHostException {
        Melding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(437.23,234.34), new Bijlage(), Calendar.getInstance());

        IMeldingController imc = mock(IMeldingController.class);
        when(imc.verwerkMeldingBurger(any(Melding.class))).thenReturn(new Status(false, null));

        MeldingResource meldingResource = new MeldingResource(imc);
        Status status = meldingResource.meldMelding(melding);

        assertEquals(false, status.isBevestiging());
        assertEquals(null, status.getId());
    }

    @Test
    public void testMeldMeldingAlsOverheid() {
        AbstractCategorie c = new DummyCategorie();
        c.setNaam("test");
        c.setStraal(34);
        Melding melding = new Melding("test", c, new Locatie(437.23,234.34), new Bijlage(), Calendar.getInstance());

        IMeldingController imc = mock(IMeldingController.class);
        when(imc.verwerkMeldingOverheid(any(Melding.class))).thenReturn(new Status(true, "23432dgyu3g4d32q2d"));

        MeldingResource meldingResource = new MeldingResource(imc);
        Status status = meldingResource.meldMeldingOverheid(melding);

        assertEquals(true, status.isBevestiging());
        assertEquals("23432dgyu3g4d32q2d", status.getId());
    }
}
