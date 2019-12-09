package fr.istic.Elmahjoub_Simpara.cartaylor.implementation;

import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Visitor;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.Engine;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Exterior.Exterior;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Interior.Interior;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission.Transmission;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Configuration;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;

public class HtmlDescriptionVisitor implements Visitor {
	
	/**
	 * attribut permet de stocker la description html du
	 *  configuration
	 */
	private StringBuilder sortie;
	
	public HtmlDescriptionVisitor(StringBuilder sortie) {
		this.sortie= sortie;
	} 
	
	/**
	 * genener le header du description
	 */
	public  void headerDescription() {
		sortie.append("<html> \n \t <head>Configuration</head> <Body> \n");
	}
	
	/**
	 * genener le footer du description
	 */
	public  void footerDescription() {
		sortie.append("</Body> \n </html> \n");
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitConfiguration(Configuration config) {
		
		// ecrire l'entete du description
		headerDescription();
		
		Set<Part> parts = config.getSelectedParts();
		
		for (Part part : parts) {
			PartImpl partimpl =(PartImpl) part;
			partimpl.accept(this);
		}
		
		sortie.append("<prixConfiguration>"+config.getPrice()+"</prixConfiguration> \n");

		// ecrire le pied du description
		footerDescription();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEngine(Engine part) {
		
		Set<String> properties = part.getPropertyNames();
		
		sortie.append("<Part>"+part.getName()+"</Part> \n");
		sortie.append("<Categorie>"+part.getCategory().getName()+"</Categorie> \n");
		sortie.append("<Type>"+part.getType().getName()+"</Type> \n");
		sortie.append("<Description>"+part.getDescription()+"</Description> \n");
		sortie.append("<prix>"+part.getPrice()+"</prix> \n");
		
		for (String property : properties) {
			sortie.append("<"+property+">");
	
			sortie.append(part.getProperty(property).get());
			
			sortie.append("<"+property+ "/> \n");
			
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitTransmission(Transmission part) {
Set<String> properties = part.getPropertyNames();
		
		sortie.append("<Part>"+part.getName()+"</Part> \n");
		sortie.append("<Categorie>"+part.getCategory().getName()+"</Categorie> \n");
		sortie.append("<Type>"+part.getType().getName()+"</Type> \n");
		sortie.append("<Description>"+part.getDescription()+"</Description> \n");
		sortie.append("<prix>"+part.getPrice()+"</prix> \n");
		
		for (String property : properties) {
			sortie.append("<"+property+">");
	
			sortie.append(part.getProperty(property).get());
			
			sortie.append("<"+property+ "/> \n");
			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitInterior(Interior part) {
Set<String> properties = part.getPropertyNames();
		
		sortie.append("<Part>"+part.getName()+"</Part> \n");
		sortie.append("<Categorie>"+part.getCategory().getName()+"</Categorie> \n");
		sortie.append("<Type>"+part.getType().getName()+"</Type> \n");
		sortie.append("<Description>"+part.getDescription()+"</Description> \n");
		sortie.append("<prix>"+part.getPrice()+"</prix> \n");
		
		for (String property : properties) {
			sortie.append("<"+property+">");
	
			sortie.append(part.getProperty(property).get());
			
			sortie.append("<"+property+ "/> \n");
			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitExterior(Exterior part) {
Set<String> properties = part.getPropertyNames();
		
		sortie.append("<Part>"+part.getName()+"</Part> \n");
		sortie.append("<Categorie>"+part.getCategory().getName()+"</Categorie> \n");
		sortie.append("<Type>"+part.getType().getName()+"</Type> \n");
		sortie.append("<Description>"+part.getDescription()+"</Description> \n");
		sortie.append("<prix>"+part.getPrice()+"</prix> \n");
		
		for (String property : properties) {
			sortie.append("<"+property+">");
	
			sortie.append(part.getProperty(property).get());
			
			sortie.append("<"+property+ "/> \n");
			
		}
	}

}
