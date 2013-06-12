package nl.ica.breas.burgernet.backend.controller;
/**
 * Deze interface is voor de controller die de foto's verwerkt.
 */

import nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;

/**
 * @author Amber Schühmacher en Samuel av Oostveen
 * @since 10/12/12
 * @version 0.1
 */
public interface IFotoController {
	/**
	 * Met deze methode worden foto's verwerkt.
	 * 
	 * @param fotoVerwerkContainer De foto die verwerkt moet worden
	 * @return Als de foto succesvol verwerkt is wordt er true teruggegeven. 
	 * Als de melding niet juist is verwerkt wordt er false teruggegeven
	 */
	Status verwerkFoto(FotoVerwerkContainer fotoVerwerkContainer);
	/**
	 * Hiermee kan de database adapter worden ingesteld;
	 * @param adapter de adapter die gebruikt moet worden.
	 */
	void setAdapter(AbstractPersistenceAdapter adapter);
}
