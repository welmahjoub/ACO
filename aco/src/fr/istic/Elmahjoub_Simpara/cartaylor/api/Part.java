package fr.istic.Elmahjoub_Simpara.cartaylor.api;

public interface Part extends PropertyManager{
	
	/**
	 * 
	 * @return String 
	 */
	/*default String getName()
	{
		return this.getClass().getTypeName();
		
	
	};*/

	String getName();
	/**
	 * 
	 * @return Category
	 */
	Category getCategory();
	
	/**
	 *  return le partType qui a instancie ce part 
	 * 
	 * @return PartType
	 */
	PartType getType();
	
}
