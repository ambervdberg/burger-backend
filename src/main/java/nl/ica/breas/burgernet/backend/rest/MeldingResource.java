package nl.ica.breas.burgernet.backend.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.ica.breas.burgernet.backend.controller.IMeldingController;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Melding;

/** De resource regelt de verbinding tussen de server en de client.
 * @author Rick de Weerd
 * @since 27/11/12
 * @version 0.2
 */

@Path("/melding/new")
@Produces(MediaType.APPLICATION_JSON)
public class MeldingResource {
	/**
	 * Hier wordt de IMeldingController gedeclareerd.
	 */
	private IMeldingController imc;
	/**
	 * De constructor, hierin wordt de IMeldingController geïnitialiseerd.
	 * @param imc De doorgegeven IMeldingController wordt lokaal opgeslagen.
	 */
	public MeldingResource(IMeldingController imc) {
		this.imc = imc;
	}
	
	/**
	 * De ontvangen melding wordt doorgegeven aan de MeldingController.
	 * Deze verwerkt vervolgens de melding.
	 * 
	 * @param melding De melding die gegeven is door de client.
	 * @return Er wordt een boolean gereturned
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public final Status meldMelding(@Valid final Melding melding) {
		return imc.verwerkMeldingBurger(melding);	
	}
	   /**
     * De ontvangen melding wordt doorgegeven aan de MeldingController.
     * Deze verwerkt vervolgens de melding.
     * 
     * @param melding De melding die gegeven is door de client.
     * @return Er wordt een boolean gereturned
     */
    @POST
    @Path("/overheid")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Status meldMeldingOverheid(@Valid final Melding melding) {  
        return imc.verwerkMeldingOverheid(melding);  
    }
}
