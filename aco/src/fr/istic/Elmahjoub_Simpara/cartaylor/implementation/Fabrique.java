package fr.istic.Elmahjoub_Simpara.cartaylor.implementation;

import java.util.HashSet;
import java.util.Set;

import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.ConflictingRuleException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IsAlreadyExistingException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.ED110;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.ED180;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.EG100;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.EG133;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.EG210;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.EH120;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Exterior.XC;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Exterior.XM;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Exterior.XS;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Interior.IH;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Interior.IN;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Interior.IS;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission.TA5;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission.TC120;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission.TM5;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission.TM6;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission.TS6;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Transmission.TSF7;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;



public class Fabrique {
	
	/**
	 *  classe qui represente notre base de donnes 
	 *  sert a initialise tous les categories et leurs variantes
	 */
	
	private  static  Set<Category> categories;
	private  static  Set<PartType> setOfPartType;
	  
	//Categories
	private  static   Category engine;
	private  static   Category transmiss ;
	private  static   Category exterior;
	private  static   Category interior ;
	
	//Engines
	private  static  PartTypeImpl  eg100 ;
	private  static  PartTypeImpl  eg133;
	private  static  PartTypeImpl  ed110;
	private  static  PartTypeImpl  ed180;
	private  static  PartTypeImpl  eh120;
	private  static  PartTypeImpl  eg210;
	
	//Transmission
	private  static  PartTypeImpl  tm5;
	private  static  PartTypeImpl  tm6;
	private  static  PartTypeImpl  ta5;
	private  static  PartTypeImpl  ts6;
	private  static  PartTypeImpl  tsf7;
	private  static  PartTypeImpl  tc120;
	
	//Exterior
	private  static  PartTypeImpl  xc;
	private  static  PartTypeImpl  xm;
	private  static  PartTypeImpl  xs;
	
	//Interior
	private  static  PartTypeImpl  ine;
	private  static  PartTypeImpl  ih;
	private  static  PartTypeImpl  is;
	 

	/**
	 * Constructor static de la classe
	 */
	static  
	{				
		init();	
	}
	
	/**
	 *  creation de tous les parts et categories qui existe dans 
	 *  notre applications 
	 *  
	 * @throws ConflictingRuleException
	 * @throws IsAlreadyExistingException
	 */
	
	public static void  init() 
	{
	
		setOfPartType = new HashSet<PartType>();
	
	  engine = new CategoryImpl("Engine");
	  transmiss = new CategoryImpl("Transmission");
	  exterior = new CategoryImpl("Exterior");
	  interior = new CategoryImpl("Interior");
	  
	  categories= new HashSet<Category>();
	  categories.add(engine);
	  categories.add(transmiss);
	  categories.add(exterior);
	  categories.add(interior);
	  
	  //Creation des PartType de categorie Engine
	   eg100 =new PartTypeImpl("EG100",EG100.class,engine);
	   eg133 =new PartTypeImpl("EG133",EG133.class,engine);
	   eg210 =new PartTypeImpl("EG210",EG210.class,engine);
	   ed110 =new PartTypeImpl("ED110",ED110.class,engine);
	   ed180 =new PartTypeImpl("ED180",ED180.class,engine);
	   eh120 =new PartTypeImpl("EH120",EH120.class,engine);
	  

	  //Creation des Parttype de categorie Transmissions
	
	   tm5 =new PartTypeImpl("tm5",TM5.class,transmiss);
	   tm6 =new PartTypeImpl("TM6",TM6.class,transmiss);
	   ta5 =new PartTypeImpl("TA5",TA5.class,transmiss);
	   ts6 =new PartTypeImpl("TS6",TS6.class,transmiss);
	   tsf7 =new PartTypeImpl("TSF7",TSF7.class,transmiss);
	   tc120 =new PartTypeImpl("TC120",TC120.class,transmiss);
	  
	  //Creation des partType de categorie exterior
	   xc =new PartTypeImpl("XC",XC.class,exterior);
	   xm =new PartTypeImpl("XM",XM.class,exterior);
	   xs =new PartTypeImpl("XS",XS.class,exterior);
	   
	  
	  //Creation des partType de categorie interior
	   ine =new PartTypeImpl("IN",IN.class,interior);
	   ih =new PartTypeImpl("IH",IH.class,interior);
	   is =new PartTypeImpl("IS",IS.class,interior);
		   
		
	//initialisation des  requirements et incompatibles
	  
	// EH120 
	
	eh120.addRequirementsParts(tc120);
	
	// ta5
	ta5.addIncompatibiltiesParts(eg100);
	
	//TSF7 : 
	tsf7.addIncompatibiltiesParts(eg100);
	tsf7.addIncompatibiltiesParts(eg133);
	tsf7.addIncompatibiltiesParts(ed110);
	
	//TC120
	tc120.addRequirementsParts(eh120);
	
	//XC
	xc.addIncompatibiltiesParts(eg210);

	
	//XM
	xm.addIncompatibiltiesParts(eg100);
	
	
	//XS
	xs.addIncompatibiltiesParts(eg100);
	xs.addRequirementsParts(is);

	//IS
	is.addIncompatibiltiesParts(eg100);
	is.addIncompatibiltiesParts(tm5);
	is.addRequirementsParts(xs);
	
	
	//Ajout des engines dans la liste de partype
	  setOfPartType.add(eg100);
	  setOfPartType.add(eg133);
	  setOfPartType.add(eg210);
	  setOfPartType.add(ed110);
	  setOfPartType.add(ed180);
	  setOfPartType.add(eh120);
	  
	  //Ajout des Transmission dans la liste de partype
	  setOfPartType.add(tm5);
	  setOfPartType.add(tm6);
	  setOfPartType.add(ta5);
	  setOfPartType.add(ts6);
	  setOfPartType.add(tsf7);
	  setOfPartType.add(tc120);
	  
	  //Ajout des exterior dans la liste de partype
	  setOfPartType.add(xc);
	  setOfPartType.add(xm);
	  setOfPartType.add(xs);
	  
	//Ajout des interior dans la liste de partype
	  setOfPartType.add(ine);
	  setOfPartType.add(ih);
	  setOfPartType.add(is);
		
	}

