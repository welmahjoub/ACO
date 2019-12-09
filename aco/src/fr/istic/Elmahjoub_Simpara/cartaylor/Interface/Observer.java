package fr.istic.Elmahjoub_Simpara.cartaylor.Interface;

public interface Observer<V> {

	/**
	 * recuperer la nouvelle configuration
	 * @param s : encapsule la configuration 
	 */
	void update(Subject<V> s);

}
