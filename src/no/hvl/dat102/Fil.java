package no.hvl.dat102;

// import....
import no.hvl.data102.adt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import static javax.swing.JOptionPane.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class Fil {

	// Skriver et Filmarkiv til tekstfil
	// Tekstfilen blir lagret i C:\Users\runar\eclipse-workspace\DAT102\Oving 2

	public static void skrivTilFil(FilmarkivADT filmarkiv, String filnavn) {

		final String FIL = filnavn;
		final String SKILLE = "#";

		try {
			// 1 - FileWriter
			FileWriter skriver = new FileWriter(FIL);

			// 2 - PrintWriter
			PrintWriter utfil = new PrintWriter(skriver);

			// Hvis vi tar imot en tabell av filmer, ville vi her lage en l�kke der
			// vi for hver film henter ut feltvariable og skriver de ut p� samme linje
			Film[] arkiv = filmarkiv.hentFilmTabell();
			Film ARKIV;

			// skriver f�rst antall filmer i arivet
			utfil.println(filmarkiv.antall());
			// 3 - Skriv postene, felt for felt

			for (int i = 0; i < arkiv.length; i++) {
				ARKIV = arkiv[i];

				utfil.print(ARKIV.getFilmnr()); // filnnr +"#"
				utfil.print(SKILLE);

				utfil.print(ARKIV.getProdusent()); // produsent + "#"
				utfil.print(SKILLE);

				utfil.print(ARKIV.getTittel()); // tittel + "#"
				utfil.print(SKILLE);

				utfil.print(ARKIV.getLanserings�r()); // lanserings�r +"#"
				utfil.print(SKILLE);

				utfil.print(ARKIV.getSjanger().toString()); // sjanger +"#"
				utfil.print(SKILLE);

				utfil.print(ARKIV.getFilmselskap()); // filmselskap + linje skift
				utfil.println();

			}
			showMessageDialog(null, "Filen " + FIL + " lagret"); // bekreftelse p� at fil ble lagret
			utfil.close();
			skriver.close();

		} catch (IOException e) {
			System.out.println("Feil ved skriving til fil : " + e);
			System.exit(3);
		}

	}

	// Metode som leser filnavn og returnerer et film arkiv

	public static FilmarkivADT nylesFraFil(String filnavn) {

		FilmarkivADT filma = null;
		final String SKILLE = "#";
		final String FIL = filnavn;

		FileReader arkivFil = null;
		BufferedReader innfil = null;

		try {
			// 1 - Leser fil navn og sjekker om filen eksisterer
			arkivFil = new FileReader(FIL);

		} catch (FileNotFoundException unntak) {
			System.out.print("Finner ikke filen " + FIL);
			System.exit(1);
			
			// Vi kan lage script som kj�rer programmet fra kommandolinjen
			// og fanger opp returkoden ved System.exit
		}

		try {
			// 2 - BufferedReader (leser inn maten i filen)
			innfil = new BufferedReader(arkivFil);

			// 3 - Leser den f�rste posten som er antall info-poster
			String linje = innfil.readLine();
			int n = Integer.parseInt(linje);

			filma = new Filmarkiv(n);

			String post = innfil.readLine();
			for (int i = 0; i < n; i++) {
				String[] felt = post.split(SKILLE);
				// http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#split(java.lang.String,
				// int)
				int filmnr = Integer.parseInt(felt[0]);
				String produsent = felt[1];
				String tittel = felt[2];
				int lanserings�r = Integer.parseInt(felt[3]);
				Sjanger sjanger = Sjanger.finnSjanger(felt[4]);
				String filmselskap = felt[5];

				Film lestfilm = new Film(filmnr, produsent, tittel, lanserings�r, sjanger, filmselskap);
				filma.leggTilFilm(lestfilm);

				post = innfil.readLine();
			}
			// 4 - Lukk filen

			innfil.close();
		} catch (IOException e) {
			System.out.println("Feil ved lesing av fil: " + e);
			System.exit(2);
		}
		return filma;
	}

	// metode som leser antall filmer i et lagret arkiv fra fil
	public static int antall(String filnavn) {

		int antall = 0;
		final String FIL = filnavn;

		FileReader arkivFil = null;
		BufferedReader innfil = null;

		try {
			// 1 - FileReader
			arkivFil = new FileReader(FIL);

		} catch (FileNotFoundException unntak) {
			System.out.println("Finner ikke filen " + FIL);
			System.exit(1);
			
		}

		try {
			// 2 - BufferedReader
			innfil = new BufferedReader(arkivFil);

			// 3 - Leser den f�rste posten som er antall info-poster
			String linje = innfil.readLine();
			antall = Integer.parseInt(linje);
		} catch (IOException e) {
			System.out.println("Feil ved lesing av fil: " + e);
			System.exit(2);
		}
		return antall;
	}
}