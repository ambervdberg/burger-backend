/**
 * 
 */
package nl.ica.breas.burgernet.backend.persistence;

import org.apache.log4j.Logger;

/**
 * @author Samuel van Oostveen
 *
 */
public final class PersistenceFactory {

	/**	Deze wordt gebruikt om de errors te loggen.*/
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");

	/** De database adapter. */
	private static String dbAdapter;

	/** De database instantie. */
	private static PersistenceFactory instantie = null; 

	/**
	 * Haalt een instantie op van de database Factory.
	 *
	 * @return single instance of PersistenceFactory
	 */
	public static PersistenceFactory getInstance() {
		synchronized (PersistenceFactory.class) {
			if (instantie == null) {
				instantie = new PersistenceFactory();
			}
		}
		return instantie;	
	}

	/**
	 * private constructor van de database instantie.
	 */
	private PersistenceFactory() {
	}

	/**
	 * Hier wordt de DatabaseAdapter gemaakt.
	 * @return de instantie van de AbstractPersistenceAdapter met de instantie van de DatabaseAdapter.
	 */
	public AbstractPersistenceAdapter createDatabaseAdapter() {
		AbstractPersistenceAdapter dba;
		try {
			dba = (AbstractPersistenceAdapter) Class.forName(dbAdapter).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOGGER.trace(e);
			return null;
		}
		return dba;
	}

	/**
	 * @return the dbAdapter
	 */
	public static String getDbAdapter() {
		return PersistenceFactory.dbAdapter;
	}

	/**
	 * @param dbAdapter the dbAdapter to set
	 */
	public static void setDbAdapter(final String dbAdapter) {
		PersistenceFactory.dbAdapter = dbAdapter;
	}

	/**
	 * Hiermee kun je de mongoDB ip en poort nummer setten.
	 * @param ip het ipadress van de database.
	 * @param poort het poortnummer van de database
	 */
	public static void setDbInfo(String ip, int poort) {
        AbstractPersistenceAdapter.setIpadres(ip);
        AbstractPersistenceAdapter.setPoort(poort);
	}
	/**
	 * Hiermee kun je de inlog van de Database setten.
	 * @param dbInlogNaam de inlognaam
	 * @param dbWachtwoord het wachtwoord
	 */
	public static void setDbInlog(String dbInlogNaam, String dbWachtwoord) {
	    AbstractPersistenceAdapter.setDatabaseNaam(dbInlogNaam);
	    AbstractPersistenceAdapter.setDatabaseInlogWachtwoord(dbWachtwoord);
	}
}
