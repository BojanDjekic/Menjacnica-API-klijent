package menjacnica.sistemskeoperacije;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import menjacnica.domen.Istorija;
import menjacnica.gui.GUIKontroler;

public class SOUbaciUFajl {
	public static void izvrsi(String dodatak,double kurs) {
		Istorija i = new Istorija();
		i.setIzValuta(dodatak.substring(0,3));
		i.setuValuta(dodatak.substring(4,7));
		i.setKurs(kurs);
		Date datumDanasnji = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS"); 
		String datum2 = dt.format(datumDanasnji);
		i.setDatumVreme(datum2);
		GUIKontroler.istorija.add(i);
		try (FileWriter writer = new FileWriter("data/log.json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(GUIKontroler.istorija,writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
