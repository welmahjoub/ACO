package fr.istic.Elmahjoub_Simpara.cartaylor.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.Elmahjoub_Simpara.cartaylor.Interface.Observer;
import fr.istic.Elmahjoub_Simpara.cartaylor.api.Configuration;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.ConfigurationImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.ConfiguratorImpl;
import fr.istic.Elmahjoub_Simpara.cartaylor.implementation.Fabrique;

class TestObserver {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@DisplayName("testNotifiy : ")
	public void testNotify()  {
		
		@SuppressWarnings("unchecked")
		Observer<Configuration> obs1 = mock(Observer.class);
		
		@SuppressWarnings("unchecked")
		Observer<Configuration> obs2= mock(Observer.class);
		
		ConfigurationImpl subject=new ConfigurationImpl();;
		 
		subject.attach(obs1);
		subject.attach(obs2);
		
		// modification de la configuration 
		subject.selectPart(Fabrique.getEd110());

		// tester que la methode update du configurator  a ete appele 
		verify(obs1).update(subject);
		verify(obs2).update(subject);

	}
	
	@Test
	public void testGetValue() {
		
		ConfigurationImpl confi=new ConfigurationImpl();
		
		
		assertEquals(confi, confi.getValue());
	}

	@Test
	public void testAttach() {
		
		Observer<Configuration> observer= new ConfiguratorImpl();
		
		ConfigurationImpl config =new ConfigurationImpl();
		
		config.attach(observer);
		assertTrue(config.isAttached(observer));
	}

	@Test
	public void testDetach() {
		
		Observer<Configuration> observer= new ConfiguratorImpl();
		
		ConfigurationImpl config =new ConfigurationImpl();
		
		config.attach(observer);
		config.detach(observer);
		
		assertFalse(config.isAttached(observer));
	}


}
