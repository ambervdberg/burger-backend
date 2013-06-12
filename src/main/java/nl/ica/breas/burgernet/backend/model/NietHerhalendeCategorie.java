/**
 * 
 */
package nl.ica.breas.burgernet.backend.model;

/**
 * @author samuel
 *
 */
public class NietHerhalendeCategorie extends AbstractCopyableCategorie {
    /**De constructor om een niet herhalende categorie te maken.
     * 
     * @param naam De naam van de nieuwe categorie.
     */
	public NietHerhalendeCategorie(String naam) {
		super(naam);
	}
	/**
	 * De constructor om een niet herhalende categorie te maken.
	 */
	public NietHerhalendeCategorie() {
		// deze constructor word gebruikt voor de jackson object omzetter.
		super();
	}
    /**
     * Copieer de categorie naar een nieuw opject.
     * @return een copie van dit object
     */
    @Override
    public final AbstractCopyableCategorie copy() {
        NietHerhalendeCategorie nieuweCategorie = new NietHerhalendeCategorie();
        nieuweCategorie.setNaam(this.getNaam());
        nieuweCategorie.setStraal(this.getStraal());
        nieuweCategorie.setTimeToLive(this.getTimeToLive());
        return nieuweCategorie;
    }
	

}
