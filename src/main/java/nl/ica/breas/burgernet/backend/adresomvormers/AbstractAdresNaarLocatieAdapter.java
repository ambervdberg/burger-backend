/**
 * 
 */
package nl.ica.breas.burgernet.backend.adresomvormers;

import java.io.IOException;

import nl.ica.breas.burgernet.backend.exceptions.VerkeerdeAdresExceptie;
import nl.ica.breas.burgernet.backend.model.Adres;
import nl.ica.breas.burgernet.backend.model.Locatie;

/**
 * @author Samuel van Oostveen
 *
 */
public abstract class AbstractAdresNaarLocatieAdapter {
    
    /**
     * Dit wordt gebruikt om een adres naar een locatie om te vormen.
     * @param adres het adres wat omgevormt moet worden. 
     * @return een locatie wat bij het adres hoort.
     * @throws VerkeerdeAdresExceptie 
     * @throws IOException 
     */
    public abstract Locatie adresToLocatie(Adres adres) throws IOException, VerkeerdeAdresExceptie;
}
