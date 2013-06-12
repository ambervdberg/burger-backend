package nl.ica.breas.burgernet.backend.persistence;

import java.io.IOException;
import java.util.List;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;
/**Dit is de interface voor de datatbase adapter.
 * 
 * @author Eric
 * @version 0.01
 * @since 27-11-2012
 */
public abstract class AbstractPersistenceAdapter {
    /** Het ipadres van de MongoDB server. */
    private static String ipadres;
    /** Het poortnummer van de MongoDB server. */
    private static int poortnummer;
    /** De database inlog naam. */
    private static String databaseInlogNaam;
    /** Het database inlog wachtwoord. */
    private static String databaseInlogWachtwoord;
    
    /** Hiermee kun je de ipAdres van de mongoDB server krijgen. 
     * @return the ipadres
     */
    public static final String getIpadres() {
        return ipadres;
    }
    /** Hiermee kun je het poortnummer krijgen van de mongoDB server.
     * @return the poortnummer
     */
    public static final int getPoortnummer() {
        return poortnummer;
    }
    /**
     * Hiermee kan het ipadress van de database adapter worden ingesteld
     * @param ipadress het ipadress van de database
     */
    public static final void setIpadres(final String ipadress) {
        ipadres = ipadress;
    }
    /**
     * Hiermee kan het poortnummer van de database adapter worden ingesteld
     * @param poort het poortnummer van de database
     */
    public static final void setPoort(final int poort) {
        poortnummer = poort;
    }
	/**
	 * Sla een melding op in de database.
	 * @param melding de melding die moet worden opgeslagen.
	 * @return De id van de Melding.
	 * @throws IOException Fout bij het omvormen van of naar JSON.
	 * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
	 * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
	 */
	public abstract String slaMeldingOp(IMelding melding) throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException;
	/**
	 * Haalt melding op van de database.
	 * @param meldingId het ID waarmee de melding gevonden kan worden.
	 * @return de Melding.
	 * @throws IOException Fout bij het omvormen van of naar JSON.
	 * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
	 * @throws IllegalArgumentException Ongeldige database ID
     * @throws PersistenceNotFoundException De Melding is niet gevonden.
	 */
	public abstract IMelding haalMeldingOp(final String meldingId) throws IOException, PersistenceConnectionException, 
	IllegalArgumentException, PersistenceNotFoundException;
	/**
	 * Sla een burger op in de database.
	 * @param burger de Burger die moet worden opgeslagen.
	 * @return de ID van de Burger.
	 * @throws IOException Fout bij het omvormen van of naar JSON.
	 * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
	 * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
	 * @throws IllegalArgumentException De database ID is niet geldig.
	 */
	
	public abstract String slaBurgerOp(final Burger burger) throws IOException, PersistenceConnectionException, 
	PersistenceDuplicateKeyException, IllegalArgumentException;
	/**
	 * Haal de Burger objecten op voor één Melding.
	 * @param melding waarvan de Burger objecten opgehaald moeten worden.
	 * @return een lijst met gevonden burgers
	 * @throws IOException Fout bij het omvormen van of naar JSON.
	 * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
	 * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
	 * @throws PersistenceInternalException Wanneer MongoDB een InternalException geeft.
	 */	
	public abstract List<Burger> selecteerBurgers(IMelding melding) throws IOException, PersistenceConnectionException, 
	PersistenceDuplicateKeyException, PersistenceInternalException;

	/**
	 * Deze methode vervangt de bewuste melding door de nieuwe melding.
	 *
	 * @param meldingId Het ID van de melding die vervangen moet worden.
	 * @param melding Dit is de melding die de oude melding vervangt.
	 * @return een true of false als de melding wijziging geslaagd is.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract boolean wijzigMelding(String meldingId, IMelding melding) throws IOException;
	
    /**
     * 
     * @param loc De locatie die als centrum geld voor de straal berekeningen.
     * @param categorieNaam De categorie die gezocht word.
     * @param straal De straal waarbinnen gezocht word.
     * @return een lijst met meldingen die aan de eisen voldoen.
     * @throws IOException 
     * @throws PersistenceInternalException 
     * @throws RuntimeException word gegooid als de coordinaten boven de 180 of onder de -180 zijn.
     */
	public abstract List<Melding> haalMeldingenOp(Locatie loc, String categorieNaam,
            double straal) throws PersistenceInternalException, IOException, RuntimeException;
	
    /**
     * @param burgerId het id van de bruger die moet worden gevonden
     * @return de burger die gezocht word.
     * @throws IOException 
     * @throws IllegalArgumentException Ongeldige database ID
     * @throws PersistenceNotFoundException De burger is niet gevonden.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     */
	public abstract Burger getBurger(String burgerId) throws IOException, PersistenceConnectionException, 
	IllegalArgumentException, PersistenceNotFoundException;
    
    /**
    * haal de meldingen met een aangepaste straal op voor een locatie.
    * @param loc De locatie die als centrum geld voor de straal berekeningen.
    * @return een lijst met meldingen die aan de eisen voldoen.
    * @throws IOException 
    * @throws PersistenceInternalException 
    * @throws RuntimeException word gegooid als de coordinaten boven de 180 of onder de -180 zijn.
    */
   public abstract List<Melding> haalAangepasteStraalMeldingenOp(Locatie loc) 
           throws PersistenceInternalException, IOException, RuntimeException;
   /**
    * Haalt de database inlognaam op.
    * @return de databaseNaam
    */
   public final String getDatabaseNaam() {
       return databaseInlogNaam;
   }

   /**
    * Zet de inlognaam van de database.
    *
    * @param dbInlogNaam de new database naam
    */
   public static void setDatabaseNaam(String dbInlogNaam) {
       databaseInlogNaam = dbInlogNaam;
   }

   /**
    * Haalt het wachtwoord van de database op.
    * @return het database Inlog Wachtwoord
    */
   public final String getDatabaseInlogWachtwoord() {
       return databaseInlogWachtwoord;
   }

   /**
    * Zet het wachtwoord van de database.
    * @param dbInlogWachtwoord het database Inlog Wachtwoord om te zetten
    */
   public static void setDatabaseInlogWachtwoord(String dbInlogWachtwoord) {
       databaseInlogWachtwoord = dbInlogWachtwoord;
   }
}