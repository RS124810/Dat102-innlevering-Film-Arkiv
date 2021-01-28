package no.hvl.data102.klient;

import static javax.swing.JOptionPane.*;
import javax.swing.JOptionPane;
import no.hvl.dat102.*;
import no.hvl.data102.adt.FilmarkivADT;

import javax.swing.ImageIcon;

public class Meny {

	public void Start() {

		boolean meny1 = true;
		String[] valg = { "Lage et nytt arkiv?", "Eksisterende arkiv", "Avslutt" };
		// bildefil til menyen
		ImageIcon ikon = new ImageIcon(
				"C:\\Users\\runar\\eclipse-workspace\\DAT102\\Oving 2\\src\\no\\hvl\\dat102\\arkivbilde.png");

		while (meny1) {
			int svar = JOptionPane.showOptionDialog(null,
					// tekst
					"               Velkommen til Film Arkiv v1.1.2\n\n               Hva ønsker du å jobbe med?\n\n",
					// tittel
					"Film Arkiv v1.1.2",
					// knapper
					JOptionPane.YES_NO_CANCEL_OPTION,
					// message Type
					JOptionPane.INFORMATION_MESSAGE,
					// ikon eller bilde, set til null om lager problem
					ikon,
					// Valg (retur verdier: nytt arkiv = 0, eksisterende arkiv = 1, avslutt = 2; X =
					// -1)
					valg,
					// start verdi
					0);

			// burde sikkert brukt switch setninger i menyen istede for if setninger....
			// nytt arkiv
			if (svar == 0) {
				String Arkivnavn = showInputDialog("Skriv inn navn til det nye arkivet"); // Filnavn
				int antall = Integer.parseInt(showInputDialog("Hvor mange filmer skal du legge til i arkivet?"));

				FilmarkivADT arkiv = new Filmarkiv(antall);
				Tekstgrensesnitt film = new Tekstgrensesnitt();

				for (int i = 0; i < antall; i++) {
					Film nyFilm = new Film();
					nyFilm = film.lesFilm();
					arkiv.leggTilFilm(nyFilm);
					film.visFilm(nyFilm);
				}

				// lager filnavn til lagring
				String filnavn = Arkivnavn + ".txt";

				// lagrer arkiv til fil "filnavn"
				Fil.skrivTilFil(arkiv, filnavn);

				// jobbe med eksisterende arkiv
			} else if (svar == 1) {

				// knapper og menybeskjeder
				String Arkivnavn = showInputDialog("Skriv inn navn til arkivet du vil jobbe med");
				String filnavn = Arkivnavn + ".txt";
				String[] valg1 = { "Vis filmer i arkivet", "Søk, slette eller legg til filmer",
						"Tilbake til hovedmeny" };
				String[] valg2 = { "Søk", "Legg til en film", "Slett en film","Tilbake" };
				String[] søkvalg = { "Søk etter filmer av produsent", "Søk etter filmer med tittel", "Tilbake" };
				boolean meny2 = true;
				
				
				while (meny2) {

					// laster inn arkiv som skal jobbes med fra fil gitt ved parameteren filnavn,
					// oppretter tekstgrensesnitt og tabell av film objekt
					FilmarkivADT arkivEdit = null;
					arkivEdit = new Filmarkiv(Fil.antall(filnavn));
					arkivEdit = Fil.nylesFraFil(filnavn);
					Tekstgrensesnitt lestinn = new Tekstgrensesnitt();
					Film[] arkivE = arkivEdit.hentFilmTabell();
					Film ARKIVEDIT;
					boolean meny3 = true;
					//meny2
					int svar1 = JOptionPane.showOptionDialog(null, "Hva vil du gjøre?\n", "Eksisterende arkiv",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, ikon, valg1, 0);

					if (svar1 == 0) { // valg, viser filmer i arkivet på skjerm

						// arkivet er tomt
						if (Fil.antall(filnavn) == 0) {
							lestinn.tom();
						} else {
							for (int i = 0; i < arkivE.length; i++) {
								ARKIVEDIT = arkivE[i];
								lestinn.visFilm(ARKIVEDIT);
							}
						}

					} else if (svar1 == 1) {
						//meny3
						while (meny3) {	
						
						int svar2 = JOptionPane.showOptionDialog(null, "Hva ønsker du å gjøre?",
								"Søk, slette eller legg til filmer", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE, ikon, valg2, 0);
						if (svar2 == 0) {
							//valg: søk i arkiv
							boolean meny4 = true;
							while (meny4) {
							int svar3 = JOptionPane.showOptionDialog(null, "Hva ønsker du å gjøre?",
									"Søk i arkivet", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.INFORMATION_MESSAGE, ikon, søkvalg, 0);
							if (svar3 == 0) {
							// søk ved produsent
								String produsentsok = showInputDialog("Skriv inn søkeord for produsent");
								lestinn.skrivUtFilmProdusent(arkivEdit, produsentsok);

							} else if (svar3 == 1) {
							// søk ved tittel
								String tittelsok = showInputDialog("Skriv inn søkeord for tittel");
								lestinn.skrivUtFilmDelstrengITittel(arkivEdit, tittelsok);
							} else {
								// returnerer til meny3
								meny4 = false;
								}
							}

						} else if (svar2 == 1) {
							// valg: legg til film i eksisterende arkiv
							int antall = Integer
									.parseInt(showInputDialog(null, "Hvor mangen filmer vil du legge til?"));
							for (int i = 0; i < antall; i++) {
								Film nyFilm = new Film();
								nyFilm = lestinn.lesFilm();
								arkivEdit.leggTilFilm(nyFilm);
								lestinn.visFilm(nyFilm);
							}

							// lagrer arkivet til fil "filnavn"
							Fil.skrivTilFil(arkivEdit, filnavn);
							// todo
						} else if (svar2 == 2) {
							// valg: slette film i eksisterende arkiv

							int filmnr = Integer.parseInt(showInputDialog(null,
									"Skriv inn film nummeret til filmen som skal slettes fra arkivet"));

							// kontroll ble film ble slettet?
							if (arkivEdit.slettFilm(filmnr)) {
								// lagrer arkivet til fil "filnavn"
								showMessageDialog(null, "Film nr: " + filmnr + " ble slettet, oppdaterer filen");
								Fil.skrivTilFil(arkivEdit, filnavn);
								// meny2 = 1;
							} else {
								showMessageDialog(null, "Ingen film ble slettet");
							}
							// todo
						} else {
							//  returnerer til meny2 
							meny3 = false;
						}
					}	
					} else {
						// returnere til hovedmenyen"
						meny2 = false;
					}
				}
			} else {
				// avslutter programmet

				meny1 = false;

				// todo
				// lagre arkivet?
				
				// Skulle laget menyen objekt orientert da denne koden er vanskelig å orientere seg i.
			}
		}
	}
}
