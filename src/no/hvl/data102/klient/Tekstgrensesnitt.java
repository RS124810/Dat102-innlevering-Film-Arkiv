package no.hvl.data102.klient;

import static javax.swing.JOptionPane.*;
import no.hvl.data102.adt.*;
import no.hvl.dat102.*;

public class Tekstgrensesnitt {

	// lese opplysningene om en FILM fra tastatur (mangler metode til � kontrolere
	// korrekt input fra bruker)<--- lagt til
	public Film lesFilm() {

		boolean ok = false;
		int filmnr = 0;
		String produsent = null;
		String tittel = null;
		int lanserings�r = 0;
		Sjanger sjanger = null;
		String filmselskap = null;

		while (!ok) {
			try {
				filmnr = Integer.parseInt(showInputDialog("Film nr: "));
				produsent = showInputDialog("Vennligst skriv inn produsent: ");
				tittel = showInputDialog("Angi tittel: ");
				lanserings�r = Integer.parseInt(showInputDialog("Fyll ut lanserings�r: "));
				sjanger = Sjanger
						.finnSjanger(showInputDialog("Vennligst velg sjanger: \nAction, Drama, History eller Scifi."));
				//Kontroll er input en gyldig sjanger
				if (!sjanger.toString().toUpperCase().equals("ACTION")
						&& !sjanger.toString().toUpperCase().equals("DRAMA")
						&& !sjanger.toString().toUpperCase().equals("HISTORY")
						&& !sjanger.toString().toUpperCase().equals("SCIFI")) {
					throw new Exception("sjang");
				}
				filmselskap = showInputDialog("Angi filmselskap: ");
				
				//godkjent input fra bruker
				ok = true;

			} catch (NumberFormatException e) {
				//fanger feil i input filmnr og lanserings�r
				showMessageDialog(null,
						e +"\n\nFeil i filmnr eller lanserings�r, m� skrives med tall\neksempel: 1 eller 1990\n"
								+ "\nPr�v igjen! ");
			} catch (Exception sjang) {
				//fanger ugyldig sjanger
				showMessageDialog(null, sjang+"\nUgjyldig sjanger\n\nGyldig sjangerer er action,drama,history, "
						+ "eller scifi\n\nPr�v igjen!");
			}
		}

		Film film = new Film(filmnr, produsent, tittel, lanserings�r, sjanger, filmselskap);
		return film;

	}

	// vise en film med alle opplysninger p� skjerm (husk tekst for sjanger)
	public void visFilm(Film film) {

		showMessageDialog(null,
				"Film nr: " + film.getFilmnr() + "\nProdusent: " + film.getProdusent() + "\nTittel: " + film.getTittel()
						+ "\nLanserings�r: " + film.getLanserings�r() + "\nSjanger: " + film.getSjanger().toString()
						+ "\nFilmselskap: " + film.getFilmselskap());

	}

	// Skrive ut alle Filmer med en spesiell delstreng i tittelen
	public void skrivUtFilmDelstrengITittel(FilmarkivADT filma, String delstreng) {

		Film[] filmer = filma.soekTittel(delstreng);
		Film film;

		for (int i = 0; i < filmer.length; i++) {
			film = filmer[i];
			showMessageDialog(null,
					"Film nr: " + film.getFilmnr() + ", produsent: " + film.getProdusent() + ", tittel: "
							+ film.getTittel() + ", lanserings�r: " + film.getLanserings�r() + ", sjanger: "
							+ film.getSjanger().toString() + ", filmselskap: " + film.getFilmselskap());
		}

	}

	// Skriver ut alle Filmer av en produsent/ en gruppe
	public void skrivUtFilmProdusent(FilmarkivADT filma, String delstreng) {

		Film[] filmer = filma.soekProdusent(delstreng);
		Film film;

		for (int i = 0; i < filmer.length; i++) {
			film = filmer[i];
			showMessageDialog(null,
					"Film nr: " + film.getFilmnr() + ", produsent: " + film.getProdusent() + ", tittel: "
							+ film.getTittel() + ", lanserings�r: " + film.getLanserings�r() + ", sjanger: "
							+ film.getSjanger().toString() + ", filmselskap: " + film.getFilmselskap());
		}

	}

	// Skrive ut en enkel statistikk som inneholder antall Filmertotalt
	// og hvor mange det er i hver sjanger
	public void skrivUtStatistikk(FilmarkivADT filma) {

		System.out.println("Antall filmer totalt i arkivet " + filma.antall() + "\n");
		System.out.println("Antall filmer i sjanger action " + filma.antall(Sjanger.ACTION));
		System.out.println("Antall filmer i sjanger drama " + filma.antall(Sjanger.DRAMA));
		System.out.println("Antall filmer i sjanger history " + filma.antall(Sjanger.HISTORY));
		System.out.println("Antall filmer i sjanger scifi " + filma.antall(Sjanger.SCIFI));
	}

	public void tom() {
		showMessageDialog(null, "Arkivet har ingen filmer");
	}
	// ... Ev. andre metoder}//class

}
