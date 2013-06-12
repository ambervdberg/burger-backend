package nl.ica.breas.burgernet.backend.rest;

import java.net.UnknownHostException;

import nl.ica.breas.burgernet.backend.adresomvormers.AbstractAdresNaarLocatieAdapter;
import nl.ica.breas.burgernet.backend.adresomvormers.AdresOmvormerFactory;
import nl.ica.breas.burgernet.backend.controller.ControllerFactory;
import nl.ica.breas.burgernet.backend.controller.IBurgerController;
import nl.ica.breas.burgernet.backend.controller.IFotoController;
import nl.ica.breas.burgernet.backend.controller.IHaalMeldingOpController;
import nl.ica.breas.burgernet.backend.controller.IMeldingController;
import nl.ica.breas.burgernet.backend.model.CategorieenMap;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;
import nl.ica.breas.burgernet.backend.persistence.PersistenceFactory;
import nl.ica.breas.burgernet.backend.push.PushServerFactory;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

/**
 * @author Rick de Weerd
 * @since 26/11/12
 * @version 0.1
 */

public final class BurgernetService extends Service<BurgernetConfiguration> {
	
	/** De persistence factory. */
	private PersistenceFactory persistenceFactory;
	
	/** De controller factory. */
	private ControllerFactory controllerFactory;
	
	/** De push server factory. */
	private PushServerFactory  pushServerFactory;
	
	/** De adres omvormer factory. */
	private AdresOmvormerFactory adresOmvormerFactory;
	/**
	 * De main methode die zorgt dat deze klasse begint met het runnen van een nieuwe instantie van MeldingService.
	 * @param args Argumenten voor de server
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		new BurgernetService().run(args);
	}

	/**
	 * De constructor.
	 */
	public BurgernetService() {
		super("categorieën");
	}

	/**
	 * Initialiseert en voegt de resource toe aan de environment.
	 * 
	 * @param configuration dit is het configuratie bestand
	 * 		  waaruit de instellingen kunnen worden geladen
	 * @param environment dit is de omgeving waarin de resources 
	 * 		  en de healthchecks worden geladen.
	 * @throws UnknownHostException 
	 */
	@Override
	protected void initialize(BurgernetConfiguration configuration, Environment environment) 
			throws UnknownHostException {
		setControllerFactoryWaarde(configuration);
		PersistenceFactory.setDbAdapter(configuration.getDatabaseAdapter());
		PersistenceFactory.setDbInfo(configuration.getDatabaseIpAdres(), configuration.getDatabasePoort());
		PersistenceFactory.setDbInlog(configuration.getDatabaseInlogNaam(), configuration.getDatabaseInlogWachtwoord());
		persistenceFactory = PersistenceFactory.getInstance();
		PushServerFactory.setsPushServer(configuration.getPushServer());
		pushServerFactory = PushServerFactory.getInstance();
		CategorieenMap categorieenMap = new CategorieenMap(configuration.getCategorieLijst());
		AdresOmvormerFactory.setAdresNaarLocatieAdapter(configuration.getAdresNaarLocatieAdapter());
		adresOmvormerFactory = AdresOmvormerFactory.getInstance();
		
		environment.addResource(new MeldingResource(maakMeldingController(categorieenMap)));
		environment.addResource(new MeldingOphaalResource(maakHaalMeldingOpController(categorieenMap)));
		environment.addResource(new FotoResource(maakFotoController()));
		environment.addResource(new BurgerResource(maakBurgerController()));
	}

	/**
	 * Zet de waarde van de controller factory.
	 * @param configuration de controller factory waardes.
	 */
	private void setControllerFactoryWaarde(BurgernetConfiguration configuration) {
		controllerFactory = ControllerFactory.getInstantie();
		controllerFactory.setsMeldingController(configuration.getMeldingController());
		controllerFactory.setsFotoController(configuration.getFotoController());
		controllerFactory.setsHaalMeldingOpController(configuration.getHaalMeldingOpController());
		controllerFactory.setsBurgerController(configuration.getBurgerController());
	}
	
	/**
	 * Maakt een IMeldingController aan met behulp van de ControllerFactory.
	 * @param categorieenMap de categorieenMap.
	 * @return de controller
	 */
	private IMeldingController maakMeldingController(CategorieenMap categorieenMap) {
		IMeldingController meldingController = controllerFactory.createMeldingController();
		AbstractPersistenceAdapter persistence = persistenceFactory.createDatabaseAdapter();
		meldingController.setAdapter(persistence);
		meldingController.setCategorieenMap(categorieenMap);
		meldingController.setPushServer(pushServerFactory.createPushServerAdapter());
		return meldingController;
	}
	
	/**
	 * Maakt een IHaalMeldingOpController aan met behulp van de ControllerFactory.
	 * @param categorieenMap de categorieenMap
	 * @return de controller
	 */
	private IHaalMeldingOpController maakHaalMeldingOpController(CategorieenMap categorieenMap) {
		IHaalMeldingOpController haalMeldingOpController = controllerFactory.createOphaalController();
		haalMeldingOpController.setCategorieenMap(categorieenMap);
	    AbstractPersistenceAdapter persistence = persistenceFactory.createDatabaseAdapter();
		haalMeldingOpController.setAdapter(persistence);
		return haalMeldingOpController;
	}
	
	/**
	 * Maakt een IFotoController aan met behulp van de ControllerFactory.
	 * @return de controller
	 */
	private IFotoController maakFotoController() {
		IFotoController fotoController = controllerFactory.createFotoController();
		AbstractPersistenceAdapter persistence = persistenceFactory.createDatabaseAdapter();
		fotoController.setAdapter(persistence);
		return fotoController;
	}
	/**
	 * Maakt de IBurgerController aan met behulp van de ControllerFactory.
	 * @return de controller.
	 */
	private IBurgerController maakBurgerController() {
	    IBurgerController burgerController = controllerFactory.createBurgercontroller();
        AbstractPersistenceAdapter persistence = persistenceFactory.createDatabaseAdapter();
        AbstractAdresNaarLocatieAdapter adresNaarLocatie = adresOmvormerFactory.createAdresNaarLocatieAdapter();
        burgerController.setAdapter(persistence, adresNaarLocatie);
        return burgerController;
	}
}
