package fr.istic.Elmahjoub_Simpara.cartaylor.Exception;

public class IncompleteConfigurationException extends RuntimeException {

	/**
	 * classe qui representer type d'exception qui sera 
	 * lever lors de recuperation de la description html du configuration
	 * si la config n'est pas complet
	 */

	private static final long serialVersionUID = 1L;

	public IncompleteConfigurationException(String message) {
		super(message);
		
	}
	
}
