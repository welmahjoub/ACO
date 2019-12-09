package fr.istic.Elmahjoub_Simpara.cartaylor.Model.Exterior;

import java.util.HashSet;
import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Visitor;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Color;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartImpl;

public class Exterior extends PartImpl {
	
	// les proprietes qui sont en commun entre tous les part 
	//de type exterior
	
	private Color paintColor = Color.White;
	
	
	public String getColor() {
		return paintColor.name();
	}


	public void setColor(String color) {
		Color newColor = Color.valueOf(color);
		this.paintColor = newColor;
	}


	public Exterior() {
		
		Set<String> possibleValues ;
		possibleValues =new HashSet<String>();
		possibleValues.add(Color.Blue.name());
		possibleValues.add(Color.White.name());
		possibleValues.add(Color.Red.name());
		possibleValues.add(Color.Yellow.name());
		addProperty("color", ()->getColor(), (c)->setColor(c), possibleValues);
	}
	
	@Override
	public Double getPrice() {
		
		switch (paintColor) {
		
		case Blue:
			
		case White: return 500000.0;
	
		case Red:return 700000.0;

		default: return 0.00;
			
		}
	}

	@Override
	public void accept(Visitor v) {
		v.visitExterior(this);
	}
	
	@Override
	public String getDescription() {
		return "Notre Exterior est parmi les plus puissant à travers le monde";
	}
}
