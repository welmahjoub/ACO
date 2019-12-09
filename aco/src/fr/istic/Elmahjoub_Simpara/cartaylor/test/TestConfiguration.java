package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.InvalidConfigurationException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IsnotExistException;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.ConfigurationImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartImpl;

/**
 * @author El Mahjoub
 *
 */
class TestConfiguration {
	
	private ConfigurationImpl configuration ;
	

	@BeforeEach
	void setUp() throws Exception {
		
		configuration=new ConfigurationImpl();
	}

	
	@Test
	@DisplayName("test const : configuration vide ")
	void testConfigurationImpl() {
		
		assertTrue(configuration.isValid());
		assertFalse(configuration.isComplete());
		
	}
	
	/*
	 *    isValid
	 */
	
	
	@Test
	@DisplayName("test is valid 1 : configuration valide  ")
	void testIsValid1() {
		
		Part eg100=Fabrique.getEg100();
		
		configuration.selectPart(eg100);
		
		assertTrue(configuration.isValid(),"configuration  doit  etre valid");
		
	}
	
	@Test
	@DisplayName("test is valid 2 : configuration valid  ")
	void testIsValid2() {
		
		Part eg100=Fabrique.getEg100();
		Part tm5=Fabrique.getTm5();
		Part xc=Fabrique.getXc();
		Part in=Fabrique.getIne();
		
		configuration.selectPart(eg100);
		configuration.selectPart(tm5);
		configuration.selectPart(xc);
		configuration.selectPart(in);
		
		assertTrue(configuration.isValid(),"config  doit  etre valid");
		
	}
	
	
	@Test
	@DisplayName(" isvalid3 : configuration  no valid  ( PartType imcompatible ) ")
	void testIsValid3() {
		
		Part eg100=Fabrique.getEg100();
		Part xs=Fabrique.getXs();
	
		
		configuration.selectPart(eg100);
		configuration.selectPart(xs);
		
		/* car xs et eg100 sont imcompatible */
		 
		
		assertFalse(configuration.isValid(),"configuration ne doit pas etre valid");
		
	}
	


	@Test
	@DisplayName(" isvalid4 : configuration  no valid ( PartType imcompatible )   ")
	void testIsValid4() {
		
		Part eg100=Fabrique.getEg100();
		Part tm5=Fabrique.getTm5();
		Part is=Fabrique.getIs();
		
		configuration.selectPart(eg100);
		configuration.selectPart(tm5);
		configuration.selectPart(is);
		
		/* configuration non valid :  car   is et eg100 sont imcompatible 
		 *   et is et tm5 
		 */ 
		
		assertFalse(configuration.isValid(),"configuration ne doit pas etre valid");
		
	}
	
	@Test
	@DisplayName("test is completed 5 :config  no valid (5 partType ) ")
	void testIssValid5() {
		
		
		Part eg100=Fabrique.getEg100();
		Part eg133=Fabrique.getEg133();
		Part tm5=Fabrique.getTm5();
		Part xc=Fabrique.getXc();
		Part in=Fabrique.getIne();
		
		configuration.selectPart(eg100);
		configuration.selectPart(eg133);
		configuration.selectPart(tm5);
		configuration.selectPart(xc);
		configuration.selectPart(in);
		
		assertFalse(configuration.isValid(),"config  doit pas etre valid car contient 5 partTyPE ");
		
	}
	
	@Test
	@DisplayName(" isvalid6 : configuration  no valid (manquePartType requise )   ")
	void testIsValid6() {
		
		Part eg133=Fabrique.getEg133();
		Part tm5=Fabrique.getTm5();
		Part xs=Fabrique.getIs();
		
		configuration.selectPart(eg133);
		configuration.selectPart(tm5);
		configuration.selectPart(xs);
		
		/* configuration non valid :  
		 * car xs requise IS et is n'est pas dans la configuration  
		 */ 
		
		assertFalse(configuration.isValid(),"configuration ne doit pas etre valid");
		
	}
	
	@Test
	@DisplayName(" isvalid6 : configuration  no valid (deux part imcompatible )   ")
	void testIsValid7() {
		
		Part eg100=Fabrique.getEg100();
		Part ta5=Fabrique.getTa5();
	
		configuration.selectPart(eg100);
		configuration.selectPart(ta5);
	
		
		/* configuration non valid :  
		 * car eg100 et ta5 sont imcompatible  
		 */ 
		
		assertFalse(configuration.isValid(),"configuration ne doit pas etre valid");
		
	}

