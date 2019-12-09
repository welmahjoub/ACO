package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.ConflictingRuleException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IsAlreadyExistingException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IsnotExistException;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.CompatibilityManager;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.PartType;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.CompatibilityManagerImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartTypeImpl;

class TestCompatibilityManager {

	private CompatibilityManager manager;

	@BeforeEach
	void setUp() throws Exception {
		
		manager=new CompatibilityManagerImpl();
		Fabrique.init();
	}

	/*
	 *            addImcompatibilities
	 */
	
	@Test
	@DisplayName("addImcompatibilities1 : ajout d'un partType  ")
	void testaddImcompatibilities() throws Exception
	{
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		PartTypeImpl tm5=(PartTypeImpl)Fabrique.getTm5().getType();
		
		Set<PartType> listeofIncomp=new HashSet<>();
		listeofIncomp.add(tm5);
		
		assertTrue(eg100.getIncompatibitiesParts().isEmpty(),"La liste des incompatiblites de eg100 devrait être vide");
		
		manager.addImcompatibilities(eg100, listeofIncomp);
		
		assertFalse(eg100.getIncompatibitiesParts().isEmpty(),"La liste des incompatiblites de eg100 ne devrait plus être vide");
		
		assertTrue(eg100.getIncompatibitiesParts().contains(tm5),"liste doit contenir tm5");
		
		assertEquals(1,eg100.getIncompatibitiesParts().size(),"liste doit etre equal a 1");
		
	}

	@Test
	@DisplayName("addImcompatibilities2 : ajout de plusieurs  partType  ")
	void testaddImcompatibilities2() throws Exception
	{
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		
		PartTypeImpl tm5=(PartTypeImpl)Fabrique.getTm5().getType();
		PartTypeImpl xs=(PartTypeImpl)Fabrique.getXs().getType();
		
		Set<PartType> listeofIncomp=new HashSet<>();
		
		listeofIncomp.add(tm5);
		listeofIncomp.add(xs);
		
		  assertDoesNotThrow(()->{
			  manager.addImcompatibilities(eg100, listeofIncomp);
		  }, "manager ne doit pas lever une exception");
		
		 
		assertTrue(eg100.getIncompatibitiesParts().contains(tm5),"liste doit etre contenir eg133");
		assertTrue(eg100.getIncompatibitiesParts().contains(xs),"liste doit etre contenir eg133");
		
		assertEquals(2,eg100.getIncompatibitiesParts().size(),"liste doit etre equal a 1");
			
		
	}
	
	@Test
	@DisplayName("add Imcompatibilities3 : ajouter un partType deja existant ")
	void testaddImcompatibilities3() throws Exception
	{
		// initialement liste des incompatibilites de ta5 contient eg100   
		
		PartTypeImpl ta5 =(PartTypeImpl)Fabrique.getTa5().getType();
		PartTypeImpl eg100 =(PartTypeImpl)Fabrique.getEg100().getType();
		Set<PartType> listeofIncomp=new HashSet<>();
		listeofIncomp.add(eg100);
		
		// s'assurer que eg100 ne peut pas etre incomp avec ta5
		//car il apartient deja 
		
		assertTrue(ta5.getIncompatibitiesParts().contains(eg100),"eg100 doit appartenir aux incomptibilities de ta5");
		
		 assertThrows(IsAlreadyExistingException.class, ()->
			{manager.addImcompatibilities(ta5, listeofIncomp) ;});
		
	}
		
	
	@Test
	@DisplayName("add Imcompatibilities4 : confluct entre Eg100 et lui meme  ")
	void testaddImcompatibilities4() throws Exception
	{
		
		Part eg100=Fabrique.getEg100();
		
		Set<PartType> listeofIncomp=new HashSet<>();
		
		listeofIncomp.add(eg100.getType());
		
		// s'assurer que eg100 ne peut pas etre incomp avec eg100
		 assertThrows(ConflictingRuleException.class, ()->
			{manager.addImcompatibilities(eg100.getType(), listeofIncomp) ;});
		
	}
	
	@Test
	@DisplayName("add Imcompatibilities5 : confluct meme categorie  ")
	void testaddImcompatibilities5() throws Exception
	{
		
		PartType eg100=Fabrique.getEg100().getType();
		
		PartType eg133=Fabrique.getEg133().getType();
		
		Set<PartType> listeofIncomp=new HashSet<>();
		
		listeofIncomp.add(eg133);
		
		// s'assurer que eg100 ne peut pas etre incomp avec eg133
		//car il sont de meme categorie
		 assertThrows(ConflictingRuleException.class, ()->
			{manager.addImcompatibilities(eg100, listeofIncomp) ;});
		
	}
	
