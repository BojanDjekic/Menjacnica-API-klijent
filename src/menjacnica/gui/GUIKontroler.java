package menjacnica.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import menjacnica.domen.Istorija;
import menjacnica.domen.Menjacnica;
import menjacnica.domen.Valuta;
import menjacnica.domen.Zemlja;
import menjacnica.sistemskeoperacije.SOKonboBox;
import menjacnica.sistemskeoperacije.SOKonverzija;
import menjacnica.sistemskeoperacije.SOUbaciUFajl;
import menjacnica.sistemskeoperacije.SOUcitajIzFajla;
import menjacnica.sistemskeoperacije.SOVratiSkraceniNaziv;
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
	public static void konvertuj(String naziv1, String naziv2) {
		Menjacnica.konverzija(naziv1, naziv2);
	}
}
