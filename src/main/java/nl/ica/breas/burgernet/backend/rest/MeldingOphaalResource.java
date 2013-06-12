package nl.ica.breas.burgernet.backend.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import nl.ica.breas.burgernet.backend.controller.IHaalMeldingOpController;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;

/** De resource regelt de verbinding tussen de server en de client.
 * @author Bram Arts
 * @since 03-12-2012
 * @version 0.2
 */

@Path("/melding")
@Produces(MediaType.APPLICATION_JSON)
public class MeldingOphaalResource {
	/**
	 * Hier wordt de IHaalMeldingOpController gedeclareerd.
	 */
	private IHaalMeldingOpController imc;
	/**
	 * De constructor, hierin wordt de IHaalMeldingOpController geïnitialiseerd.
	 * @param imc De doorgegeven IMeldingController wordt lokaal opgeslagen.
	 */
	public MeldingOphaalResource(IHaalMeldingOpController imc) {
		this.imc = imc;
	}
	
	/**
	 * De meldingID wordt doorgegeven aan de HaalMeldingOpController.
	 * Deze verwerkt vervolgens de melding.
	 * 
	 * @param meldingID de melding id horende bij de melding die moet worden opgehaald.
	 * @return Er wordt een melding gereturned
	 */
	@GET
	@Path("/haalop")
	public final IMelding haalMeldingOp(@QueryParam("meldingID") String meldingID) {
		return imc.haalMeldingOp(meldingID);
	}
	/**
	 * De burgerID wordt doorgegeven aan de HaalMeldingOpController.
	 * Deze verwerkt hem en geeft een lijst van meldingen terug.
	 * @param burgerID de burgerID horende bij de burger waarvoor berichten worden opgehaald.
	 * @return een lijst met Meldingen.
	 */
	@GET
	@Path("/haalmeldingenop")
	public final List<IMelding> haalMeldingenOp(@QueryParam("burgerID") String burgerID) {
        return imc.haalMeldingenOp(burgerID);
	    
	}
    /**
     * De burgerID wordt doorgegeven aan de HaalMeldingOpController.
     * Deze verwerkt hem en geeft een lijst van meldingen terug.
     * @param burgerID de burgerID horende bij de burger waarvoor berichten worden opgehaald.
     * @param lon dit is de longitude.
     * @param lat dit is de latitude
     * @return een lijst met Meldingen.
     */
	@GET
	@Path("/haalmeldingenopmetlocatie")
	public final List<IMelding> haalMeldingenOp(@QueryParam("burgerID") String burgerID, 
	        @QueryParam("lon") Double lon, @QueryParam("lat") Double lat) {
	    if (burgerID != null && lon != null && lat != null) {
	        return imc.haalMeldingenOp(burgerID, new Locatie(lon, lat));
	    }
	    return null;

	}
	
}
