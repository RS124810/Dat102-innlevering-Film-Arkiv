package no.dat102.kjedet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat102.adt.KoeADT;
import no.hvl.dat102.adt.KoeADTTest;
import no.hvl.dat102.kjedet.KjedetKoe;

public class KjedetKoeTest extends KoeADTTest {
	
	@Override
	protected KoeADT<Integer> reset() {
		
		return new KjedetKoe<Integer>();
	}

}
