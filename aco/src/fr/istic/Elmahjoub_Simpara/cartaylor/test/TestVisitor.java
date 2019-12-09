package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.IncompleteConfigurationException;
import fr.istic.Elmahjoub_Simpara.cartaylor.Exception.InvalidConfigurationException;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Part;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.ConfigurationImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;

class TestVisitor {
	private ConfigurationImpl configuration ;

	

	@BeforeEach
	void setUp() throws Exception {
		configuration=new ConfigurationImpl();
	}

	/*
	 * testgetDescriptionHtml
	 */
	
	@Test
	@DisplayName("description d'un configuration  valid mais no complet ")
	public void testgetDescriptionHtml()
	{
		
		Part eg100=Fabrique.getEg100();
		
		configuration.selectPart(eg100);
		
		assertThrows(IncompleteConfigurationException.class, ()->configuration.getDescription());
		
	}
	

	@Test
	@DisplayName("description d'un configuration  complet et non valid")
	public void testgetDescriptionHtml3()
	{
		Part eg100=Fabrique.getEg100();
		Part TA5=Fabrique.getTa5();
		Part xc=Fabrique.getXc();
		Part in=Fabrique.getIne();
		
		configuration.selectPart(eg100);
		configuration.selectPart(TA5);
		configuration.selectPart(xc);
		configuration.selectPart(in);
		
		assertThrows(InvalidConfigurationException.class, ()->configuration.getDescription());
		
	}
		
	@Test
	@DisplayName("config  complet et valid ")
	public void testgetDescriptionHtml5()
	{
		Part eg100=Fabrique.getEg100();
		Part tm5=Fabrique.getTm5();
		Part xc=Fabrique.getXc();
		Part in=Fabrique.getIne();
		
		configuration.selectPart(eg100);
		configuration.selectPart(tm5);
		configuration.selectPart(xc);
		configuration.selectPart(in);
		
		assertDoesNotThrow(()->
		{
			String description=configuration.getDescription();
			
			Logger.getGlobal().log(Level.INFO, description);
		});
		
		
	
	}


}