	@Test
	@DisplayName("add Imcompatibilities5 6 : ajout des partypes requirements")
	void testaddImcompatibilities6() throws Exception
	{
		//initialement  eh120 requis  tc120 
		// donc ils peuvent pas etre incompatbile
		
		PartTypeImpl eh120=(PartTypeImpl)Fabrique.getEh120().getType();
		PartTypeImpl tc120=(PartTypeImpl)Fabrique.getTc120().getType();
		
		
		assertTrue(eh120.getIncompatibitiesParts().isEmpty(),"L'ensble des incompatibilite de eh120  doit etre vide  ");

		Set<PartType> liste=new HashSet<>();
		
		liste.add(eh120);
		
		assertThrows(ConflictingRuleException.class, ()->
		{manager.addImcompatibilities(tc120, liste) ;});
		
		assertTrue(eh120.getIncompatibitiesParts().isEmpty(),"L'ensble des incompatibilite de eh120  doit etre vide  ");

	}
	
	/*
	 *            addRequirements
	 */
	
	@Test
	@DisplayName("addRequirements 1 : ajoute des requirements ")
	void testaddRequirements()
	{
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		PartTypeImpl eg133=(PartTypeImpl)Fabrique.getEg133().getType();
		
		Set<PartType> liste=new HashSet<>();
		
		liste.add(eg133);
		
		assertThrows(ConflictingRuleException.class, ()->
		{manager.addRequirements(eg100, liste) ;});
		
		 
	}
	@Test
	@DisplayName("addRequirements 2 : ajout de plusieurs  partType  ")
	void testaddRequirements2() throws Exception
	{
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		PartTypeImpl tm5 = (PartTypeImpl)Fabrique.getTm5().getType();
		PartTypeImpl ine = (PartTypeImpl)Fabrique.getIne().getType();
		
		Set<PartType> liste=new HashSet<>();
		
		liste.add(tm5);
		liste.add(ine);
		
		assertTrue(eg100.getRequirementsParts().isEmpty(),"L'ensble des requirements de eg100 doit etre vide");
		  assertDoesNotThrow(()->{
			  manager.addRequirements(eg100, liste);
		  }, "manager ne doit pas lever une exception");
		
		assertFalse(eg100.getRequirementsParts().isEmpty(),"L'ensble des requirements de eg100 ne doit plus etre vide");
		assertTrue(eg100.getRequirementsParts().contains(tm5),"liste doit etre contenir eg133");
		assertTrue(eg100.getRequirementsParts().contains(ine),"liste doit etre contenir eg133");
		assertEquals(2,eg100.getRequirementsParts().size(),"liste doit etre equal a 1");
			
		
	}
	
	@Test
	@DisplayName("addRequirements 3 : eh120 : ajout d'un partType deja existant")
	void testaddRequirements3()
	{
		PartTypeImpl eh120=(PartTypeImpl)Fabrique.getEh120().getType();
		PartTypeImpl TC120=(PartTypeImpl)Fabrique.getTc120().getType();
		
		Set<PartType> liste=new HashSet<>();
		
		liste.add(TC120);
		
		assertEquals(eh120.getRequirementsParts().size(), 1,"liste doit etre equal a 1");
		assertTrue(eh120.getRequirementsParts().contains(TC120),"eh120 contient tc120 initialement");
		
		  assertThrows(IsAlreadyExistingException.class, ()->
			{manager.addRequirements(eh120, liste) ;});
		
			
	}
	
	@Test
	@DisplayName(" addRequirements4 : confluct entre Eg100 et lui meme  ")
	void testaddRequirements4() throws Exception
	{
		
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		
		Set<PartType> liste=new HashSet<>();
		
		liste.add(eg100);
		
		// s'assurer que eg100 ne peut pas etre incomp avec eg100
		 assertThrows(ConflictingRuleException.class, ()->
			{manager.addRequirements(eg100, liste) ;});
		
	}
	
	@Test
	@DisplayName(" add Requirements5 : confluct meme categorie  ")
	void testaddRequirements5() throws Exception
	{
		
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		
		PartTypeImpl eg133=(PartTypeImpl)Fabrique.getEg133().getType();
		
		Set<PartType> liste=new HashSet<>();
		
		liste.add(eg133);
		
		// s'assurer que eg100 ne peut pas etre requise avec eg133
		//car il sonte de meme categorie
		
		 assertThrows(ConflictingRuleException.class, ()->
			{manager.addRequirements(eg100, liste) ;});
		
	}
	
