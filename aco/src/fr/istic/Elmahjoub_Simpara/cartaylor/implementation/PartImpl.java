package fr.istic.Elmahjoub_Simpara.cartaylor.implementation;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Element;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;

public  abstract class PartImpl implements Part,Element {
	
	private PartType type;

	/**
	 *  return le prix d'un part
	 * @return Double
	 */
	public abstract Double getPrice();

	/**
	 * 
	 * @return String
	 */
	public abstract String getDescription() ;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		
		return getType().getName();
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public Category getCategory() {
		
		return this.type.getCategory();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public PartType getType() {
		
		return this.type;
	}
	

	/**
	 *  modifier le parttYPE
	 * @param type
	 */
	public void setType(PartType type) {
		this.type = type;
	}
	
	/**
	 * {@inheritDoc}
	 */



	/**
	 *  class interne represente une propriete qui 
	 *  sera ajouter au part 
	 * @author El Mahjoub
	 *
	 */
	private class Property {
		public final Supplier<String> getter;
		public final Consumer<String> setter;
		public final Set<String> possibleValues;

		Property(Supplier<String> getter, Consumer<String> setter, Set<String> possibleValues) {
			this.getter = getter;
			this.setter = setter;
			this.possibleValues = new HashSet<String>(possibleValues);

		}
	}

	private Map<String, Property> properties = new HashMap<>();

	/**
	 * To be used but subclasses only.
	 * 
	 * @param name
	 * @param getter
	 * @param setter
	 * @param possibleValues
	 */
	protected void addProperty(String name, Supplier<String> getter, Consumer<String> setter,
			Set<String> possibleValues) {
		properties.put(name, new Property(getter, setter, possibleValues));
	
	}

	
	/*
	 * Property management (from the definitions in PropertyManager)
	 */

	/**
	 * Returns an immutable set of the property names supported by the property
	 * manager.
	 * 
	 * @return
	 */
	@Override
	public Set<String> getPropertyNames() {
		return Collections.unmodifiableSet(properties.keySet());
	}

	/**
	 * Returns the optional value of a property. If the object does not support that
	 * property then an empty optional is returned.
	 * 
	 * @param propertyName the property to read
	 * @return
	 */
	@Override
	public Optional<String> getProperty(String propertyName) {
		Objects.requireNonNull(propertyName);

		if ((properties.containsKey(propertyName)) && (properties.get(propertyName).getter != null)) {
			return Optional.of(properties.get(propertyName).getter.get());
		}
		return Optional.empty();
	}

	/**
	 * Sets the value of a given property. If there is not such property, or if it
	 * not writable, or if the value is invalid then an IllegalArgumentException is
	 * thrown.
	 * 
	 * @param propertyName (must be non-null)
	 * @param propertyValue (must be non-null)
	 * @throws IllegalArgumentException (see above)
	 */
	@Override
	public void setProperty(String propertyName, String propertyValue) {
		Objects.requireNonNull(propertyName);
		Objects.requireNonNull(propertyValue);

		if ((properties.containsKey(propertyName)) && (properties.get(propertyName).setter != null)) {
			
			//La propriété ne fait partie des valeurs possibles
			if(!properties.get(propertyName).possibleValues.contains(propertyValue)) {
				throw new IllegalArgumentException("bad property value: " + propertyValue);
		
			}
			
			properties.get(propertyName).setter.accept(propertyValue);
		} else {
			throw new IllegalArgumentException("bad property name or setter not defined: " + propertyName);
		}
	}

	/**
	 * Returns the immutable set of discrete string values for a given property. For
	 * properties that have a non explicit set of possible values (eg double
	 * converted to strings), or for a non existing property name, returns an empty
	 * set.
	 * 
	 * @param propertyName a non-null string reference
	 * @return an immutable set (see above)
	 */
	@Override
	public Set<String> getAvailablePropertyValues(String propertyName) {
		Objects.requireNonNull(propertyName);

		if (properties.containsKey(propertyName)) {
			return Collections.unmodifiableSet(properties.get(propertyName).possibleValues);
		}
		return Collections.emptySet();
	}

}