	/**
	 *  return nombre de categories dans notre application
	 * @return int 
	 */
	public static int getNbCategorie() {
		return categories.size();
		
		} 
	
	/**
	 * return tous les categories qui existent
	 * @return Set<Category>
	 */
	public static Set<Category> getCategories(){
		return  new HashSet<Category>(categories);
	}
	
	/**
	 * return tous lespartTypes qui existent
	 * @return Set<Category>
	 */
	public static Set<PartType> getPartTypes(){
		return  new HashSet<PartType>(setOfPartType);
	}

	/**
	 * 
	 * @return Category
	 */
	public static Category getEngine() {
		return engine;
	}
	
	/*
	 *  les differents categories
	 */
	public static Category getTransmiss() {
		return transmiss;
	}

	public static Category getExterior() {
		return exterior;
	}

	public static Category getInterior() {
		return interior;
	}
    
	/*
	 *  les differents variantes de engine
	 */
	public static Part getEg100() {
		
			PartImpl part=	eg100.newInstance();
			part.setType(eg100);
			return part;
	}

	public static Part getEg133() {
		PartImpl part=	eg133.newInstance();
		part.setType(eg133);
		return part;
		
	}
	
	public static Part getEg210() {
		PartImpl part=	eg210.newInstance();
		part.setType(eg210);
		return part;
		
	}

	public static Part getEd110() {
		PartImpl part=	ed110.newInstance();
		part.setType(ed110);
		return part;
		
	}

	public static Part getEd180() {
		PartImpl part=	ed180.newInstance();
		part.setType(ed180);
		return part;
		
	}


	public static Part getEh120() {
		
		PartImpl part=	eh120.newInstance();
		part.setType(eh120);
		return part;

	}

	/*
	 *  les differents variantes du transmission
	 */
	public static Part getTm5() {
		
		PartImpl part=	tm5.newInstance();
		part.setType(tm5);
		return part;
	}
	
	public static Part getTm6() {
		
		PartImpl part=	tm6.newInstance();
		part.setType(tm6);
		return part;
	}

	public static Part getTa5() {
		PartImpl part=	ta5.newInstance();
		part.setType(ta5);
		return part;
	}

	public static Part getTs6() {
		
		PartImpl part=	ts6.newInstance();
		part.setType(ts6);
		return part;
		
	}

	public static Part getTsf7() {
		PartImpl part=	tsf7.newInstance();
		part.setType(tsf7);
		return part;
	}

	public static Part getTc120() {
		PartImpl part=	tc120.newInstance();
		part.setType(tc120);
		return part;
	}

	/*
	 *  les differents variantes du exterior
	 */
	public static Part getXc() {
		
		PartImpl part=	xc.newInstance();
		part.setType(xc);
		return part;
		
	}

	public static Part getXm() {
		
		PartImpl part=	xm.newInstance();
		part.setType(xm);
		return part;
		
	}

	public static Part getXs() {
	
		PartImpl part=	xs.newInstance();
		part.setType(xs);
		return part;
		
	}

	/*
	 *  les differents variantes du interior
	 */
	public static Part getIne() {
		PartImpl part=	ine.newInstance();
		part.setType(ine);
		return part;
		
	}

	public static Part getIh() {
		PartImpl part=	ih.newInstance();
		part.setType(ih);
		return part;
		
	}

	public static Part getIs() {
		PartImpl part=	is.newInstance();
		part.setType(is);
		return part;
	}

}