	/*
	 *    isComplete
	 */
	
	@Test
	@DisplayName("test is completed  :config non complet (1..3 partType )")
	void testIsComplete() {
		
		
		// l'ajout d'un partType 
		
		Part eg100=Fabrique.getEg100();
		
		configuration.selectPart(eg100);
		
		assertFalse(configuration.isComplete(),"config ne doit pas etre complet");
		
		// l'ajout du deuxieme  partType 
		
		Part tm5=Fabrique.getTm5();
		configuration.selectPart(tm5);
		
		assertFalse(configuration.isComplete(),"configuration ne doit pas etre complet");
		
		// l'ajout du troisieme  partType 
		
		Part xc=Fabrique.getXc();
		configuration.selectPart(xc);
		
		assertFalse(configuration.isComplete(),"config ne doit pas etre complet");
		
	}

	@Test
	@DisplayName("test is completed 2 :config  complet (4 partType ) ")
	void testIsComplete2() {
		
		
		
		Part eg100=Fabrique.getEg100();
		Part tm5=Fabrique.getTm5();
		Part xc=Fabrique.getXc();
		Part in=Fabrique.getIne();
		
		configuration.selectPart(eg100);
		configuration.selectPart(tm5);
		configuration.selectPart(xc);
		configuration.selectPart(in);
		
		assertTrue(configuration.isComplete(),"config  doit  etre complet");
		
	}
	
	@Test
	@DisplayName("test is completed 3 :config  complet (5 partType ) ")
	void testIsComplete3() {
		
		
		Part eg100=Fabrique.getEg100();
		Part eg133=Fabrique.getEg133();
		Part tm5=Fabrique.getTm5();
		Part xc=Fabrique.getXc();
		Part in=Fabrique.getIne();
		
		configuration.selectPart(eg100);
		configuration.selectPart(eg133);
		configuration.selectPart(tm5);
		configuration.selectPart(xc);
		configuration.selectPart(in);
		
		assertTrue(configuration.isComplete(),"config  doit  etre complet");
		
	}
	
	/*
	 * getSelectedParts
	 */
	
	@Test
	@DisplayName("test GetSelected Parts ")
	void testGetSelectedParts() {
		
		Part eg100=Fabrique.getEg100();
		Part tc=Fabrique.getTa5();
		
		configuration.selectPart(eg100);
		configuration.selectPart(tc);
		
		Set<Part> ensOfParts=configuration.getSelectedParts();
		
		assertNotNull(ensOfParts," l'ensemble ne doit pas être null");
		assertFalse(ensOfParts.isEmpty(),"L'ensemble ne doit pas être vide");
		assertTrue(ensOfParts.size()==2,"L'ensemble doit contenir 2 part Type");
		
		assertTrue(ensOfParts.contains(eg100),"L'ensemble doit contenir eg100");
		assertTrue(ensOfParts.contains(tc),"L'ensemble doit contenir is");
		
	}

	/*
	 * selectPart
	 */
	
	@Test
	@DisplayName("Test select Part 1  ")
	void testSelectPart() {
		
		// ajout de deux partType 
		
		Part eg100=Fabrique.getEg100();
		Part tc=Fabrique.getTa5();
		
		configuration.selectPart(tc);
		configuration.selectPart(eg100);
		
		
		Set<Part> ensOfParts;
		ensOfParts=configuration.getSelectedParts();
		
		assertNotNull(ensOfParts," l'ensemble ne doit pas être null");
		assertFalse(ensOfParts.isEmpty(),"L'ensemble ne doit pas être vide");
		assertTrue(ensOfParts.size()==2,"L'ensemble doit contenir 2 part Type");
		
		assertTrue(ensOfParts.contains(eg100),"L'ensemble doit contenir eg100");
		assertTrue(ensOfParts.contains(tc),"L'ensemble doit contenir tc");
		
		// on rajoute d'autre partTypes
		
		Part xs=Fabrique.getXs();
		Part is=Fabrique.getIs();
		
		configuration.selectPart(is);
		configuration.selectPart(xs);
		
		ensOfParts=configuration.getSelectedParts();
		
		assertTrue(ensOfParts.size()==4,"L'ensemble doit contenir 4 part Type");
		
	}
	
