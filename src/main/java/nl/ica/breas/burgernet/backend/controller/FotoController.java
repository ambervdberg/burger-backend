/**
 * 
 */
package nl.ica.breas.burgernet.backend.controller;

import java.io.IOException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Bijlage;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;
import org.apache.log4j.Logger;

/**
 * Deze klasse verwerkt de binnengekomen foto's.
 * 
 * @author Amber Schühmacher en Samuel av Oostveen
 * @since 10/12/12
 * @version 0.1
 */
public class FotoController implements IFotoController {

	/**
	 * Hierin wordt de IPersistanceAdapter opgeslagen.
	 */
	private AbstractPersistenceAdapter adapter;

	/**
	 * De logger wordt gebruikt om de errors te loggen.
	 */
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");

	/**
	 * Een constructor.
	 */
	public FotoController() {
		//nodig voor de reflection in de factory.
	}
	/**
	 * @param adapter de database adapter
	 */
	public FotoController(AbstractPersistenceAdapter adapter) {
		this.adapter = adapter;
	}

	/**
	 * Met deze methode worden foto's verwerkt.
	 * 
	 * @param fotoVerwerkContainer De foto die verwerkt moet worden
	 * @return Als de foto succesvol verwerkt is wordt er true teruggegeven.
	 * Als de melding niet juist is verwerkt wordt er false teruggegeven
	 */
	public final Status verwerkFoto(FotoVerwerkContainer fotoVerwerkContainer) {
		Bijlage foto = new Bijlage(fotoVerwerkContainer.getFotoBase64(), fotoVerwerkContainer.getContentType());
		try {
			IMelding melding = adapter.haalMeldingOp(fotoVerwerkContainer.getMeldingID()); 
			melding.setBijlage(foto);
			adapter.wijzigMelding(fotoVerwerkContainer.getMeldingID(), melding);
			return new Status(true, fotoVerwerkContainer.getMeldingID());
		} catch (PersistenceConnectionException | IllegalArgumentException | IOException | PersistenceNotFoundException e) {
			LOGGER.trace(e);
		}
		return new Status(false, null);
	}
	/**
	 * Hiermee kan de database adapter worden ingesteld;
	 * @param adapter de adapter die gebruikt moet worden.
	 */
	public final void setAdapter(AbstractPersistenceAdapter adapter) {
		this.adapter = adapter;
	}
}
