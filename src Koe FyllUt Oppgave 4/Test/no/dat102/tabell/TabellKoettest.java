package no.dat102.tabell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat102.adt.KoeADT;
import no.hvl.dat102.adt.KoeADTTest;

public class TabellKoettest extends KoeADTTest {

	@Override
	protected KoeADT<Integer> reset() {
	
		return new TabellKoe<Integer>();
		
	}
	
	
	

}
