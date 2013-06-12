/**
 * 
 */
package nl.ica.breas.burgernet.backend.push;

import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.IMelding;

/**
 * @author samuel
 *
 */
public interface IPushServer {
	/**
	 * Dit wordt gebruikt om een pushmelding naar een burger te sturen.
	 * @param burger de burger naar wie de melding verzonden moet worden.
	 * @param melding de melding die verzonden moet worden.
	 */
	void verstuurPushMelding(Burger burger, IMelding melding);
}
