/**
 * 
 */
package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.ConflictingRuleException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IsAlreadyExistingException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Visitor;
import fr.istic.Elmahjoub_Simpara.cartaylor.Model.Engine.EG100;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.CategoryImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartTypeImpl;

/**
 * @author Simpara
 *
 */
class TestPartType {

	
	@BeforeEach
	void setUp() throws Exception {
		Fabrique.init();
	}
	
	
	
	@DisplayName("New Intance")
	@Test
	void newInstance() {
		
		// cree une classe qui herite de partImpl et qui n 'a pas de constructeur 
		// par default 
		// donc new Instance doit lever une exception
		
		class testInstance extends  PartImpl{
			Double price;
			
			testInstance(Double price){
				this.price =price;
			}
			
			@Override
			public Double getPrice() {
				// TODO Auto-generated method stub
				return price;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void accept(Visitor v) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		assertNotNull(new testInstance(1.1).getPrice());
		assertNull(new testInstance(21d).getDescription());
		
		
		PartTypeImpl partType = new PartTypeImpl("test", testInstance.class, Fabrique.getEngine());
		assertNull(partType.newInstance());
		
		
		}

	
	@DisplayName("Get incompatibilities")
	@Test
	void testGetIncompatibitiesParts() {
		
		PartTypeImpl tsf7 =(PartTypeImpl) Fabrique.getTsf7().getType();
		
		Set<PartType> incompatibities = tsf7.getIncompatibitiesParts();
		
		assertNotNull(incompatibities,"La liste des incompatibits ne doit pas tre vide");
		assertFalse(incompatibities.isEmpty());
		assertEquals(3, incompatibities.size());
		
		assertTrue(incompatibities.contains(Fabrique.getEg100().getType()));
		assertTrue(incompatibities.contains(Fabrique.getEg133().getType()));
		assertTrue(incompatibities.contains(Fabrique.getEd110().getType()));
		
	}


	@Test
	void testAddIncompatibiltiesParts1() {
		
		PartTypeImpl part = (PartTypeImpl)Fabrique.getEg100().getType();
		 
		 assertTrue(part.getIncompatibitiesParts().isEmpty());
		 assertEquals(0, part.getIncompatibitiesParts().size());
		 
		assertTrue(part.addIncompatibiltiesParts(Fabrique.getEd110().getType()));
		assertTrue(part.addIncompatibiltiesParts(Fabrique.getEh120().getType()));
		
		assertThrows(NullPointerException.class, ()-> {part.addIncompatibiltiesParts(null);});
		
	
		 assertFalse(part.getIncompatibitiesParts().isEmpty());
		
		 assertEquals(2, part.getIncompatibitiesParts().size());
		 
		assertTrue(part.getIncompatibitiesParts().contains(Fabrique.getEd110().getType()),"Les incompatibilts doivent contenir Ed110") ;
		assertTrue(part.getIncompatibitiesParts().contains(Fabrique.getEh120().getType()),"Les incompatibilts doivent contenir Eh120") ;
		 
	}
	

	
	@DisplayName("Remove Incompatibilities")
	@Test
	void testRemoveIncompatibiltiesParts() {
		
		PartTypeImpl partIs = (PartTypeImpl)Fabrique.getIs().getType();
		
		/*IS contient comme incompatibilits : Eg100 et Tm5 initialement*/
		
		assertThrows(NullPointerException.class, ()-> {partIs.removeIncompatibiltiesParts(null);});
		assertTrue( partIs.removeIncompatibiltiesParts(Fabrique.getEg100().getType()));
		assertFalse(partIs.removeIncompatibiltiesParts(Fabrique.getEd110().getType()));
		
		
	}

	@DisplayName("Get RequirementsParts 1")
	@Test
	void testGetRequirementsParts() {
		
		PartTypeImpl eh120 =(PartTypeImpl)Fabrique.getEh120().getType();
		
		Set<PartType> requirs= eh120.getRequirementsParts();
		
		assertNotNull(requirs,"Le requirs ne doit pas tre vide");
		assertFalse(requirs.isEmpty(), "le requirs ne doit pas tre vide");
		assertEquals(1, requirs.size(),"Le size devrait tre  egal  1");
		assertTrue(requirs.contains(Fabrique.getTc120().getType()), "Les requirements doivent contenir tc120");
		assertFalse(requirs.contains(Fabrique.getEd110().getType()),"Ne devrait pas contenir ed120");
		
	}

	
	@Test
	void testAddRequirementsParts() {
		PartTypeImpl part = (PartTypeImpl)Fabrique.getEg100().getType();
		
		 
		 assertTrue(part.getRequirementsParts().isEmpty());
		 assertTrue(part.getRequirementsParts().size()==0);
		 
		assertTrue(part.addRequirementsParts(Fabrique.getEd110().getType()));
		assertTrue(part.addRequirementsParts(Fabrique.getEh120().getType()));
		
		assertThrows(NullPointerException.class, ()-> {part.addIncompatibiltiesParts(null);});
		
	
		 assertFalse(part.getRequirementsParts().isEmpty());
		 assertTrue(part.getRequirementsParts().size()==2);
		 
		assertTrue(part.getRequirementsParts().contains(Fabrique.getEd110().getType()),"L'ensemble des  requirements doit contenir Ed110") ;
		assertTrue(part.getRequirementsParts().contains(Fabrique.getEh120().getType()),"L'ensemble des  requirements doit contenir Eh120") ;
		 
	}

	
	@DisplayName("Remove requirement")
	@Test
	void testRemoveRequirementsParts() {
		
		PartTypeImpl eg100 =(PartTypeImpl)Fabrique.getEg100().getType();
		
		assertTrue(eg100.addRequirementsParts(Fabrique.getTm5().getType()),"Devrait tre ajout");
		assertTrue(eg100.addRequirementsParts(Fabrique.getXc().getType()),"Devrait tre ajout");
		
		//Suppression de eg100 dans la liste des requirements
		assertTrue(eg100.removeRequirementsParts(Fabrique.getTm5().getType()));
		assertFalse(eg100.getRequirementsParts().contains(Fabrique.getTm5().getType()), "Ne devrait plus contenir Tm5");
		assertTrue(eg100.getRequirementsParts().size()==1, "La taille devrait tre egale  1");
		
		//Suppression de eg133 dans la liste des requirements
		assertTrue(eg100.removeRequirementsParts(Fabrique.getXc().getType()));
		assertFalse(eg100.getRequirementsParts().contains(Fabrique.getXc().getType()), "Ne devrait plus contenir eg100");
		assertTrue(eg100.getRequirementsParts().size()==0, "La taille devrait tre egale  0");
		
	}

	@DisplayName("Get Name")
	@Test
	void testGetNamePart() {
		
		
		PartTypeImpl partype = new PartTypeImpl("EG100",EG100.class, Fabrique.getEngine());
		
		assertNotNull(partype.getName(), "Le nom ne drvait pas tre null");
		assertTrue(!partype.getName().isEmpty(),"Le nom du partType ne devrait pas tre vide");
		assertEquals("EG100", partype.getName(),"Les deux noms devrait tre pareil");
	
	}

	@DisplayName("Set Name")
	@Test
	void testSetNamePart() {
		
		PartTypeImpl partype = new PartTypeImpl("EG100",EG100.class, Fabrique.getEngine());
		
		assertNotNull(partype.getName(), "Le nom ne drvait pas tre null");
		assertTrue(!partype.getName().isEmpty(),"Le nom du partType ne devrait pas tre vide");
		assertEquals("EG100", partype.getName(),"Les deux noms devrait tre pareil");
		
		partype.setName("EG133");
		assertNotNull(partype.getName(), "Le nom ne drvait pas tre null");
		assertFalse(partype.getName().isEmpty(), "Le nom ne drvait pas tre null");
		assertEquals("EG133", partype.getName(), "Les noms doivent tre les meme");
	}
	
	
	
	@DisplayName("Get Categorie")
	@Test
	void testGetCategoryPart() {
		
		Category engine = new CategoryImpl("Engine");
		
		PartTypeImpl partype = new PartTypeImpl("EG100",EG100.class, Fabrique.getEngine());
		
		assertNotNull(partype.getCategory(), "La categorie ne devrait pas tre null");
		assertEquals(engine, partype.getCategory(),"Les deux categories devrait tre egales");
	}

	
	@DisplayName("Set Categorie")
	@Test
	void testSetCategoryPart() {
		
		Category engine = new CategoryImpl("Engine");
		
		Category transmission = new CategoryImpl("Transmission");
		
		PartTypeImpl partype = new PartTypeImpl("EG100",EG100.class, engine);
		
		partype.setCategory(transmission);
		
		assertNotNull(partype.getCategory(), "La categorie ne devrait pas tre null");
		
		assertTrue(partype.getCategory().getName().equals("Transmission"), "Le nom doit tre Transmission");
		assertEquals(transmission, partype.getCategory(),"Les deux categories devrait tre egales");
	}
	
	@DisplayName("newInstance")
	@Test
	void testnewInstance() {
		
		Category engine = new CategoryImpl("Engine");
		
		
		PartTypeImpl partype = new PartTypeImpl("EG100",EG100.class, engine);
		
		PartImpl part=partype.newInstance();
		part.setType(partype);

		assertNotNull(part,"part ne doit pas etre null");
		assertNotNull(part.getCategory(),"part ne doit pas etre null");
		assertEquals(part.getCategory().getName(),engine.getName());

		
	}
	
	@AfterAll
	 static void resetFabrique() throws ConflictingRuleException, IsAlreadyExistingException {
		Fabrique.init();
	}

}
