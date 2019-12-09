package fr.istic.Elmahjoub_Simpara.cartaylor.Exception;

public class IsAlreadyExistingException extends RuntimeException {

	/**
	 * classe qui representer type d'exception qui sera 
	 * lever lors de l'ajout des imcompatibilte ou requit PartType
	 */

	private static final long serialVersionUID = 1L;
		
	public IsAlreadyExistingException(String message) {
		super(message);
		
	}
}
