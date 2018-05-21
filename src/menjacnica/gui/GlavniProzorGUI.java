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

import menjacnica.domen.Menjacnica;
import menjacnica.domen.Valuta;
import menjacnica.domen.Zemlja;
import menjacnica.util.URLConnectionUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class GlavniProzorGUI extends JFrame {
	public JPanel contentPane;
	public JTextField textField;
	public JTextField textField_1;

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
		Menjacnica.iscitajIzFajla();
		
		JLabel lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
		lblIzValuteZemlje.setBounds(44, 36, 128, 16);
		contentPane.add(lblIzValuteZemlje);
		
		JLabel lblUValutuZemlje = new JLabel("U valutu zemlje:");
		lblUValutuZemlje.setBounds(277, 36, 128, 16);
		contentPane.add(lblUValutuZemlje);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(44, 65, 115, 22);
		Menjacnica.komboBox(comboBox);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(277, 65, 115, 22);
		Menjacnica.komboBox(comboBox_1);
		contentPane.add(comboBox_1);
		
		JButton btnKonvertuj = new JButton("Konvertuj");
		btnKonvertuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIKontroler.konvertuj(comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString());
			}
		});
		btnKonvertuj.setBounds(167, 182, 97, 25);
		contentPane.add(btnKonvertuj);
		
		JLabel lblIznos = new JLabel("Iznos");
		lblIznos.setBounds(44, 111, 56, 16);
		contentPane.add(lblIznos);
		
		JLabel lblIznos_1 = new JLabel("Iznos");
		lblIznos_1.setBounds(277, 111, 56, 16);
		contentPane.add(lblIznos_1);
		
		textField = new JTextField();
		textField.setBounds(44, 140, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(277, 140, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
