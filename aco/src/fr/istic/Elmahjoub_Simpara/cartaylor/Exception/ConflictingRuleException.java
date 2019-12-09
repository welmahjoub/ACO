package fr.istic.Elmahjoub_Simpara.cartaylor.Exception;

public class ConflictingRuleException extends RuntimeException {

	/**
	 * classe qui representer type d'exception qui sera 
	 * lever lors de l'ajout des imcompatibilte ou requit PartType
	 */

	private static final long serialVersionUID = 1L;

	public ConflictingRuleException(String message) {
		super(message);
		
	}

	
	
}
