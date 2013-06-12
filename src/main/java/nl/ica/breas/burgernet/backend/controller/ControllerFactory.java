/**
 * 
 */
package nl.ica.breas.burgernet.backend.controller;

import org.apache.log4j.Logger;

/**
 * Een singleton factory voor het creëren van de Contollers.
 *
 * @author Amber
 * @version 0.1
 * @since 14/12/12
 */
public final class ControllerFactory {

	/** De LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");

	/** De instantie. */
	private static ControllerFactory instantie = null;

	/** De meldingController string. */
	private static String sMeldingController;

	/** De fotoController string. */
	private static String sFotoController;

	/** De meldingOphaalController string. */
	private static String sHaalMeldingOpController;
	
	/** De BurgerController String. */
	private static String sBurgerController;

    /**
	 * private constructor controller factory.
	 */
	private ControllerFactory() {

	}

	/**
	 * Maakt een factory instantie aan.
	 *
	 * @return de factory instantie.
	 */
	public static ControllerFactory getInstantie() {
		synchronized (ControllerFactory.class) {
			if (instantie == null) {
				instantie = new ControllerFactory();
			}
		}
		return instantie;
	}

	/**
	 * createController wordt gebruikt om een Controller aan te maken van het meegegeven type.
	 *
	 * @param sController de meegegeven controller als string.
	 * @return het object
	 */
	private Object createController(final String sController) {
		try {
			return Class.forName(sController).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOGGER.trace(e);
			return null;
		}
	}
	
	/**
	 * Maakt een nieuwe melding controller aan.
	 *
	 * @return de melding controller.
	 */
	public IMeldingController createMeldingController() {
		return  (IMeldingController) createController(sMeldingController);
	}

	/**
	 * Maakt een nieuwe foto controller aan.
	 *
	 * @return de foto controller.
	 */
	public IFotoController createFotoController() {
		return (IFotoController) createController(sFotoController);
	}

	/**
	 * Maakt een nieuwe haal melding op controller aan.
	 *
	 * @return de haal melding op controller.
	 */
	public IHaalMeldingOpController createOphaalController() {
		return (IHaalMeldingOpController) createController(sHaalMeldingOpController);
	}
	
	/** Maakt een nieuwe BurgerController aan.
	 * @return de BurgerController.
	 */
	public IBurgerController createBurgercontroller() {
	    return (IBurgerController) createController(sBurgerController);
	}

	/**
	 * @param sMeldingController the sMeldingController to set
	 */
	public void setsMeldingController(final String sMeldingController) {
		ControllerFactory.sMeldingController = sMeldingController;
	}

	/**
	 * @param sFotoController the sFotoController to set
	 */
	public void setsFotoController(final String sFotoController) {
		ControllerFactory.sFotoController = sFotoController;
	}

	/**
	 * @param sHaalMeldingOpController the sHaalMeldingOpController to set
	 */
	public void setsHaalMeldingOpController(final String sHaalMeldingOpController) {
		ControllerFactory.sHaalMeldingOpController = sHaalMeldingOpController;
	}

    /**
     * @param sBurgerController the sBurgerController to set
     */
    public void setsBurgerController(String sBurgerController) {
        ControllerFactory.sBurgerController = sBurgerController;
    }
}
