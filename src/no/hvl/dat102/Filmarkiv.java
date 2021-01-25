package no.hvl.dat102;

import no.hvl.data102.adt.FilmarkivADT;

public class Filmarkiv implements FilmarkivADT {

	private Film[] filmTabell;
	private int antall = 0;

	private void utvidKapasitet() {
		Film[] hjelpetabell = new Film[(int) Math.ceil(1.1 * filmTabell.length)];
		for (int i = 0; i < filmTabell.length; i++) {
			hjelpetabell[i] = filmTabell[i];
		}
		filmTabell = hjelpetabell;
	}

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
	public void leggTilFilm(Film nyFilm) {
		if (antall == filmTabell.length) {
			utvidKapasitet();
		}
		filmTabell[antall]= nyFilm;
		antall++;
	}

	@Override
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
	public int antall() {

		return antall;
	}

}

// public class Filmarkiv implements FilmarkivADT{

//Instansvariable

//Konstruktører og andre metoder
//...fyll ut
//class}