	@Test
	@DisplayName("addRequirements 6 : ajout des partypes incompatibles")
	void testaddRequirements6() throws Exception
	{
		//initialement is et xs sont imcompatible avec eg100
		// donc ils peuvent pas etre requirement
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		PartTypeImpl is=(PartTypeImpl)Fabrique.getIs().getType();
		PartTypeImpl xs=(PartTypeImpl)Fabrique.getXs().getType();
		
		Set<PartType> liste=new HashSet<>();
		
		liste.add(is);
		liste.add(xs);
		
		assertTrue(eg100.getRequirementsParts().isEmpty(),"L'ensble des requirements de eg100 doit etre vide");
		
		assertThrows(ConflictingRuleException.class, ()->
		{manager.addRequirements(eg100, liste) ;});
		
		assertTrue(eg100.getRequirementsParts().isEmpty(),"L'ensble des requirements de eg100  doit etre vide");

	}
	
	/*
	 * removeIncompatibility
	 */
	
	@Test
	@DisplayName("removeIncompatibility 1 : ")
	void testremoveIncompatibility()
	{
		PartTypeImpl TA5=(PartTypeImpl)Fabrique.getTa5().getType();
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		
		  assertDoesNotThrow(()->{
			  manager.removeIncompatibility(TA5, eg100);
		  }, "manager ne doit pas lever une exception");
		
		 
		assertFalse(TA5.getIncompatibitiesParts().contains(eg100),"liste doit etre contenir eg133");
		
		assertEquals(0,TA5.getIncompatibitiesParts().size(),"liste doit etre equal a 1");
		
	}
	
	@Test
	@DisplayName("removeIncompatibility 2 : remove a inexiste imcomp ")
	void testremoveIncompatibility2()
	{
		PartTypeImpl TA5=(PartTypeImpl)Fabrique.getTa5().getType();
		PartTypeImpl ed180=(PartTypeImpl)Fabrique.getEd180().getType();
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		
		
		assertFalse(TA5.getIncompatibitiesParts().contains(ed180),"liste doit etre contenir ed180");
	    assertThrows(IsnotExistException.class, ()->
		{manager.removeIncompatibility(TA5, ed180) ;});
		
		assertTrue(TA5.getIncompatibitiesParts().contains(eg100),"liste doit etre contenir eg133");
		
		assertEquals(1,TA5.getIncompatibitiesParts().size(),"liste doit etre equal a 1");
		
	}
	
	/*
	 * 			remove Requirements
	 */
	@Test
	@DisplayName("remove Requirements 1 : ")
	void testremoveRequirements1()
	{
		PartTypeImpl EH120=(PartTypeImpl)Fabrique.getEh120().getType();
		PartTypeImpl tc120=(PartTypeImpl)Fabrique.getTc120().getType();
		
		assertTrue(EH120.getRequirementsParts().contains(tc120),"liste doit  contenir tc120");
		
		assertEquals(1,EH120.getRequirementsParts().size(),"liste doit etre equal a 1");
		
		assertDoesNotThrow(()->{
			  manager.removeRequirement(EH120, tc120);
		  }, "manager ne doit pas lever une exception");
		
		  
			
		assertFalse(EH120.getRequirementsParts().contains(tc120),"liste ne doit plus contenir tc120");
		
		assertEquals(0,EH120.getRequirementsParts().size(),"liste doit etre equal a 0");
		
	}
	
	@Test
	@DisplayName("remove Requirements 2 : IsnotExistException ")
	void testremoveRequirements2()
	{
		// supprimer un imcompa qui n existe pas 
		
		PartTypeImpl EH120=(PartTypeImpl)Fabrique.getEh120().getType();
		PartTypeImpl eg100=(PartTypeImpl)Fabrique.getEg100().getType();
		
		assertFalse(EH120.getRequirementsParts().contains(eg100),"Ne doit pas contenir eg100");
	
	    assertThrows(IsnotExistException.class, ()->
		{manager.removeRequirement(EH120, eg100) ;});
		
		assertEquals(EH120.getRequirementsParts().size(), 1,"liste doit etre equal a 1");
		
	}
	
	/*
	 * getIncompatibilities
	 */
	
	@Test
	@DisplayName("getIncompatibilities")
	void testgetIncompatibilities()
	{
		PartType ta5=Fabrique.getTa5().getType();

		Set<PartType> liste=manager.getIncompatibilities(ta5);
		
		assertTrue(liste.contains(Fabrique.getEg100().getType()));
		assertEquals(1,liste.size());
	
		
	}
	
	/*
	 * getRequirements
	 */
	
	@Test
	@DisplayName("getRequirements")
	void testgetRequirements()
	{
		PartType eh120=Fabrique.getEh120().getType();

		Set<PartType> liste=manager.getRequirements(eh120);
		
		assertTrue(liste.contains(Fabrique.getTc120().getType()));
		assertEquals(1,liste.size());
	}
	
	
	
}
