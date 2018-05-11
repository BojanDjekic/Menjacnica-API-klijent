package menjacnica.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.klase.Istorija;
import menjacnica.klase.Valuta;
import menjacnica.klase.Zemlja;
import menjacnica.util.URLConnectionUtil;

public class GUIKontroler {
	public static GlavniProzorGUI gp;
	public static LinkedList<Zemlja> zemlje = new LinkedList<Zemlja>();
	public static LinkedList<Istorija> istorija = new LinkedList<Istorija>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIKontroler.gp= new GlavniProzorGUI();
					GUIKontroler.gp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void komboBox(JComboBox comboBox) {
		try {
			String content = URLConnectionUtil.getContent("http://free.currencyconverterapi.com/api/v3/countries");
			Gson gson = new GsonBuilder().create();
			JsonParser jsonParser = new JsonParser();
			JsonObject a = jsonParser.parse(content).getAsJsonObject().getAsJsonObject("results");

			for (Entry<String, JsonElement> entry : a.entrySet()) {
			    Zemlja zemlja = gson.fromJson(entry.getValue(), Zemlja.class);
			    zemlje.add(zemlja);
			    comboBox.addItem(zemlja.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String vratiSkraceniNaziv(String punNaziv) {
		for (int i = 0; i < zemlje.size(); i++) {
			Zemlja z = zemlje.get(i);
			if (z.getName().equals(punNaziv)){
				return z.getCurrencyId();
			}
		}
		return null;
	}
	public static void iscitajIzFajla() {
		try (FileReader reader = new FileReader("data/log.json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
			for (int i = 0; i < jsonArray.size(); i++) {
				Istorija is = gson.fromJson(jsonArray.get(i), Istorija.class);
				istorija.add(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void ubaciUFajl() {
		
	}
	public static void konverzija(String naziv1, String naziv2) {
		String osnova = "http://free.currencyconverterapi.com/api/v3/convert?q=";
		String dodatak = vratiSkraceniNaziv(naziv1) + "_" + vratiSkraceniNaziv(naziv2);
		String url = osnova + dodatak;
		try {
			String content = URLConnectionUtil.getContent(url);
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObjekat = jsonParser.parse(content).getAsJsonObject().getAsJsonObject("results").getAsJsonObject(dodatak);
			Gson gson = new GsonBuilder().create();
			Valuta valuta = gson.fromJson(jsonObjekat, Valuta.class);
			if (valuta == null) {
				JOptionPane.showConfirmDialog(gp.contentPane, "Ne postoje podaci o ovoj promeni na sajtu.","Greska!",JOptionPane.ERROR_MESSAGE);
			}
			else {
				try {
				double iz = Double.parseDouble(gp.textField.getText());
				double u = iz * valuta.getVal();
				gp.textField_1.setText(String.valueOf(u));
				ubaciUFajl();
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(gp.contentPane, "Prvo polje iznos mora biti validno popunjeno\n"
							+ "(ne sme biti prazno i ne sme sadrzati karaktere osim cifara.","Greska!",JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
