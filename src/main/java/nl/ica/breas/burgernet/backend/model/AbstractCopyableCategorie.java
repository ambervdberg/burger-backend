package nl.ica.breas.burgernet.backend.model;
/**
 * Abstract categorie
 * @author Eric
 *
 */
public abstract class AbstractCopyableCategorie extends AbstractCategorie {
    /**
     * Een contsturctor.
     * @param naam de naam van de te maken caegorie
     */
    public AbstractCopyableCategorie(String naam) {
        super(naam);
    }
    /**
     * Een contsturctor.
     */
    public AbstractCopyableCategorie() {
        // deze constructor word gebruikt voor de jackson object omzetter.
        super();
    }
    
    /**
     * copieer de categorie naar een nieuw opject.
     * @return een copie van dit object
     */
    public abstract AbstractCopyableCategorie copy();
    
    /**
     * Met deze methode worden de aangepaste paramters uit de dummy categorie geladen.
     * @param dummy De dummy categorie waar de aangepaste paramters in kunnen zitten.
     */
    public final void laadAangepasteParamters(DummyCategorie dummy) {
        if (dummy.getStraal() != 0) {
           this.setStraal(dummy.getStraal());
        }
        if (dummy.getTimeToLive() != 0) {
           this.setTimeToLive(dummy.getTimeToLive());
        }
    }

}
