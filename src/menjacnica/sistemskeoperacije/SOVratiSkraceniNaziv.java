package menjacnica.sistemskeoperacije;

import menjacnica.domen.Zemlja;
import menjacnica.gui.GUIKontroler;

public class SOVratiSkraceniNaziv {
	public static String izvrsi(String punNaziv) {
		for (int i = 0; i < GUIKontroler.zemlje.size(); i++) {
			Zemlja z = GUIKontroler.zemlje.get(i);
			if (z.getName().equals(punNaziv)){
				return z.getCurrencyId();
			}
		}
		return null;
	}
}
