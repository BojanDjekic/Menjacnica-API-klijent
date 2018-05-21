package menjacnica.sistemskeoperacije;

import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import menjacnica.domen.Istorija;
import menjacnica.gui.GUIKontroler;

public class SOUcitajIzFajla {
	public static void izvrsi() {
		try (FileReader reader = new FileReader("data/log.json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
			for (int i = 0; i < jsonArray.size(); i++) {
				Istorija is = gson.fromJson(jsonArray.get(i), Istorija.class);
				GUIKontroler.istorija.add(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