	@Test
	@DisplayName("Test select Part 1  ")
	void testSelectPart2() {
		
		// ajout de deux partType 
		
		Part Tm6=Fabrique.getTm6();
		Part eg210=Fabrique.getEg210();
		
		configuration.selectPart(Tm6);
		configuration.selectPart(eg210);
		
		
		Set<Part> ensOfParts;
		ensOfParts=configuration.getSelectedParts();
		
		assertNotNull(ensOfParts," l'ensemble ne doit pas être null");
		assertFalse(ensOfParts.isEmpty(),"L'ensemble ne doit pas être vide");
		assertTrue(ensOfParts.size()==2,"L'ensemble doit contenir 2 part Type");
			
	}
	
	/*
	 * getSelectionForCategory
	 */

	@Test
	@DisplayName("test GetSelection For Category")
	void testGetSelectionForCategory() {
		
		Part eg100=Fabrique.getEg100();	
		Category engime=Fabrique.getEngine();
		
		// ajouter du eg100
		configuration.selectPart(eg100);
		
		Optional<Part> engineChosen=configuration.getSelectionForCategory(engime);
		
		assertNotNull(engineChosen, "engime selectionner ne doit pas etre null ");
		
		assertTrue(((PartImpl) engineChosen.get()).getCategory().equals(engime), "doit être de la meme categorie");
		
	}

	/*
	 *   unselectPartType
	 */
	
	@Test
	@DisplayName("test Unselect PartType 1 ")
	void testUnselectPartType1() throws IsnotExistException {
		
		Part eg100=Fabrique.getEg100();
		Part tm5=Fabrique.getTm5();
		Part xs=Fabrique.getXs();
		Part is=Fabrique.getIs();
		
		Category engime=Fabrique.getEngine();
		
		// ajouter des pieces
		configuration.selectPart(eg100);
		configuration.selectPart(tm5);
		configuration.selectPart(is);
		configuration.selectPart(xs);
		
		assertTrue(configuration.getSelectedParts().contains(eg100),"L'ensemble doit contenir eg100");
		
		//suppresion du categorie engime
		configuration.unselectPartType(engime);
		
		Set<Part> ensOfParts=configuration.getSelectedParts();
		
		assertNotNull(ensOfParts," l'ensemble ne doit pas être null");
		assertFalse(ensOfParts.isEmpty(),"L'ensemble ne doit pas être vide");
		
		assertFalse(ensOfParts.contains(eg100),"L'ensemble ne doit plus contenir eg100");
		
		assertTrue(ensOfParts.contains(tm5),"L'ensemble doit contenir tm5");
		assertTrue(ensOfParts.contains(xs),"L'ensemble doit contenir xs");
		assertTrue(ensOfParts.contains(is),"L'ensemble doit contenir is");
		
		assertTrue(ensOfParts.size()==3,"L'ensemble doit contenir 3 part Type");
		
		
	}

	
	
	@Test
	@DisplayName("test Unselect PartType 2 sans exception ")

	void testUnselectPartType2() throws IsnotExistException {
		
		Part eg100=Fabrique.getEg100();
		
		Category engime=Fabrique.getEngine();
		
		// l'ajout  d'une   piece
		configuration.selectPart(eg100);
		
		//suppresion du categorie engime
		
		assertDoesNotThrow(()-> configuration.unselectPartType(engime)
				," unselectPartType ne doit pas lever une exception");
		
	}
	
	
	@Test
	@DisplayName("test Unselect PartType 3 avec exception ")
	void testUnselectPartType3() throws IsnotExistException {
		
		Part eg100=Fabrique.getEg100();
		
		Category transmision=Fabrique.getTransmiss();
		
		// ajouter des pieces
		configuration.selectPart(eg100);
		 	
		Assertions.assertThrows(IsnotExistException.class,()->{
			configuration.unselectPartType(transmision);
		}," unselectPartType doit lever IsnotExistException ");
		
	}
	
	/*
	 * clear
	 */
	
