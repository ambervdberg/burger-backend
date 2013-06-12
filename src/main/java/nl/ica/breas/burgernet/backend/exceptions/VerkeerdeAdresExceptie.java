package nl.ica.breas.burgernet.backend.exceptions;

/**
 * @author Bram
 * @since 11/1/13
 */
@SuppressWarnings("serial")
public class VerkeerdeAdresExceptie extends Exception {
    /**
     * De VerkeerdeAdresExceptie.
     * 
     * @param message het meegegeven bericht
     */
    public VerkeerdeAdresExceptie(String message) {
        super(message);
    }
}