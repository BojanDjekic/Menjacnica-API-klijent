package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.klase.Zemlja;
import menjacnica.util.URLConnectionUtil;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class GlavniProzorGUI extends JFrame {
	private LinkedList<Zemlja> zemlje = new LinkedList<Zemlja>();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzorGUI frame = new GlavniProzorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GlavniProzorGUI() {
		setTitle("Menjacnica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
		lblIzValuteZemlje.setBounds(44, 60, 128, 16);
		contentPane.add(lblIzValuteZemlje);
		
		JLabel lblUValutuZemlje = new JLabel("U valutu zemlje:");
		lblUValutuZemlje.setBounds(277, 60, 128, 16);
		contentPane.add(lblUValutuZemlje);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(44, 104, 115, 22);
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
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(277, 104, 115, 22);
		try {
			String content = URLConnectionUtil.getContent("http://free.currencyconverterapi.com/api/v3/countries");
			Gson gson = new GsonBuilder().create();
			JsonParser jsonParser = new JsonParser();
			JsonObject a = jsonParser.parse(content).getAsJsonObject().getAsJsonObject("results");

			for (Entry<String, JsonElement> entry : a.entrySet()) {
			    Zemlja zemlja = gson.fromJson(entry.getValue(), Zemlja.class);
			    zemlje.add(zemlja);
			    comboBox_1.addItem(zemlja.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPane.add(comboBox_1);
		
		JButton btnKonvertuj = new JButton("Konvertuj");
		btnKonvertuj.setBounds(167, 182, 97, 25);
		contentPane.add(btnKonvertuj);
	}
}
