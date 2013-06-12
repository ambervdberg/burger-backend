/**
 * 
 */
package nl.ica.breas.burgernet.backend.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Samuel van Oostveen
 *
 */
public class CategorieenMap {
    /** Hier worden de categorieen opgeslagen. */
    private Map<String, AbstractCategorie> categorieen;

    /**
     * Stel een nieuwe CategorieenMap aan met een categorieen array.
     * @param categorieenLijst de array met Categorie objecten.
     */
    public CategorieenMap(AbstractCategorie[] categorieenLijst) {
        categorieen = new HashMap<String, AbstractCategorie>();
        for (AbstractCategorie categorie: categorieenLijst) {
            categorieen.put(categorie.getNaam(), categorie);
        }
    }
    
    /**
     * @return the categorieenMap
     */
    public final Map<String, AbstractCategorie> getCategorieen() {
        return categorieen;
    }

    /**
     * @param categorieenMap the categorieenMap to set
     */
    public final void setCategorieen(Map<String, AbstractCategorie> categorieenMap) {
        this.categorieen = categorieenMap;
    }
    
    /**
     * Stel een nieuwe Map in met een categorieen array.
     * @param categorieenLijst de array met Categorie objecten.
     */
    public final void newCategorieenLijst(AbstractCategorie[] categorieenLijst) {
        categorieen = new HashMap<String, AbstractCategorie>();
        for (AbstractCategorie categorie: categorieenLijst) {
            categorieen.put(categorie.getNaam(), categorie);
        }
    }
}
