package fr.istic.Elmahjoub_Simpara.cartaylor.api;

import java.util.Set;

public interface CompatibilityChecker {

	/**
	 * @return list of Incompatibilities part of reference
	 */

    Set<PartType> getIncompatibilities(PartType reference);

    /**
   	 * @return list of requirement part of reference
   	 */

    Set<PartType> getRequirements(PartType reference);

}
