package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.PartTypeImpl;

class TestPartImpl {


	@DisplayName("Get Price")
	@Test
	void getPrice() {
		
		PartImpl part = (PartImpl) Fabrique.getEg100();
		assertNotNull(part);
		assertNotNull(part.getPrice());
		assertEquals(500000.0, part.getPrice(),"les prix doivent etre pareils");
	}
	
	@DisplayName("Get Price Interior ")
	@Test
	void getPrice2() {
		
		PartImpl part = (PartImpl) Fabrique.getIne();
		assertNotNull(part);
		assertNotNull(part.getPrice());
		assertEquals(20000.0, part.getPrice(),"les prix doivent etre pareils");
	
	
	}
	
	
	
	
	@DisplayName("Get Price exterior 1 ")
	@Test
	void getPrice3() {
		
		PartImpl part = (PartImpl) Fabrique.getXm();
		assertNotNull(part);
		assertNotNull(part.getPrice());
		assertEquals(500000.0, part.getPrice(),"les prix doivent etre pareils");
	}
	@DisplayName("Get Price exterior 2 ")
	@Test
	void getPrice4() {
		
		PartImpl part = (PartImpl) Fabrique.getXs();
		assertNotNull(part);
		assertNotNull(part.getPrice());
		assertEquals(700000.0, part.getPrice(),"les prix doivent etre pareils");
	}
	
	@DisplayName("Get Categorie")
	@Test
	void getCategorie() {
		
		
		PartTypeImpl partTypeImpl = (PartTypeImpl) Fabrique.getEg100().getType() ;
		
		PartImpl part = partTypeImpl.newInstance();
		part.setType(partTypeImpl);
		assertNotNull(part);
		assertNotNull(part.getCategory());
		
		}
	
	@DisplayName("Get Property")
	@Test
	void getProperty() {
		
		PartTypeImpl partTypeImpl = (PartTypeImpl) Fabrique.getXs().getType();
		PartImpl part = partTypeImpl.newInstance(); part.setType(partTypeImpl);
		assertNotNull(part);
		assertFalse(part.getProperty("color").isEmpty(), "ne doit pas être vide");
		assertTrue(part.getProperty("color").get().equals("Red"), "doivent être egale"); 
	}
	
	@DisplayName("Get Property name for Exterior partype : XS")
	@Test
	void GetPropertyNamesForXS() {
		
		//Les exterieurs contiennent la propriété color
		
		PartImpl xs =  (PartImpl) Fabrique.getXs() ;
		
		Set<String> properties = xs.getPropertyNames();
		
		assertNotNull(properties,"Les proprietés ne sont pas vide");
		
		assertTrue(properties.contains("color"),"doit contenir color");
		
		
	}
	
	@DisplayName("Get Property name for Engine partype : Eg100")
	@Test
	void GetPropertyNamesForEngine() {
		
		//Les Engine contiennent les propriété power et carburant
		
		PartImpl eg100 =  (PartImpl) Fabrique.getEg100() ;
		
		Set<String> properties = eg100.getPropertyNames();
		
		assertNotNull(properties,"Les proprietés ne sont pas vides");
		assertEquals(2,properties.size(),"doit contenir 2 propriétés");
		assertTrue(properties.contains("power"),"doit contenir power");
		assertTrue(properties.contains("carburant"),"doit contenir carburant");
	}
	
	@DisplayName("Get Good Property  for engine: Eg100")
	@Test
	void getPropertyEngine() {
		
		//Les Engine contiennent les propriété power et carburant
		// la proriété power de eg100 est initialisé à 100kw
		
		PartImpl eg100 =  (PartImpl) Fabrique.getEg100() ;

		assertTrue(eg100.getProperty("power").isPresent(),"power doit exister");
		assertEquals("100kw",eg100.getProperty("power").get(), "doivent être egale");
	}
	
	@DisplayName("Get Bad Property  for engine: Eg100")
	@Test
	void getPropertyEngine2() {
		
		//Les Engine contiennent les propriété power et carburant
		// la proriété power de eg100 est initialisé à 100kw
		
		PartImpl eg100 =  (PartImpl) Fabrique.getEg100() ;
		assertTrue(eg100.getProperty("dontexist").isEmpty()," doit être vide");
		}
	
	@DisplayName("Set Property for engine Ed110")
	@Test
	public void setProperty1() {
		PartImpl ed110 =  (PartImpl) Fabrique.getEd110() ;
		
		assertEquals("110kw",ed110.getProperty("power").get(), "doivent être egale");

		ed110.setProperty("power", "180kw");
		
		assertEquals("180kw",ed110.getProperty("power").get(), "doivent être egale");

	}
	
	@DisplayName("Set Property for interior IN")
	@Test
	public void setProperty2() {
		PartImpl ine =  (PartImpl) Fabrique.getIne() ;
		assertEquals("standard", ine.getProperty("typeInterior").get(), "doivent être egale");
		ine.setProperty("typeInterior", "High_end");
		assertEquals("High_end",ine.getProperty("typeInterior").get(), "doivent être egale");

	}
	
	@DisplayName(" try to Add property value don't exist in possibleValue for interior IN")
	@Test
	public void setProperty3() {
		PartImpl ine =  (PartImpl) Fabrique.getIne() ;
		assertThrows(IllegalArgumentException.class, ()-> {
			ine.setProperty("typeInterior", "Standart don't exist in Possible value");;
			});
		
	}
	
	@DisplayName(" try to Add property don't exist in possibleValue for interior IN")
	@Test
	public void setProperty() {
		PartImpl ine =  (PartImpl) Fabrique.getIne() ;
		assertThrows(IllegalArgumentException.class, ()-> {
			ine.setProperty("typeDontExist", "standart");;
			});
		
	}
	
	@DisplayName("Get getAvailable Property Values for transmission : With good property ")
	@Test
	public void getAvailablePropertyValues1() {
		
		//Les valeurs possible pour la boite de vitesse transmission sont
		PartImpl tm5 = (PartImpl) Fabrique.getTm5();
		
		Set<String> availableValue = tm5.getAvailablePropertyValues("boite");
		
		assertNotNull(availableValue,"Ne doit pas être nul");
		assertTrue(availableValue.contains("Manual"));
		assertTrue(availableValue.contains("Automatic"));
		assertTrue(availableValue.contains("Sequential"));
		assertTrue(availableValue.contains("Converter"));
		
		Set<String> availableValue2 = tm5.getAvailablePropertyValues("embrayage");
		
		assertNotNull(availableValue2,"Ne doit pas être nul");
		assertTrue(availableValue2.contains("6gears"));
		assertTrue(availableValue2.contains("120kwmax"));
		assertTrue(availableValue2.contains("7gears")); 

	}
	@DisplayName("Get getAvailable Property Values for transmission : With bad property ")
	@Test
	public void getAvailablePropertyValues2() {
		//La transmission ne contient le property "dontexist"
		
		PartImpl tm5 = (PartImpl) Fabrique.getTm5();
		Set<String> availableValue = tm5.getAvailablePropertyValues("dontexist");
		
		assertTrue(availableValue.isEmpty(),"Doit etre vide");
	}
	
	@DisplayName("GetType for engine : eg100")
	@Test
	public void getType() {
		
		PartImpl eg100 =  (PartImpl) Fabrique.getEg100() ;
		assertNotNull(eg100.getType(),"Le type de eg100 ne doit pas etre nul");
		assertEquals("EG100",eg100.getType().getName(),"doit etre egal");
	}
	

}
