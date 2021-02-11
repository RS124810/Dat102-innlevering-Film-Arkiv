package no.hvl.dat102.kjedetstruktur;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;
import no.hvl.data102.adt.FilmarkivADT;

public class Filmarkiv2 implements FilmarkivADT {

	private LinearNode<Film> start;
	private int antall;
	// OBS! Ingen referanse til siste, kun start

	/*
	 * Klassen skal hade samme operasjoner som for Filmarkiv i øving 2, men pass på
	 * at implementeringen av alle metoder blir tilpasset den nyestrukturen.
	 */

	public Filmarkiv2() {

	}

	// legger alle element fra nodene over i en tabell og returnere denne
	@Override
	public Film[] hentFilmTabell() {
		Film[] filmtabell = new Film[antall];

		LinearNode<Film> temp = new LinearNode<>();
		temp = start;
		for (int i = 0; i < antall; i++) {
			filmtabell[i] = temp.getElement();
			if (temp.getElement() != null) {
				temp = temp.getNeste();
			}
		}
		return filmtabell;
	}

	// Legger til en ny node med et film objekt.
	@Override
	public void leggTilFilm(Film nyFilm) {
		LinearNode<Film> film = new LinearNode<>(nyFilm);
		if (antall == 0) {
			start = film;
			antall++;
		} else {
			film.setNeste(start);
			start = film;
			antall++;
		}

	}

	@Override
	public boolean slettFilm(int filmnr) {

		// Sletter første node og avslutter metoden med return
		if (start.getElement().getFilmnr() == filmnr) {
			start = start.getNeste();
			antall--;
			return true;
		}
		// teller gjennom nodene og setter temp til noden med objektet som inneholder
		// film nr som matcher parameter filmnr

		LinearNode<Film> temp = new LinearNode<>();
		int teller = 1;
		// søker igjennom nodene etter treff på filmnr eller til de ikke er flere noder
		while (temp.getNeste() != null && temp.getNeste().getElement().getFilmnr() != filmnr) {
			temp = temp.getNeste();
			teller++;
		}

		if (teller < antall - 1) {

			temp.setNeste(temp.getNeste().getNeste());
			// om vi har ... ->[1]->[2]->[3]->... gir koden over at [1]->[3], [2] blir
			// slettet
			antall--;
			return true;
		}
		// Sletter siste node
		else if (teller == antall - 1) {
			temp.setNeste(null);
			antall--;
			return true;
		}
		// Om ingen matcher
		return false;
	}

	@Override
	// søk i nodene med en delstreng mot titteler

	public Film[] soekTittel(String delstreng) {

		Filmarkiv2 tittler = new Filmarkiv2();
		LinearNode<Film> temp = new LinearNode<>();
		temp = start;

		for (int i = 0; i < antall; i++) {
			// legger til treff på søket mot produsent
			if (temp.getElement().getTittel().contains(delstreng)) {
				tittler.leggTilFilm(temp.getElement());
			}
			// henter neste node vis den eksisterer
			if (temp.getNeste() != null) {
				temp = temp.getNeste();
			}
		}
		return tittler.hentFilmTabell();
	}

	@Override
	// søk i nodene med en delstreng mot produsent

	public Film[] soekProdusent(String delstreng) {

		Filmarkiv2 produsent = new Filmarkiv2();
		LinearNode<Film> temp = new LinearNode<>();
		temp = start;

		for (int i = 0; i < antall; i++) {
			// legger til treff på søket mot produsent
			if (temp.getElement().getTittel().contains(delstreng)) {
				produsent.leggTilFilm(temp.getElement());
			}
			// henter neste node vis den eksisterer
			if (temp.getNeste() != null) {
				temp = temp.getNeste();
			}
		}
		return produsent.hentFilmTabell();
	}

	@Override
	// søk i nodene etter antall i en sjanger

	public int antall(Sjanger sjanger) {

		int antallSjanger = 0;
		LinearNode<Film> temp = new LinearNode<>();
		temp = start;

		for (int i = 0; i < antall; i++) {
			// legger til treff på søket mot sjanger
			if (temp.getElement().getSjanger().equals(sjanger)) {
				antallSjanger++;
			}
			// henter neste node vis den eksisterer
			if (temp.getNeste() != null) {
				temp = temp.getNeste();
			}
		}

		return antallSjanger;
	}

	@Override
	public int antall() {

		return antall;
	}

}
