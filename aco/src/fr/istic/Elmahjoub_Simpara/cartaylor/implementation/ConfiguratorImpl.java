package fr.istic.Elmahjoub_Simpara.cartaylor.implementation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Observer;
import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Subject;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.CompatibilityChecker;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.CompatibilityManager;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Configuration;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Configurator;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;

public class ConfiguratorImpl  implements Configurator  ,Observer<Configuration>{

	
	private Configuration configuration;
	private CompatibilityManager manager;
	
	
	/**
	 * 
	 */
	public ConfiguratorImpl() {

		manager=new CompatibilityManagerImpl();
		 
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Set<Category> getCategories() {
		
		return Fabrique.getCategories();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Set<PartType> getVariants(Category category) {
		
		//return category.getPartTypes();
		Set<PartType> partTypes = new HashSet<PartType>();
		
		Objects.requireNonNull(category," la categorie ne doit pas etre null");
		for (PartType partType : Fabrique.getPartTypes()) {

			if(partType.getCategory().getName().equals(category.getName()))
				 partTypes.add(partType);
		}
		
		return partTypes;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Configuration getConfiguration() {
		
		return configuration;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public CompatibilityChecker getCompatibilityChecker() {
		
		return manager;
	}


	/**
	 * 
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void update(Subject<Configuration> s) {
		
		this.configuration=s.getValue();
		
	}

}
