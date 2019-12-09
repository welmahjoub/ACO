package fr.istic.Elmahjoub_Simpara.cartaylor.Exception;

public class IsnotExistException extends RuntimeException {

	/**
	 * classe qui representer type d'exception qui sera 
	 * lever lors de  suppression des imcompatibilte ou requit PartType
	 */


	private static final long serialVersionUID = 1L;

	public IsnotExistException(String message) {
		super(message);
		
	}


}
