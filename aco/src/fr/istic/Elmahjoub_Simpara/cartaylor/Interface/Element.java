package fr.istic.Elmahjoub_Simpara.cartaylor.Interface;

public interface Element {

	/**
	 * chaque element doit accepter la visite du visitor
	 * @param v
	 */
	public void accept(Visitor v) ;
	
}
