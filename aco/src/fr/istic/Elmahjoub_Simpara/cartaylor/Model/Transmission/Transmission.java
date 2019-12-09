package fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission;

import java.util.HashSet;
import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Visitor;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.BoiteVitesse;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartImpl;

public class Transmission extends PartImpl {

	// les proprietes qui sont encommun entre tous les part 
	// de type transmission
	
	private BoiteVitesse boite = BoiteVitesse.Manual;
	private String embrayage="gears";
	
	
	public String getEmbrayage() {
		return embrayage;
	}

	public String getBoite() {
		
		return boite.name();
	}

	public void setBoite(String boite) {
		BoiteVitesse newBoite = BoiteVitesse.valueOf(boite);
		this.boite = newBoite;
	}

	public void setEmbrayage(String emb) {
		this.embrayage=emb;
	}

	public Transmission() {
		Set<String> possibleValues =new HashSet<String>();
		
		possibleValues.add(BoiteVitesse.Manual.name());
		possibleValues.add(BoiteVitesse.Automatic.name());
		possibleValues.add(BoiteVitesse.Sequential.name());
		possibleValues.add(BoiteVitesse.Converter.name());
		
		addProperty("boite", ()->getBoite(), (c)->setBoite(c), possibleValues);
	
		possibleValues =new HashSet<String>();
		
		possibleValues.add("6gears");
		possibleValues.add("5gears");
		possibleValues.add("7gears");
		possibleValues.add("120kwmax");

		addProperty("embrayage", ()->getEmbrayage(), (c)->setEmbrayage(c), possibleValues);
	
	}
	
	@Override
	public Double getPrice() {
		
		switch (boite) {
		
		case Manual:
			
		case Converter: return 500000.0;
	
		case Automatic:return 700000.0;

		case Sequential:return 800000.0;
		
		default: return 0.00;
			
		
		}
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitTransmission(this);
	}
	
	@Override
	public String getDescription() {
		return "Notre Transmission est parmi les plus puissant à travers le monde";
	}

}
