package fr.istic.Elmahjoub_Simpara.cartaylor.Model.Interior;

import java.util.HashSet;
import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Visitor;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.TypeInterior;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartImpl;

public class Interior extends PartImpl {


	// les proprietes qui sont encommun entre tous les part 
	// de type interior
	
	private TypeInterior  typeInterior = TypeInterior.standard;

	public String gettypeInterior() {
		return typeInterior.name();
	}


	public void settypeInterior(String type) {
		this.typeInterior=TypeInterior.valueOf(type);
	}


	public Interior() {
		Set<String> possibleValues ;
		possibleValues =new HashSet<String>();
		possibleValues.add(TypeInterior.High_end.name());
		possibleValues.add(TypeInterior.sport_finsih.name());
		possibleValues.add(TypeInterior.standard.name());
		
		addProperty("typeInterior", ()->gettypeInterior(), (c)->settypeInterior(c), possibleValues);
	}
	
	@Override
	public Double getPrice() {
		
		switch (typeInterior) {
		
		case standard: return 20000.0;
			
		case High_end: return 500000.0;
	
		case sport_finsih:return 700000.0;

		default: return 0.00;
			
		}
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitInterior(this);
	}
	
	@Override
	public String getDescription() {
		return "Notre interior est parmi les plus puissant à travers le monde";
	}

}
