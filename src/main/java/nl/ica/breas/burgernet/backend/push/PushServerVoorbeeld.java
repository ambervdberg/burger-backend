/**
 * 
 */
package nl.ica.breas.burgernet.backend.push;

import org.apache.log4j.Logger;

import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.IMelding;

/**
 * @author samuel
 *
 */
public class PushServerVoorbeeld implements IPushServer {
	/**
	 * Dit is de logger
	 */
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");
	/**
	 * Dit wordt gebruikt om een pushmelding naar een burger te sturen.
	 * @param burger de burger naar wie de melding verzonden moet worden.
	 * @param melding de melding die verzonden moet worden.
	 */
	public final void verstuurPushMelding(Burger burger, IMelding melding) {
		LOGGER.info("[DEBUG]:" + burger.getVoornaam() + " " + burger.getAchternaam() + " Received a Push message " + melding.getBeschrijving());
	}
}
