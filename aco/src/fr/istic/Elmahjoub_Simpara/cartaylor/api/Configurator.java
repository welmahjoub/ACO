package fr.istic.Elmahjoub_Simpara.cartaylor.api;

import java.util.Set;

public interface Configurator {

	/**
	 *  return la liste des differentes categories dans la appli
	 * @return Set<Category>
	 */

    Set<Category> getCategories();
    
    /**
     *  return la liste des partTypes qui apparients a la categories
     *  passe en parametre
     * @param category
     * @return Set<PartType> 
     */
    
    Set<PartType> getVariants(Category category);

    /**
     * return reference de la configuration en cours
     * @return Configuration
     */

    Configuration getConfiguration();

    /**
     *  return une reference de la classe CompatibilityChecker
     *  dans l'occurence un objet de la class compatibilityManager
     *  
     * @return CompatibilityChecker
     */

    CompatibilityChecker getCompatibilityChecker();

}
