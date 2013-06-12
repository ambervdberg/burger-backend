/**
 * 
 */
package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author Samuel van Oostveen
 *
 */
public class CategorieenMapTest {

    AbstractCategorie[] categorieenLijst = {new NietHerhalendeCategorie("categorieenLijstTest"), new NietHerhalendeCategorie("test"), new NietHerhalendeCategorie("piet")};
    
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.model.CategorieenMap#CategorieenMap(nl.ica.breas.burgernet.backend.model.AbstractCategorie[])}.
     */
    @Test
    public void testCategorieenMap() {
        CategorieenMap categorieenMap = new CategorieenMap(categorieenLijst);
        assertEquals(true, categorieenMap instanceof CategorieenMap);
        assertEquals("categorieenLijstTest", categorieenMap.getCategorieen().get("categorieenLijstTest").getNaam());
    }

    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.model.CategorieenMap#getCategorieen()}.
     */
    @Test
    public void testSetGetCategorieen() {
        HashMap<String, AbstractCategorie> categorieenHashMap = new HashMap<String, AbstractCategorie>();
        categorieenHashMap.put("testCategorieNaam", new NietHerhalendeCategorie("testCategorieNaam"));
        CategorieenMap categorieenMap = new CategorieenMap(categorieenLijst);
        categorieenMap.setCategorieen(categorieenHashMap);
        assertEquals("testCategorieNaam", categorieenMap.getCategorieen().get("testCategorieNaam").getNaam());
    }

    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.model.CategorieenMap#newCategorieenLijst(nl.ica.breas.burgernet.backend.model.AbstractCategorie[])}.
     */
    @Test
    public void testNewCategorieenLijst() {
        CategorieenMap categorieenMap = new CategorieenMap(categorieenLijst);
        categorieenLijst[2] = new NietHerhalendeCategorie("testje...");
        categorieenMap.newCategorieenLijst(categorieenLijst);
        assertEquals("testje...", categorieenMap.getCategorieen().get("testje...").getNaam());
    }

}
