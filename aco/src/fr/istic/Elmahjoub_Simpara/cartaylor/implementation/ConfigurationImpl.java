package fr.istic.Elmahjoub_Simpara.cartaylor.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IncompleteConfigurationException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.InvalidConfigurationException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IsnotExistException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Observer;
import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Subject;
import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Visitor;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.CompatibilityManager;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Configuration;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PropertyManager;

public class ConfigurationImpl implements Configuration,PropertyManager , Subject<Configuration>{

	//Contient toutes les pièces selectionnées
	private Set<Part> listeofParts;
	
	// liste des observateurs 
	private Collection<Observer<Configuration>> observers;
	
	/**
	 * constructor
	 */
	
	public ConfigurationImpl()
	{
		listeofParts=new HashSet<Part>();
		observers = new ArrayList<Observer<Configuration>>();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid() {
		
		CompatibilityManager manager=new CompatibilityManagerImpl();
		
		
		//1 )tester si il ya pas deux pieces de meme category
		 List<Category> listofCatg=new ArrayList<Category>();
		 Set<Category> EnsofCategory=new HashSet<Category>();
		 
		 for (Part part : listeofParts) {
			 
			 listofCatg.add(part.getCategory());
			 EnsofCategory.add(part.getCategory());
		}

		if(EnsofCategory.size()!=listofCatg.size())
			return false;
		
		/*
		 *  construire la liste des partTypes a partir des parts
		*  car on a besoin des liste des imcompatibilite et requise 
		*  de chaque partType dans la configuration 
		*/
		
	   Set<PartType> lisetofPartTypes=new HashSet<PartType>();
		
		for (Part part : listeofParts) {
			 
			lisetofPartTypes.add(part.getType());
		}
		
		//parcourir la liste des pieces selectionner 
		for (PartType partType : lisetofPartTypes) {
			
			Set<PartType> listeRequirements=manager.getRequirements(partType);
			
			Set<PartType> listeIncomp=manager.getIncompatibilities(partType);
			
			// 2) la liste des requirements de chaque pieces selectionnées doivent exister dans la conf
			if(! lisetofPartTypes.containsAll(listeRequirements))
				return false;
			
			// 3) les incompatibiltés de chaque pièce ne doivent pas être dans la config
			for (PartType incomp : listeIncomp) {
				
				if(lisetofPartTypes.contains(incomp))
					return false;
			}
			
		}
		
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isComplete() {
		
		//Contient les categories des pièces selectionnées
		 Set<Category> ensofCategory=new HashSet<Category>();
		 
		 for (Part part : listeofParts) {
			 ensofCategory.add(part.getCategory());
			 
		}
			 
		return ensofCategory.size()==Fabrique.getNbCategorie();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Part> getSelectedParts() {
		
		return new HashSet<>(listeofParts);
	} 

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectPart(Part chosenPart) {
		
		Objects.requireNonNull(chosenPart,"la pièce à ajouter ne doit pas etre null");
		
		listeofParts.add(chosenPart);
		
		notifyObservers();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Part> getSelectionForCategory(Category category)  {
		
		Objects.requireNonNull(category," la categorie ne doit pas etre null");
		for (Part part : listeofParts) {
			
			if(part.getCategory().getName().equals(category.getName()))
				return Optional.of(part);
		}
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unselectPartType(Category categoryToClear){
		
		// recupere la pieces selectioner pour cette categorie
		Optional<Part> PartToremove=getSelectionForCategory(categoryToClear);
		
		if(!PartToremove.isPresent())
			throw new IsnotExistException("pas de pieces selectioner qui correspond a cette categorie");
		
		listeofParts.remove(PartToremove.get());// supprimer partType
	
		notifyObservers();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		
		listeofParts.clear();
		
		notifyObservers();

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getPrice() {
		Double prices=0.0;
		if(!this.isValid()) { 
			
			throw new InvalidConfigurationException("configuration invalide");
			}
		
		for (Part part : listeofParts) {
			
			PartImpl partImpl = (PartImpl) part;
			prices +=  partImpl.getPrice();
		}
		return prices;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		
		if(!this.isComplete()) {
			throw new IncompleteConfigurationException("Configuration imcomplete");
		}
		if(!this.isValid()) {
			
			throw new InvalidConfigurationException("Configuration imcomplete");

		}
		
		StringBuilder resultat = new StringBuilder();
		
		Visitor visitor = new HtmlDescriptionVisitor(resultat);
		visitor.visitConfiguration(this);
		return resultat.toString();
	}

	/*
	 *  implementation de property Manager pour le part Exterior
	 */
	
	/**
	 * 
	 * {@inheritDoc}
	 * elle leve une exception si le part exterior n'existe pas dans la config
	 */
	@Override
	public Set<String> getPropertyNames() {
		
		Optional<Part> exterior=getSelectionForCategory(Fabrique.getExterior());
		
		if(exterior.isEmpty())
		{
			throw new IsnotExistException("la config ne contient pas un part de type exterior");
		}
		
		PartImpl ext=(PartImpl) exterior.get();
		
		return ext.getPropertyNames();
		
	}

	/**
	 * 
	 * {@inheritDoc}
	 * elle leve une exception si le part exterior n'existe pas dans la config
	 */
	@Override
	public Set<String> getAvailablePropertyValues(String propertyName) {
		
		Optional<Part> exterior=getSelectionForCategory(Fabrique.getExterior());
		
		if(exterior.isEmpty())
		{
			throw new IsnotExistException("la config ne contient pas un part de type exterior");
		}
		
		PartImpl ext=(PartImpl) exterior.get();
		
		return ext.getAvailablePropertyValues(propertyName);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * elle leve une exception si le part exterior n'existe pas dans la config
	 */
	@Override
	public Optional<String> getProperty(String propertyName) {
		
		Optional<Part> exterior=getSelectionForCategory(Fabrique.getExterior());
		
		if(exterior.isEmpty())
		{
			throw new IsnotExistException("la config ne contient pas un part de type exterior");
		}
		
		PartImpl ext=(PartImpl) exterior.get();
		
		return ext.getProperty(propertyName);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * elle leve une exception si le part exterior n'existe pas dans la config
	 */
	@Override
	public void setProperty(String propertyName, String propertyValue) throws IllegalArgumentException{
		
		Optional<Part> exterior=getSelectionForCategory(Fabrique.getExterior());
		
		if(exterior.isEmpty())
		{
			throw new IsnotExistException("la config ne contient pas un part de type exterior");
		}
		
		PartImpl ext=(PartImpl) exterior.get();
		
		ext.setProperty(propertyName, propertyValue);
		
	}
	
	/*
	 *  implementation de Subject 
	 */
	

	/**
	 *  envoi une notification a tous les observers ( configurator)
	 *  
	 */
	
	private void notifyObservers() {
		// L'opération notify est déjà définie dans Object
		for(Observer<Configuration> o : observers) {
			o.update(this);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void attach(Observer<Configuration> o) {
		observers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void detach(Observer<Configuration> o) {
		observers.remove(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAttached(Observer<Configuration> observer) {
		return observers.contains(observer);
	}
	/**
	 * {@inheritDoc}
	 */

	@Override
	public Configuration getValue() {
		
		return this;
	}

}
