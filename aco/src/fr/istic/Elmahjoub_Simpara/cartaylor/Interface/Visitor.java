package fr.istic.Elmahjoub_Simpara.cartaylor.Interface;

import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.Engine;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Exterior.Exterior;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Interior.Interior;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission.Transmission;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Configuration;

public interface Visitor {

	/**
	 * visiter l'ensemble des parts dans la configuration passe en param 
	 * @param config
	 */
	public void visitConfiguration(Configuration config);
	
	/**
	 * visite un part de type engine( eg100 etc)
	 * @param egine
	 */
	public void visitEngine(Engine part);
	
	/**
	 * visite un part de type transmission( tm5 etc)
	 * @param egine
	 */
	public void visitTransmission(Transmission part);
	
	/**
	 * visite un part de type interior( in etc)
	 * @param egine
	 */
	public void visitInterior(Interior part);
	
	/**
	 * visite un part de type exterior( xs etc)
	 * @param interior
	 */
	public void visitExterior(Exterior part);
}
