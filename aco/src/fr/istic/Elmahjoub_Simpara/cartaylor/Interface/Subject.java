package fr.istic.Elmahjoub_Simpara.cartaylor.Interface;

/**
 * @author elmahjoub , Simpara
 */
public interface Subject<V> {
	
	/**
	 * ajoute le observer depuis la liste des observateurs du sujects
	 * 
	 * @param o
	 */
	public void attach(Observer<V> o);
	/**
	 *  supprimer le observer depuis la liste des observateurs du sujects
	 * @param o
	 */
	public void detach(Observer<V> o);
	/**
	 *  tester si un observer est deja abonne au sujet 
	 * @param observer
	 * @return
	 */
	public boolean isAttached(Observer<V> observer);
	/**
	 *  recuperer la nouvelle configuration
	 * @return
	 */
	public V getValue();
}
