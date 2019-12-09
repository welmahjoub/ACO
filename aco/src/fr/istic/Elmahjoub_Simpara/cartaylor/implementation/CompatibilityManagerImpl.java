package fr.istic.Elmahjoub_Simpara.cartaylor.implementation;

import java.util.Objects;
import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.ConflictingRuleException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IsAlreadyExistingException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IsnotExistException;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.CompatibilityManager;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;

public class CompatibilityManagerImpl implements CompatibilityManager {

	
	/**
     * {@inheritDoc}
     */

	@Override
	public void addImcompatibilities(PartType reference, Set<PartType> target) throws ConflictingRuleException, IsAlreadyExistingException {
		
		Objects.requireNonNull(reference," la reference ne doit pas etre null");
		
		Objects.requireNonNull(target," la target ne doit pas etre null");
		
		//La refrence ne doit pas appartenir au target
		
		if(target.contains(reference))
			throw new ConflictingRuleException("la reference ne doit pâs apartenir au target");
	
	  
		/*
		 * La reference ne doit pas appartenir aux requirements
		 * de chaque element de target
		 */
		
		for (PartType partIncomp : target) {
			if(getRequirements(partIncomp).contains(reference))
			  throw new ConflictingRuleException("la reference ne doit pas apartenir aux requirements de l'incompatibilité");
			
		}
		
		/*chaque incomp de la liste des incompatibilitén'est pas pas deja 
		 * present dans la liste des incompatibiltés de la ref
		 */
		
		for (PartType partIncomp : target) {
			if(this.getIncompatibilities(reference).contains(partIncomp))
				throw new IsAlreadyExistingException("L'incompatibilité que vous essayer d'ajouter existe déjà");
		}
		
		// tous les target ne doit pas a partenir au meme categorie que reference 
		
		for (PartType partIncomp : target) {
			
			if(partIncomp.getCategory().equals(reference.getCategory()))
				throw new ConflictingRuleException("target ne doit pas appartenir à la meme catagorie que reference");
		}
		
		
		
		// caster la reference 
		PartTypeImpl ref=(PartTypeImpl)reference;
		
		//enfin :ajouter les incompatibiltés 
		for (PartType partType : target) {
			ref.addIncompatibiltiesParts(partType);
		}
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void removeIncompatibility(PartType reference, PartType target) throws IsnotExistException {
		
		Objects.requireNonNull(reference," la reference ne doit pas etre null");
		Objects.requireNonNull(target,"impossible de supprimer un objet null");
		
		// caster la reference 
		PartTypeImpl ref=(PartTypeImpl)reference;
				
		if(this.getIncompatibilities(reference).contains(target)) {
		  ref.removeIncompatibiltiesParts(target);
		}
		else {
			throw new IsnotExistException("L'élement à supprimer n'existe pas");
		}
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void addRequirements(PartType reference, Set<PartType> target) throws ConflictingRuleException, IsAlreadyExistingException {
		
		Objects.requireNonNull(reference," la reference ne doit pas etre null");
		
		Objects.requireNonNull(target," la target ne doit pas etre null");
		
		//La refrence ne doit pas appartenir au target
		
		if(target.contains(reference))
			throw new ConflictingRuleException("la reference ne doit pas apartenir au target");
	
		/*La reference ne doit pas appartenir aux imcompatibil de
		 *  chaque element de target
		 */
		for (PartType partRequir : target) {
			if(this.getIncompatibilities(partRequir).contains(reference))
			  throw new ConflictingRuleException("la reference ne doit pas apartenir aux requirements de l'icompatibilité");
			
		}
		
		/*chaque requirement de la liste des requirements n'est 
		 *  pas deja present dans la liste des requirements de la ref
		 */
		
		for (PartType partRequir : target) {
			if(this.getRequirements(reference).contains(partRequir))
				throw new IsAlreadyExistingException("la piece requise que vous essayer d'ajouter existe déjà");
		}
		
		// tous les target ne doit pas a partenir au meme categorie que reference 
		
		for (PartType partIncomp : target) {
			
			if(partIncomp.getCategory().equals(reference.getCategory()))
				throw new ConflictingRuleException("target ne doit pas appartenir a la meme catagorie que la reference ");
		}
		
		// caster la reference 
		PartTypeImpl ref=(PartTypeImpl)reference;
				
		//en fin l'ajouter les requirements 
		for (PartType partRequir : target) {
			ref.addRequirementsParts(partRequir);
		}
		
	}

	/**
     * {@inheritDoc}
     */
	
	@Override
	public void removeRequirement(PartType reference, PartType target) throws IsnotExistException {
		
		Objects.requireNonNull(reference," la reference ne doit pas etre null");
		Objects.requireNonNull(target,"impossible de supprimer un objet null");
		
		// caster la reference 
		PartTypeImpl ref=(PartTypeImpl)reference;
				
		if(this.getRequirements(reference).contains(target)) {
			ref.removeRequirementsParts(target);
			}
			else {
				throw new IsnotExistException("L'élement à supprimer n'existe pas");
			}	
	}

	/*
	 *                compatibility checker Methods
	 */
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Set<PartType> getIncompatibilities(PartType reference) {
		
		// caster la reference 
		PartTypeImpl ref=(PartTypeImpl)reference;
				
		return ref.getIncompatibitiesParts();
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public Set<PartType> getRequirements(PartType reference) {
		
		// caster la reference 
		PartTypeImpl ref=(PartTypeImpl)reference;
				
		return ref.getRequirementsParts();
	}

}
