package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartTypeImpl;

class TestFabrique {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
			
		Fabrique.init();

	}

	
	@Test
	@DisplayName("test init fabrique")
	void test1() {
				
		Fabrique.init();
		
		Fabrique fab =new Fabrique();
		
		assertNotNull(fab,"p ne doit pas etre null");
		
	}

	@Test
	@DisplayName("test 1 get part  ")
	void testPart1() {
		
	Part part=	Fabrique.getEd110();
	PartType type=	part.getType();
	
	assertNotNull(part,"part ne doit pas etre null");
	assertNotNull(type,"partType  ne doit pas etre null");
	assertNotNull(part.getCategory(),"part ne doit pas etre null");
	assertNotNull(type.getCategory(),"part ne doit pas etre null");
	
	assertEquals(part.getCategory(), type.getCategory(),"le part et le partType doivent avoir la meme cat");
	
	}
	
	@Test
	@DisplayName("test que le part eg100 est bien configure  ")
	void testPart2() {
		
	Part part=	Fabrique.getEg100();
	
	assertFalse(part.getPropertyNames().isEmpty(),"eg100 doit contenir des propriete ");
   
	assertTrue(part.getPropertyNames().contains("carburant"));
	assertTrue(part.getPropertyNames().contains("power"));
	
	 
	assertEquals("gazoil", part.getProperty("carburant").get());
	assertEquals("100kw", part.getProperty("power").get());
	
	}
	
	@Test
	@DisplayName("test 2  :  getrequirement de eh120 ")
	void testgetrequirement() {
		
		
	Part p=	Fabrique.getEh120();
	PartTypeImpl type = (PartTypeImpl) p.getType();
	Set<PartType> listeRequi= type.getRequirementsParts();
	
	assertNotNull(p,"eh120 ne doit pas etre null");
	
	assertNotNull(listeRequi,"liste requir de eh120 ne doit pas etre null");
	assertFalse(listeRequi.isEmpty(),"liste requir de eh120 ne doit pas etre vide ");
	assertEquals(listeRequi.size(), 1,"liste requir de eh120 ne doit pas etre egal a 1");
	
	PartType tc120=Fabrique.getTc120().getType();	
	assertTrue(listeRequi.contains(tc120),"liste requir de eh120 doit contenir tc120");
	
	
	
	}
	
	@Test
	@DisplayName("test 3 :  getIncompatibilite  de ta5 ")
	void testgetIncompatibilite() {
		
		Part p=	Fabrique.getTa5();
		PartTypeImpl type = (PartTypeImpl) p.getType();
		
	Set<PartType> listeIncomp=type.getIncompatibitiesParts();
	
	assertNotNull(p,"ta5 ne doit pas etre null");
	
	assertNotNull(listeIncomp,"liste incomp de TA5 ne doit pas etre null");
	assertFalse(listeIncomp.isEmpty(),"liste incomp de TA5 ne doit pas etre vide ");
	assertEquals(listeIncomp.size(), 1,"liste incomp de TA5 ne doit pas etre egal a 1");
	
	PartType eg100=Fabrique.getEg100().getType();	
	assertTrue(listeIncomp.contains(eg100),"liste incomp de eh120 doit contenir eg100");
	
	
	}
	
	@Test
	@DisplayName("test 4 : tester engime ")
	
	void testEngime() {
		
	Category engime=	Fabrique.getEngine();
	
	assertNotNull(engime,"engime ne doit pas etre null");

	}

	@Test
	@DisplayName("test 5 :  get liste category  ")
	void testCategories() {
		
	Set<Category> listecat=Fabrique.getCategories();
	
	assertNotNull(listecat,"listecat ne doit pas etre null");
	assertFalse(listecat.isEmpty()," la liste ne doit pas etre vide");
	
	}
	
	@Test
	@DisplayName("test get part type ")	
	void test2() {
		
	PartType p=	Fabrique.getEd110().getType();
	
	assertNotNull(p,"p ne doit pas etre null");
	
	PartType p4=	Fabrique.getIh().getType();
	
	assertNotNull(p4,"p ne doit pas etre null");
	
	PartType p2=	Fabrique.getXm().getType();
	
	assertNotNull(p2,"p ne doit pas etre null");
	
	PartType p3=	Fabrique.getTs6().getType();
	
	assertNotNull(p3,"p ne doit pas etre null");
	
	}

}
