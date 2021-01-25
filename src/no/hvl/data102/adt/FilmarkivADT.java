package no.hvl.data102.adt;

import no.hvl.dat102.*;

public interface FilmarkivADT {

	// Returnere en tabell av Filmer
	Film[] hentFilmTabell();

	// Legger til en ny Film
	void leggTilFilm(Film nyFilm);

	// Sletter en Filmhvis den fins
	boolean slettFilm(int filmnr);

	// Søker og henter Filmermed en gitt delstreng
	Film[] soekTittel(String delstreng);

	// Søker og henter produsenter med en gitt delstreng
	Film[] soekProdusent(String delstreng);

	// Henter antall Filmerfor en gitt sjanger
	int antall(Sjanger sjanger);

	// Returnerer antall Filmer
	int antall();
}

//interface 
