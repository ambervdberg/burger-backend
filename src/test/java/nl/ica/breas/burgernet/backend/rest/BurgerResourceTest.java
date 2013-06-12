package nl.ica.breas.burgernet.backend.rest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;

import nl.ica.breas.burgernet.backend.controller.IBurgerController;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Adres;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.Locatie;

import org.junit.Test;
    /**
     * @author Rick
     * @since  9/1/13
     */
public class BurgerResourceTest {
    /**
     * Hier wordt de IBurgerController gedeclareerd.
     */
    private IBurgerController ibc = mock(IBurgerController.class);
    /**
     * Hier wordt de BurgerResource gedeclareerd.
     */
    private BurgerResource br = new BurgerResource(ibc);
     /**
     * Hier wordt de BurgerResource getest.
     * Dit door te kijken of de eerder gedeclareerde BurgerResource
     * ook daadwerkelijk een instantie is van BurgerResource.
     */
    @Test
    public void testBurgerResource() {
        assertEquals(true, br instanceof BurgerResource);
    }
    /**
     * Er wordt een nieuwe Burger aangemaakt met de bijbehorende gegevens.
     * Er wordt een nieuwe BurgerController aangemaakt.
     * Er wordt een instantie van BurgerResource aangemaak, 
     * deze krijgt de BurgerController mee.
     * Vervolgens testen wij dit, met een verwachte boolean output
     * en met de Burger als input.
     * @throws UnknownHostException 
     */
    @Test
    public void testRegistreer() throws UnknownHostException {
        Burger burger = new Burger("Piet", "Puk", 
                null,
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"), "pietpuk@gmail.com");
        
        when(ibc.registreer(any(Burger.class))).thenReturn(new Status(true, "23as224mf22fsre"));
        Status status = br.registreer(burger);
        System.out.println(br.registreer(burger));
        
        assertEquals(true, status.isBevestiging());
        assertEquals("23as224mf22fsre", status.getId());
    }
    /**
     * Test wat er gebeurt als de controller false terug geeft.
     * @throws UnknownHostException
     */
    @Test
    public void testRegistreerFout() throws UnknownHostException {
        Burger burger = new Burger("Piet", "Puk", 
                new Locatie(2.0, 3.0),
                new Adres("harrie van den berg straat", "Sint Anthonis", 4, "5845HE"), "pietpuk@gmail.com");
        
        when(ibc.registreer(any(Burger.class))).thenReturn(new Status(false, null));
        Status status = br.registreer(burger);
        
        assertEquals(false, status.isBevestiging());
        assertEquals(null, status.getId());
    }
}