package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.CategoryImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;

class TestCategory {
	
	
	@BeforeEach
	void setUp() throws Exception {
		Fabrique.init();
	}

	@DisplayName("Test Get Name")
	@Test
	void TestGetName() {
		
		Category engine = new CategoryImpl("Engine");
		
		assertFalse(engine.getName().isEmpty(), "Le nom de la categorie ne doit pas être vide ");
		assertEquals("Engine", engine.getName(), "Les deux noms doivent être pareils");
	}
	
	@DisplayName("Test Set Name")
	@Test
	void TestSetName() {
		
		CategoryImpl engine = new CategoryImpl("Engine");
		
		assertFalse(engine.getName().isEmpty(), "Le nom de la categorie ne doit pas être vide ");
		assertEquals("Engine", engine.getName(), "Les deux noms doivent être pareils");
		
		engine.setName("Transmission");
		
		assertFalse(engine.getName().equals("Engine"),"Le nom devrait changer");
		assertTrue(engine.getName().equals("Transmission"),"Le nom devrait changer");
	}
	@DisplayName("Test equals ")
	@Test
	void TestEquals() {
		
		Category cat1 = new CategoryImpl("Engine");
		Category cat2 = new CategoryImpl("Engine");
		
		 assertTrue(cat1.equals(cat2));
		assertFalse(cat1.equals(null));
	    assertFalse(cat1.equals(Fabrique.getEd110()));
	}



}
