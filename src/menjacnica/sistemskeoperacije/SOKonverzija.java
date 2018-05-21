package menjacnica.sistemskeoperacije;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.domen.Menjacnica;
import menjacnica.domen.Valuta;
import menjacnica.gui.GUIKontroler;
import menjacnica.util.URLConnectionUtil;

public class SOKonverzija {
	public static void izvrsi(String naziv1, String naziv2) {
		final String osnova = "http://free.currencyconverterapi.com/api/v3/convert?q=";
		final String dodatak = Menjacnica.vratiSkraceniNaziv(naziv1) + "_" + Menjacnica.vratiSkraceniNaziv(naziv2);
		final String url = osnova + dodatak;
		try {
			String content = URLConnectionUtil.getContent(url);
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObjekat = jsonParser.parse(content).getAsJsonObject().getAsJsonObject("results").getAsJsonObject(dodatak);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Valuta valuta = gson.fromJson(jsonObjekat, Valuta.class);
			if (valuta == null) {
				JOptionPane.showConfirmDialog(GUIKontroler.gp.contentPane, "Ne postoje podaci o ovoj promeni na sajtu.","Greska!",JOptionPane.ERROR_MESSAGE);
			}
			else {
				try {
				double iz = Double.parseDouble(GUIKontroler.gp.textField.getText());
				double u = iz * valuta.getVal();
				GUIKontroler.gp.textField_1.setText(String.valueOf(u));
				Menjacnica.ubaciUFajl(dodatak, valuta.getVal());
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(GUIKontroler.gp.contentPane, "Prvo polje iznos mora biti validno popunjeno\n"
							+ "(ne sme biti prazno i ne sme sadrzati karaktere osim cifara.","Greska!",JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
