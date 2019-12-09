package fr.istic.Elmahjoub_Simpara.cartaylor.Exception;

public class InvalidConfigurationException extends RuntimeException {

	
	/**
	 * classe qui representer type d'exception qui sera 
	 * lever lors de recuperation de la description html du configuration
	 * si la config n'est pas valid
	 */

	private static final long serialVersionUID = 1L;

	public InvalidConfigurationException(String message) {
		super(message);
		
	}

	
	
}
