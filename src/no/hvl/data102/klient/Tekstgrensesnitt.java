package no.hvl.data102.klient;

import static javax.swing.JOptionPane.*;
import no.hvl.data102.adt.*;
import no.hvl.dat102.*;

public class Tekstgrensesnitt {

	// lese opplysningene om en FILM fra tastatur (mangler metode til å kontrolere korrekt input fra bruker)
	public Film lesFilm() {
					
		int filmnr = Integer.parseInt(showInputDialog("Film nr: "));
		String produsent = showInputDialog("Vennligst skriv inn produsent: ");
		String tittel = showInputDialog("Angi tittel: ");
		int lanseringsår = Integer.parseInt(showInputDialog("Fyll ut lanseringsår: "));
		Sjanger sjanger = Sjanger.finnSjanger(showInputDialog("Vennligst velg sjanger: \nAction, Drama, History eller Scifi."));
		String filmselskap = showInputDialog("Angi filmselskap: ");

		Film film = new Film(filmnr, produsent, tittel, lanseringsår, sjanger, filmselskap);
		return film;

	}

	// vise en film med alle opplysninger på skjerm (husk tekst for sjanger)
	public void visFilm(Film film) {

		showMessageDialog(null,
				"Film nr: " + film.getFilmnr() + "\nProdusent: " + film.getProdusent() + "\nTittel: " + film.getTittel()
						+ "\nLanseringsår: " + film.getLanseringsår() + "\nSjanger: " + film.getSjanger().toString()
						+ "\nFilmselskap: " + film.getFilmselskap());

	}

	// Skrive ut alle Filmer med en spesiell delstreng i tittelen
	public void skrivUtFilmDelstrengITittel(FilmarkivADT filma, String delstreng) {
		
		Film[] filmer = filma.soekTittel(delstreng);
		Film film;
		
		for (int i = 0; i < filmer.length; i++) {
			film = filmer[i];
			showMessageDialog(null,"Film nr: " + film.getFilmnr() + ", produsent: " + film.getProdusent() + ", tittel: "
					+ film.getTittel() + ", lanseringsår: " + film.getLanseringsår() + ", sjanger: "
					+ film.getSjanger().toString() + ", filmselskap: " + film.getFilmselskap());
		}

	}

	// Skriver ut alle Filmer av en produsent/ en gruppe
	public void skrivUtFilmProdusent(FilmarkivADT filma, String delstreng) {
		
		Film[] filmer = filma.soekProdusent(delstreng);
		Film film;
		
		for (int i = 0; i < filmer.length; i++) {
			film = filmer[i];
			showMessageDialog(null,"Film nr: " + film.getFilmnr() + ", produsent: " + film.getProdusent() + ", tittel: "
					+ film.getTittel() + ", lanseringsår: " + film.getLanseringsår() + ", sjanger: "
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
	// ... Ev. andre metoder}//class

}
