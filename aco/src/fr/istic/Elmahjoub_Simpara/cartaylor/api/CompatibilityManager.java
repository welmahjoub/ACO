package fr.istic.Elmahjoub_Simpara.cartaylor.api;

import java.util.Set;

public interface CompatibilityManager extends CompatibilityChecker {

	/**
	 * addImcompatibilities :
	 * 	 add target  to reference as incompatible part :
	 *   add if and if all element in target can be incompatible with reference
	 *   else no one will  be add 
	 *   @param  reference
	 *   @param target
	 */

	void addImcompatibilities(PartType reference, Set<PartType> target);
    
	/**
	 * removeIncompatibility :
	 * remove target from list of incompatibility of reference
	 */

    void removeIncompatibility(PartType reference, PartType target);

    /**
   	 *  addRequirements :
   	 * 	 add target  to reference as requirement part :
   	 *   add if and if all element in target can be requirement with reference
   	 *   else no one will  be add
   	 */

    void addRequirements(PartType reference, Set<PartType> target);

    
    /**
	 * removeRequirement
	 * remove target from list of requirement of reference
	 */

    void removeRequirement(PartType reference, PartType target);

    
}
