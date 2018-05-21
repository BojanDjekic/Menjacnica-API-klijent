package menjacnica.domen;

import javax.swing.JComboBox;

import menjacnica.sistemskeoperacije.SOKonboBox;
import menjacnica.sistemskeoperacije.SOKonverzija;
import menjacnica.sistemskeoperacije.SOUbaciUFajl;
import menjacnica.sistemskeoperacije.SOUcitajIzFajla;
import menjacnica.sistemskeoperacije.SOVratiSkraceniNaziv;

public class Menjacnica {
	public static void komboBox(JComboBox comboBox) {
		SOKonboBox.izvrso(comboBox);
	}
	public static String vratiSkraceniNaziv(String punNaziv) {
		return SOVratiSkraceniNaziv.izvrsi(punNaziv);
	}
	public static void iscitajIzFajla() {
		SOUcitajIzFajla.izvrsi();
	}
	public static void ubaciUFajl(String dodatak,double kurs) {
		SOUbaciUFajl.izvrsi(dodatak, kurs);
	}
	public static void konverzija(String naziv1, String naziv2) {
		SOKonverzija.izvrsi(naziv1, naziv2);
	}
}
