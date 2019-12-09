package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Configurator;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.ConfigurationImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.ConfiguratorImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;

class TestConfigurator {

	private Configurator configurator;
	

	@BeforeEach
	void setUp() throws Exception {
		
		configurator=new ConfiguratorImpl();
	}

	/*
	 * getCategories
	 */
	
	@Test
	@DisplayName("getCategories ")
	void testgetCategories() {
	
		Set<Category> liste=configurator.getCategories();
		
		assertNotNull(liste,"la liste des cat ne doit pas etre null");
		assertFalse(liste.isEmpty(),"la liste des cat ne doit pas etre vide");
		
		assertEquals(4,liste.size(),"la liste des cat doit etre egal a 4 ");
		
		Category engime=Fabrique.getEngine();
		
		assertTrue(liste.contains(engime),"liste doit contenir engime ");
	}
	
	/*
	 *  getVariants
	 */
	@Test
	@DisplayName("getVariants 1 : engime ")
	void testgetVariants1() {
	
		Category engime=Fabrique.getEngine();
		
		Set<PartType> listeofVar=configurator.getVariants(engime);
		
		assertNotNull(listeofVar,"la liste des engins ne doit pas etre null");
		assertFalse(listeofVar.isEmpty(),"la liste des engins ne doit pas etre vide");
		
		assertEquals(6,listeofVar.size(),"la liste des engins doit etre egal a 6");
		
		PartType eg100=Fabrique.getEg100().getType();
		assertTrue(listeofVar.contains(eg100),"liste doit contenir eg100 ");
		
	}
	
	@Test
	@DisplayName("getVariants 2 : transmission ")
	void testgetVariants2() {
	
		Category trans=Fabrique.getTransmiss();
		
		Set<PartType> listeofVar=configurator.getVariants(trans);
		
		assertNotNull(listeofVar,"l'ensble des transmission ne doit pas etre null");
		assertFalse(listeofVar.isEmpty(),"l'ensble des transmission ne doit pas etre vide");
		
		assertEquals(6,listeofVar.size(),"l'ensble des transmission doit etre egal a 6");
		
		PartType tm5=Fabrique.getTm5().getType();
		assertTrue(listeofVar.contains(tm5),"l'ensble des transmission contenir tm5 ");
		
	}
	
	@Test
	@DisplayName("getVariants 3 : exterior ")
	void testgetVariants3() {
	
		Category ext=Fabrique.getExterior();
		
		Set<PartType> listeofVar=configurator.getVariants(ext);
		
		assertNotNull(listeofVar,"l'ensble des exterior ne doit pas etre null");
		assertFalse(listeofVar.isEmpty(),"l'ensble des exterior ne doit pas etre vide");
		
		assertEquals(3,listeofVar.size(),"l'ensble des exterior doit etre egal a 3");
		
		PartType xc=Fabrique.getXc().getType();
		assertTrue(listeofVar.contains(xc),"l'ensble des exterior doit contenir xc ");
		
	}
	
	@Test
	@DisplayName("getVariants 4 : interior ")
	void testgetVariants4() {
	
		Category inter=Fabrique.getInterior();
		
		Set<PartType> listeofVar=configurator.getVariants(inter);
		
		assertNotNull(listeofVar,"l'ensble des interior ne doit pas etre null");
		assertFalse(listeofVar.isEmpty(),"l'ensble des interior  ne doit pas etre vide");
		
		assertEquals(3,listeofVar.size(),"l'ensble des interior doit etre egal a 3");
		
		PartType ine=Fabrique.getIne().getType();
		assertTrue(listeofVar.contains(ine),"liste doit contenir in ");
		
	}
	
	/*
	 * getCompatibilityChecker
	 */
	
	@Test
	@DisplayName("getCompatibilityChecker ")
	void testgetCompatibilityChecker() {
	
		assertNotNull(configurator.getCompatibilityChecker(),"compa checker ne doit pas etre null");
		
	}

	/*
	 * getConfiguration : design pattern observer
	 */
	
	@Test
	@DisplayName("getConfiguration : test disign observer ")
	void testgetConfiguration() {
	
		ConfiguratorImpl configurator=new ConfiguratorImpl();
		
	    ConfigurationImpl configuration=new ConfigurationImpl();
	    
	   configuration.attach(configurator);
		
		assertNull(configurator.getConfiguration()," la config doit etre null");
		
		// modification de la configuration 
		Part part=Fabrique.getEd110();
		
		configuration.selectPart(part);
		
		assertNotNull(configurator.getConfiguration()," la config ne doit pas  etre null");
		
		// s'assurer que le configurator est mis a jour 
		assertTrue(configurator.getConfiguration().getSelectedParts().contains(part));
		
		
	}
	
	
}
