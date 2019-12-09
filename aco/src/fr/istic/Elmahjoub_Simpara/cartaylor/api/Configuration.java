package fr.istic.Elmahjoub_Simpara.cartaylor.api;


import java.util.Optional;
import java.util.Set;

public interface Configuration {

	/**
	 * return true si la configuration est valide
	 * @return
	 */

    boolean isValid();

    /**
     *  return true si la configuration est complet ( contient 4 part)
     * @return
     */
    boolean isComplete();

    /**
     *  return la liste des partTypes selectionner
     * @return
     */
    Set<Part> getSelectedParts();

    /**
   	 * selectionner une piece pour une categorie 
   	 */

    void selectPart(Part chosenPart);

    /**
     * return la partType selectionner qui apparetient au categorie 
     * passe en param 
     * @param category
     * @return
     */
    Optional<Part> getSelectionForCategory(Category category);

    /**
	 *  supprimer la piece qui etait selectioner pour la category 
	 * @throws IsnotExistException 
	 */

    void unselectPartType(Category categoryToClear);

    /**
   	 *  supprimer tous les partTypes selectionner
   	 */

    void clear();
    
    /**
     *  return le prix total de la configuration 
     *  si la configuration est complet
     * @return
     */
    Double getPrice();
    
    /**
     * return  la description html du configuration 
     * si et ssi la config est valid et complet
     * @return
     */
    String  getDescription();


}
