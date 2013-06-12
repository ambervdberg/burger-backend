/**
 * 
 */
package nl.ica.breas.burgernet.backend.rest;

import static org.junit.Assert.*;

import nl.ica.breas.burgernet.backend.model.AbstractCategorie;
import nl.ica.breas.burgernet.backend.model.HerhalendeCategorie;

import org.junit.Before;
import org.junit.Test;

import com.yammer.dropwizard.config.Environment;

import static org.mockito.Mockito.*;

/**
 * @author samuel
 *
 */
public class MeldingServiceTest {
	
    private final Environment environment = mock(Environment.class);
    private final BurgernetService service = new BurgernetService();
    private final BurgernetConfiguration config = new DUMMYConfiguration();
    
    @Before
    public void setup() throws Exception {
    }

    @Test
    public void buildsAThingResource() throws Exception {
        service.initialize(config, environment);
        verify(environment,times(4)).addResource(any());
    }
	/**
	 * Test of de server start.
	 */
	@Test
	public void test() {
		try {
			BurgernetService.main(new String[]{});
		} catch (Exception e) {
			fail("fail, server not started");
		}
	}
	public class DUMMYConfiguration extends BurgernetConfiguration {
		/**
		 * Hierin wordt de array met Categorieën opgeslagen.
		 */
		public DUMMYConfiguration(){
			super();
			AbstractCategorie[] list = {new HerhalendeCategorie(),new HerhalendeCategorie()};
			super.setCategorieLijst(list);
			super.setDatabaseAdapter("nl.ica.breas.burgernet.backend.persistence.MongoDB");
			super.setAdresNaarLocatieAdapter("nl.ica.breas.burgernet.backend.adresomvormers.GoogleAdresNaarLocatie");
			super.setFotoController("nl.ica.breas.burgernet.backend.controller.FotoController");
			super.setHaalMeldingOpController("nl.ica.breas.burgernet.backend.controller.HaalMeldingOpController");
			super.setMeldingController("nl.ica.breas.burgernet.backend.controller.MeldingController");
			super.setPushServer("nl.ica.breas.burgernet.backend.push.PushServerVoorbeeld");
			super.setBurgerController("nl.ica.breas.burgernet.backend.controller.BurgerController");
			super.setDatabaseIpAdres("149.5.47.170");
			super.setDatabasePoort(27017);
			super.setDatabaseInlogNaam("burgernet");
			super.setDatabaseInlogWachtwoord("breas2012");
		}
	}
	
}
