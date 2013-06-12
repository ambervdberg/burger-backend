/**
 * 
 */
package nl.ica.breas.burgernet.backend.adresomvormers;

import static org.junit.Assert.*;

import java.io.IOException;

import nl.ica.breas.burgernet.backend.exceptions.VerkeerdeAdresExceptie;
import nl.ica.breas.burgernet.backend.model.Adres;
import nl.ica.breas.burgernet.backend.model.Locatie;

import org.junit.Test;

/**
 * @author bramiejo
 *
 */
public class GoogleAdresNaarLocatieTest {

    @Test
    public void testAdresToLocatie() throws IOException, VerkeerdeAdresExceptie { 
        GoogleAdresNaarLocatie googleAdresNaarLocatie = new GoogleAdresNaarLocatie();
        Adres adres = new Adres("harrie van den berg straat", "Sint anthonis", 4, "5845HE");

        Locatie locatie = googleAdresNaarLocatie.adresToLocatie(adres);

        assertEquals(51.62396110, locatie.getLatitude(), 0.01);
        assertEquals(5.87987340, locatie.getLongitude(), 0.01);

    }
    
    @Test
    public void testWrongAdresToLocatie() { 
        GoogleAdresNaarLocatie googleAdresNaarLocatie = new GoogleAdresNaarLocatie();
        Adres adres = new Adres("harrie van den bergat", "Sintis", 4, "5845HE");
        try {
            Locatie locatie = googleAdresNaarLocatie.adresToLocatie(adres);
            System.out.println(locatie.getLatitude() + " " + locatie.getLongitude());
        } catch (VerkeerdeAdresExceptie | IOException e) {
            assertEquals("Dit is geen bestaand adres", e.getMessage());
            return;
        }
        fail("Er is geen exceptie gegooit");
    }
    
    @Test
    public void testGetSetURL() {
        GoogleAdresNaarLocatie googleAdresNaarLocatie = new GoogleAdresNaarLocatie();
        googleAdresNaarLocatie.setURL("piet");
        assertEquals("piet", googleAdresNaarLocatie.getURL());
    }
}
