/**
 * 
 */
package nl.ica.breas.burgernet.backend.adresomvormers;

import org.apache.log4j.Logger;

/**
 * @author Samuel van Oostveen
 *
 */
public final class AdresOmvormerFactory {

	/**	Deze wordt gebruikt om de errors te loggen.*/
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");

	/** De database adapter. */
	private static String adresNaarLocatieAdapter;

	/** De database instantie. */
	private static AdresOmvormerFactory instantie = null; 

	/**
	 * Haalt een instantie op van de database Factory.
	 *
	 * @return single instance of PersistenceFactory
	 */
	public static AdresOmvormerFactory getInstance() {
		synchronized (AdresOmvormerFactory.class) {
			if (instantie == null) {
				instantie = new AdresOmvormerFactory();
			}
		}
		return instantie;	
	}

	/**
	 * private constructor van de database instantie.
	 */
	private AdresOmvormerFactory() {
	}

	/**
	 * Hier wordt de AdresNaarLocatieAdapter gemaakt.
	 * @return de instantie van de AbstractAdresNaarLocatie.
	 */
	public AbstractAdresNaarLocatieAdapter createAdresNaarLocatieAdapter() {
	    AbstractAdresNaarLocatieAdapter abstractAdresNaarLocatie;
		try {
		    abstractAdresNaarLocatie = (AbstractAdresNaarLocatieAdapter) Class.forName(adresNaarLocatieAdapter).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOGGER.trace(e);
			return null;
		}
		return abstractAdresNaarLocatie;
	}

    /**
     * @return the adresNaarLocatieAdapter
     */
    public static String getAdresNaarLocatieAdapter() {
        return adresNaarLocatieAdapter;
    }

    /**
     * @param adresNaarLocatieAdapter the adresNaarLocatieAdapter to set
     */
    public static void setAdresNaarLocatieAdapter(
            String adresNaarLocatieAdapter) {
        AdresOmvormerFactory.adresNaarLocatieAdapter = adresNaarLocatieAdapter;
    }
}