	@Test
	@DisplayName("test clear")
	void testClear() {
		
		Part eg100=Fabrique.getEg100();
		Part tm5=Fabrique.getTm5();
		Part xs=Fabrique.getXs();
		Part is=Fabrique.getIs();
		
		// ajouter des pieces
		configuration.selectPart(eg100);
		configuration.selectPart(tm5);
		configuration.selectPart(is);
		configuration.selectPart(xs);
		
		//vider la config
		configuration.clear();
		
		Set<Part> ensOfParts=configuration.getSelectedParts();	
		assertNotNull(ensOfParts," l'ensemble ne doit pas être null");
		assertTrue(ensOfParts.isEmpty(),"L'ensemble  doit  être vide");
		
	}

  
	/*
	 * testGetPrice
	 */
	@Test
	@DisplayName("prix  d'une configuration  no valid ")
	public void testGetPrice1()
	{
		
		Part eg100=Fabrique.getEg100();
		Part TA5=Fabrique.getTa5();
		
		configuration.selectPart(eg100);
		configuration.selectPart(TA5);
		
		assertThrows(InvalidConfigurationException.class, ()->configuration.getPrice());
		
			
	}
	
	@Test
	@DisplayName("prix  d'une configuration valid ")
	public void testGetPrice2()
	{
		assertTrue(configuration.getPrice()==0.0);
		
		Part eg100=Fabrique.getEg100();
		Part tm5=Fabrique.getTm5();
		Part xc=Fabrique.getXc();
		Part in=Fabrique.getIne();
		
		configuration.selectPart(eg100);
		configuration.selectPart(tm5);
		configuration.selectPart(xc);
		configuration.selectPart(in);
		
		Double prix=700000.0+500000.0+500000.0+20000.0;
		
		assertEquals(prix, configuration.getPrice());

	}
	
	
	/*
	 * test property Manger de part exterior
	 */
	
	@Test
	@DisplayName("getPropertyNames vide ")
	public void testgetPropertyNames()
	{
		//Les exterieurs contiennent la propriété color
		
		assertThrows(IsnotExistException.class, ()->
		{
			configuration.getPropertyNames();
		});
		
		
	}
	
	/*
	 * getPropertyNames
	 */
	
	@Test
	@DisplayName("getPropertyNames configuration contient exterior ")
	public void testgetPropertyNames2()
	{
		//Les exterieurs contiennent la propriété color
		
		Part exterior=Fabrique.getXs();
		configuration.selectPart(exterior);
		
		Set<String> properties = configuration.getPropertyNames();
		
		assertNotNull(properties,"Les proprietés ne sont pas vide");
		
		assertTrue(properties.contains("color"),"doit contenir color");
		
	}
	
	/*
	 * GetAvailablePropertyValues
	 */
	
	@Test
	@DisplayName("GetAvailablePropertyValues configuration vide")
	public void testGetAvailablePropertyValues()
	{
		
		assertThrows(IsnotExistException.class, ()->
		{
			configuration.getAvailablePropertyValues("color");
		});

	}
	
	@Test
	@DisplayName("GetAvailablePropertyValues  configuration contient exterior")
	public void testGetAvailablePropertyValues2()
	{
		
		Part exterior=Fabrique.getXs();
		configuration.selectPart(exterior);
		
		Set<String> availableValue = configuration.getAvailablePropertyValues("color");
		
		assertNotNull(availableValue,"Ne doit pas être nul");
		
		assertTrue(availableValue.contains("Blue"));
		assertTrue(availableValue.contains("White"));
		assertTrue(availableValue.contains("Red"));
		assertTrue(availableValue.contains("Yellow"));
	}
	
	/*
	 * getProperty
	 */
	
	@Test
	@DisplayName("getProperty configuration vide  ")
	public void testgetProperty()
	{
		
		assertThrows(IsnotExistException.class, ()->
		{
			configuration.getProperty("color");
		});
		
	}
	
	@Test
	@DisplayName("getProperty2 configuration contient un exterior ")
	public void testgetProperty2()
	{
		Part exterior=Fabrique.getXs();
		configuration.selectPart(exterior);
		
		assertFalse(configuration.getProperty("color").isEmpty(), "ne doit pas être vide");
		assertTrue(configuration.getProperty("color").get().equals("Red"), "doivent être egale"); 
	
	}

	/*
	 * setProperty
	 */
	@Test
	@DisplayName("setProperty configuration vide ")
	public void testsetProperty()
	{
		
		assertThrows(IsnotExistException.class, ()->
		{
			configuration.setProperty("color", "White");
		});
	
	}
	
	@Test
	@DisplayName("setProperty ")
	public void testsetProperty2()
	{
		Part exterior=Fabrique.getXs();
		configuration.selectPart(exterior);
		
		assertEquals("Red",configuration.getProperty("color").get(), "doivent être egale");

		configuration.setProperty("color", "White");
		
		assertEquals("White",configuration.getProperty("color").get(), "doivent être egale");

	}
	
}
