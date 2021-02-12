package no.hvl.dat102.adt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.exception.EmptyCollectionException;

public abstract class KoeADTTest {

	private KoeADT<Integer> Koe;

	private Integer e0 = 1;
	private Integer e1 = 2;
	private Integer e2 = 3;
	private Integer e3 = 4;
	private Integer e4 = 5;
	
	protected abstract KoeADT<Integer> reset();
	
	@BeforeEach
	public void setup() {
		Koe = reset();
	}	
	@Test
	public void nyErTom() {
		assertTrue(Koe.erTom());
	}
	@Test
	public void testInnKoe() {
		
		Koe.innKoe(e0);
		assertEquals (1, Koe.antall());
		
		Koe.innKoe(e1);
		assertEquals (2, Koe.antall());
		
		Koe.innKoe(e2);
		assertEquals (3, Koe.antall());
		
		assertFalse(Koe.erTom());
	}
	@Test
	public void testUtKoeOgSlett() {
		Koe.innKoe(e0);
		Koe.innKoe(e1);
		Koe.innKoe(e2);
		Koe.innKoe(e3);
		
		assertEquals(e0,Koe.utKoe());
		assertEquals (3, Koe.antall());
		assertEquals(e1,Koe.utKoe());
		assertEquals (2, Koe.antall());
		assertEquals(e2,Koe.utKoe());
		assertEquals (1, Koe.antall());
		assertEquals(e3,Koe.utKoe());
		assertEquals (0, Koe.antall());
		assertTrue(Koe.erTom());
		
	}
	@Test
	public void testForste() {
		Koe.innKoe(e2);
		Koe.utKoe();
		Koe.innKoe(e3);
		Koe.innKoe(e4);
		Koe.innKoe(e0);
		Koe.utKoe();
				
		assertEquals (e4, Koe.foerste());
		
		
	}
	@Test
	public void testAntall() {
		Koe.innKoe(e0);
		Koe.innKoe(e1);
		Koe.innKoe(e2);
		Koe.innKoe(e3);
		
		Koe.utKoe();
		
		assertEquals (3, Koe.antall());
		
		
	}
	@Test
	public void InnOgUtMedDuplikater() {

	Koe.innKoe(e0);
	Koe.innKoe(e1);
	Koe.innKoe(e1);
	Koe.innKoe(e2);

	assertEquals(e0, Koe.utKoe());
	assertEquals(e1, Koe.utKoe());
	assertEquals(e1, Koe.utKoe());
	assertEquals(e2, Koe.utKoe());
	}
	
	//Test toString
	
	@Test
	public void SlettFromEmptyIsUnderflowed() {
		

		Assertions.assertThrows(EmptyCollectionException.class, () -> {
			
			Koe.utKoe();
			
			//test som sjekker om pop på en tom stabel kaster untakket "EmptyCollectionException"
		});
	}

}
