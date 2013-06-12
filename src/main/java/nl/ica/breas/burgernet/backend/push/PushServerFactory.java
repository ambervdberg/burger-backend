/**
 * 
 */
package nl.ica.breas.burgernet.backend.push;

import org.apache.log4j.Logger;

/**
 * @author Samuel van Oostveen
 *
 */
public final class PushServerFactory {

	/**	Deze wordt gebruikt om de errors te loggen.*/
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");
	
	/** De push server string. */
	private static String sPushServer;

	/** De push server factory. */
	private static PushServerFactory instantie = null;

	/**
	 * private constructor.
	 */
	private PushServerFactory() {
		// instantie
	}

	/**
	 * Creeërt een instantie van deze klasse.
	 * @return de instantie
	 */
	public static PushServerFactory getInstance() {
		synchronized (PushServerFactory.class) {
			if (instantie == null) {
				instantie = new PushServerFactory();
			}
		}
		return instantie;
	}
	

	/**
	 * Hier wordt de PushServer gemaakt.
	 * @return de instantie van de IPushServer met de instantie van de PushServer.
	 */
	public IPushServer createPushServerAdapter() {
		IPushServer pushServer;
		try {
			pushServer = (IPushServer) Class.forName(sPushServer).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOGGER.trace(e);
			return null;
		}
		return pushServer;
	}

	/**
	 * @return the sPushServer.
	 */
	public static String getsPushServer() {
		return sPushServer;
	}

	/**
	 * @param sPushServer the sPushServer to set.
	 */
	public static void setsPushServer(final String sPushServer) {
		PushServerFactory.sPushServer = sPushServer;
	}

}
