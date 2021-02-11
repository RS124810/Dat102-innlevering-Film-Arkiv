package no.hvl.dat102;

import no.hvl.data102.adt.FilmarkivADT;

public class Filmarkiv implements FilmarkivADT {

	private Film[] filmTabell;
	private int antall = 0;
	
	// øker tabell str.
	
	private void utvidKapasitet() {
		Film[] hjelpetabell = new Film[(int) Math.ceil(1.1 * filmTabell.length)];

		for (int i = 0; i < filmTabell.length; i++) {
			hjelpetabell[i] = filmTabell[i];
		}
		// bugfix, dersom en sletter alle filmer i arkivet og så prøver å legge til i et tomt arkiv Film[(Math.ceil(1.1 * 0) = 0]
		if (filmTabell.length == 0) {
			hjelpetabell = new Film[1];
		}
		filmTabell = hjelpetabell;

	}
	
	// fjerner tomme plasser i tabellen
	
	private Film[] trimTab(Film[] tab, int n) {
		// n er antall elementer
		Film[] filmtab2 = new Film[n];
		int i = 0;
		while (i < n) {
			filmtab2[i] = tab[i];
			i++;
		}
		// while
		return filmtab2;
	}//

	public Filmarkiv(int antall) {
		filmTabell = new Film[antall];
	}

	@Override
	public Film[] hentFilmTabell() {
		return getFilmTabell();
	}

	@Override
	// legger til en ny film i tabellen
	
	public void leggTilFilm(Film nyFilm) {
		if (antall == filmTabell.length) {
			utvidKapasitet();
		}
		filmTabell[antall] = nyFilm;
		antall++;
	}

	@Override
	// sletter en film fra tabellen
	
	public boolean slettFilm(int filmnr) {
		for (int i = 0; i < filmTabell.length; i++) {
			if (filmnr == filmTabell[i].getFilmnr()) {
				filmTabell[i] = filmTabell[antall - 1];
				filmTabell[antall - 1] = null;
				filmTabell = trimTab(filmTabell, antall - 1);
				antall--;
				return true;
			}
		}
		return false;
	}

	public Film[] getFilmTabell() {
		return filmTabell;
	}

	public void setFilmTabell(Film[] filmTabell) {
		this.filmTabell = filmTabell;
	}

	public int getAntall() {
		return antall;
	}

	public void setAntall(int antall) {
		this.antall = antall;
	}

	@Override
	// søk i tabellen med delstreng mot tittel
	
	public Film[] soekTittel(String delstreng) {
		int antallTittler = 0;
		Film[] filmTittel = new Film[antall];
		for (int i = 0; i < filmTabell.length; i++) {
			if (filmTabell[i].getTittel().contains(delstreng)) {
				filmTittel[antallTittler] = filmTabell[i];
				antallTittler++;
			}
		}

		return trimTab(filmTittel, antallTittler);
	}

	@Override
	// søk i tabellen med delstreng mot produsent
	
	public Film[] soekProdusent(String delstreng) {
		int antallProd = 0;
		Film[] filmProd = new Film[antall];
		for (int i = 0; i < filmTabell.length; i++) {
			if (filmTabell[i].getProdusent().contains(delstreng)) {
				filmProd[antallProd] = filmTabell[i];
				antallProd++;
			}
		}
		return trimTab(filmProd, antallProd);
	}

	@Override
	//finner antall av filmer i en sjanger
	
	public int antall(Sjanger sjanger) {
		int antallSjanger = 0;

		for (int i = 0; i < filmTabell.length; i++) {
			if (filmTabell[i].getSjanger().equals(sjanger)) {
				antallSjanger++;
			}
		}
		return antallSjanger;
	}

	@Override
	// antall filmer i arkivet
	
	public int antall() {

		return antall;
	}

}

// public class Filmarkiv implements FilmarkivADT{

//Instansvariable

//Konstruktører og andre metoder
//...fyll ut
//class}
