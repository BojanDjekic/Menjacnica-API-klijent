package menjacnica.sistemskeoperacije;

import java.util.Map.Entry;

import javax.swing.JComboBox;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.domen.Zemlja;
import menjacnica.gui.GUIKontroler;
import menjacnica.util.URLConnectionUtil;

public class SOKonboBox {
	public static void izvrso(JComboBox comboBox) {
		try {
			String content = URLConnectionUtil.getContent("http://free.currencyconverterapi.com/api/v3/countries");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jsonParser = new JsonParser();
			JsonObject a = jsonParser.parse(content).getAsJsonObject().getAsJsonObject("results");

			for (Entry<String, JsonElement> entry : a.entrySet()) {
			    Zemlja zemlja = gson.fromJson(entry.getValue(), Zemlja.class);
			    GUIKontroler.zemlje.add(zemlja);
			    comboBox.addItem(zemlja.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
