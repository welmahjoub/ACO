package fr.istic.Elmahjoub_Simpara.cartaylor.implementation;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;

/**
 * @author El Mahjoub
 * @author Yaya
 * 
 *
 */
public class PartTypeImpl implements PartType {
	
	private String name;
	private Category category;
	private Class<? extends PartImpl> classRef;
	
	private Set<PartType> incompatibitiesParts;
	private Set<PartType> requirementsParts;
	
	/***
	 * 
	 * Constructor
	 * @param idPart
	 * @param namePart
	 * @param descriptionPart
	 * @param categoryPart
	 */
	public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category) {
		super();
		
		this.name = name;
		this.classRef= classRef;
		this.category = category;
		
		this.requirementsParts=new HashSet<>();
		this.incompatibitiesParts=new HashSet<>();
		
	}
	
	/**
	 *  Cree une instance de type part
	 *  
	 * @return PartImpl
	 */
	public PartImpl newInstance() {
		Constructor<? extends PartImpl> constructor;
		try {
			constructor = classRef.getConstructor();
			return constructor.newInstance();
		}catch(Exception e) {
			//Logger.getGlobal().log(Level.SEVERE,"constructor call failed",e);
			//System.exit(-1);	
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Category getCategory() {
		return category;
	}

	/** getIncompatibitiesParts
	 * 
	 * @return Set<PartType>
	 */

	public Set<PartType> getIncompatibitiesParts() {
		
		return  new HashSet<>(incompatibitiesParts);
	}
	
	/*
	 *  acces au liste des Incompatibilties
	 */
	
	/**
	 * addIncompatibiltiesParts
	 * @param parttype
	 * @return boolean
	 */
	public boolean addIncompatibiltiesParts(PartType parttype) {
		Objects.requireNonNull(parttype,"Can not add null object in incompatibilities");
		return incompatibitiesParts.add(parttype);
	}
	
	/** removeIncompatibiltiesParts
	 * 
	 * @param parttype
	 * @return
	 */
	public boolean removeIncompatibiltiesParts(PartType parttype) {
		Objects.requireNonNull(parttype,"Can not remove null object in incompatibilities");
		return incompatibitiesParts.remove(parttype);
	}

	
	/*
	 *  acces au liste des requirements 
	 */
	
	/** getRequirementsParts
	 * 
	 * @return Set<PartType>
	 */
	public Set<PartType> getRequirementsParts() {
		
		return new HashSet<>(requirementsParts);
	}
	
	/**
	 *  addRequirementsParts
	 * @param parttype
	 * @return
	 */
	public boolean addRequirementsParts(PartType parttype) {
		Objects.requireNonNull(parttype,"Can not add null object in requirements");
		return requirementsParts.add(parttype);
	}
	
	/**
	 *  removeRequirementsParts
	 * @param parttype
	 * @return
	 */
	public boolean removeRequirementsParts(PartType parttype) {
		Objects.requireNonNull(parttype,"Can not remove null object in requirements");
		//leve une exception si l'lmt n'existe pas dans  le set
		return requirementsParts.remove(parttype);
	}
	

	/**
	 * 
	 * @param namePart
	 */
	public void setName(String namePart) {
		this.name = namePart;
	}
	
	/**
	 * 
	 * @param categoryPart
	 */
	public void setCategory(Category categoryPart) {
		this.category = categoryPart;
	}
	
	
	
}
