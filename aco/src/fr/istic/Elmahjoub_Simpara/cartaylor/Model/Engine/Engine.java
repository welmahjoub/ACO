package fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine;

import java.util.HashSet;
import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Visitor;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Carburant;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartImpl;

public class Engine extends PartImpl {

	// les proprietes qui sont encommun entre tous les part 
	//de type engime
	
	private String power="0kw";
	private Carburant carburant=Carburant.diesel;

	public Engine() {
		
		Set<String>  possibleValues ;

		/* carburant */
		possibleValues =new HashSet<String>();
		possibleValues.add(Carburant.diesel.name());
		possibleValues.add(Carburant.essence.name());
		possibleValues.add(Carburant.gazoil.name());
		possibleValues.add(Carburant.electrique.name());
		
		addProperty("carburant", ()->getCarburant(), (c)->setCarburant(c), possibleValues);
		
		/* power */
		possibleValues =new HashSet<String>();
		possibleValues.add("100kw");
		possibleValues.add("133kw");
		possibleValues.add("210kw");
		possibleValues.add("110kw");
		possibleValues.add("180kw");
		possibleValues.add("120kw");
		
		addProperty("power", ()->getPower(), (c)->setPower(c),  possibleValues);
	
	}
	
	@Override
	public Double getPrice() {
		
		switch (carburant) {
				
		case essence:
			
		case gazoil: return 500000.0;
	
		case diesel:return 700000.0;

		case electrique:return 900000.0;
		
		default: return 0.00;
			
		}
	}

 
	@Override
	public void accept(Visitor v) {
		v.visitEngine(this);
	}
	
	@Override
	public String getDescription() {
		return "Notre moteur est parmi les plus puissant à travers le monde";
	}


	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getCarburant() {
		return carburant.name();
	}

	public void setCarburant(String carburant) {
		
		this.carburant = Carburant.valueOf(carburant);
	}

	
}
