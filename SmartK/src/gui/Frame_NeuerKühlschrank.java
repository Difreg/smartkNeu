package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ger�te.Ger�te;
import ger�te.K�hlschrank;
import listen.Bestandsliste;
import listen.Einkaufsliste;
import listen.Entnahmeliste;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_NeuerK�hlschrank extends JFrame {

	/**
	 * Klasse mit Formular zur Erstellung eines neuen K�hlschranks
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_temperatur;
	private JTextField textField_name;
	private JTextField textField_standort;
	private JTextField textField_volumen;
	private JTextField textField_f�cher;



	/**
	 * Create the frame.
	 */
	public Frame_NeuerK�hlschrank(Ger�te inventar) {
		
		setTitle("Neuen K\u00FChlschrank erstellen...");
		setBounds(100, 100, 420, 550);		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 10, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(10, 2, 10, 10));
		
		JLabel lblBezeichnung = new JLabel("Bezeichnung:");
		lblBezeichnung.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel.add(lblBezeichnung);
		
		JLabel lblNewLabel = new JLabel("Standort:");
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel.add(lblNewLabel);
		
		JLabel lblGreinLiter = new JLabel("Gr\u00F6\u00DFe (in Liter):");
		lblGreinLiter.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel.add(lblGreinLiter);
		
		JLabel lblAnzahlDerFcher = new JLabel("Anzahl der F\u00E4cher:");
		lblAnzahlDerFcher.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel.add(lblAnzahlDerFcher);
		
		JLabel lblGefrierfachIntegriert = new JLabel("Gefrierfach integriert?");
		lblGefrierfachIntegriert.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel.add(lblGefrierfachIntegriert);
		
		JLabel lblAnfangstemperatur = new JLabel("Betriebstemperatur (in �C):");
		lblAnfangstemperatur.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel.add(lblAnfangstemperatur);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fensterSchlie�en();
			}
		});
		btnAbbrechen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel.add(btnAbbrechen);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(10, 1, 10, 10));
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_1.add(textField_name);
		textField_name.setColumns(10);
		
		textField_standort = new JTextField();
		textField_standort.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_1.add(textField_standort);
		textField_standort.setColumns(10);
		
		textField_volumen = new JTextField();
		textField_volumen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_1.add(textField_volumen);
		textField_volumen.setColumns(10);
		
		textField_f�cher = new JTextField();
		textField_f�cher.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_1.add(textField_f�cher);
		textField_f�cher.setColumns(10);
		
		JComboBox<String> comboBox_gefrierfach = new JComboBox<String>();
		comboBox_gefrierfach.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		comboBox_gefrierfach.addItem("Ja");
		comboBox_gefrierfach.addItem("Nein");
		panel_1.add(comboBox_gefrierfach);
		
		
		
		textField_temperatur = new JTextField();
		textField_temperatur.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_1.add(textField_temperatur);
		textField_temperatur.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_1.add(lblNewLabel_1);
		
		JButton btnKhlschrankErstellen = new JButton("K\u00FChlschrank erstellen");
		btnKhlschrankErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				K�hlschrank neuerK�hlschrank = new K�hlschrank();
				if(!textField_name.getText().equals("")) {
					if(!textField_standort.getText().equals("")) {
						if(!textField_volumen.getText().equals("") && Integer.parseInt(textField_volumen.getText()) < 401 && Integer.parseInt(textField_volumen.getText()) > 39) {
							if(!textField_f�cher.getText().equals("")) {
								if(!textField_temperatur.getText().equals("")&&Integer.parseInt(textField_temperatur.getText())>3 && Integer.parseInt(textField_temperatur.getText())< 12) {
									neuerK�hlschrank.setName(textField_name.getText());
									neuerK�hlschrank.setStandort(textField_standort.getText());
									neuerK�hlschrank.setGr��e(Double.parseDouble(textField_volumen.getText()));
									neuerK�hlschrank.setF�cher(Integer.parseInt(textField_f�cher.getText()));
									boolean gefrierfachVorhanden = false;
									if(comboBox_gefrierfach.getSelectedItem().equals("Ja")){
										gefrierfachVorhanden = true;
									}
									neuerK�hlschrank.setGefrierfachVorhanden(gefrierfachVorhanden);
									neuerK�hlschrank.setTemperatur(Double.parseDouble(textField_temperatur.getText()));
									neuerK�hlschrank.setBest�nde(new Bestandsliste());
									neuerK�hlschrank.setEinkaufen(new Einkaufsliste());
									neuerK�hlschrank.setEntnommen(new Entnahmeliste());
									inventar.k�hlschrankHinzuf�gen(neuerK�hlschrank);
									inventar.displayGer�te();
									
									JOptionPane.showMessageDialog(null, "K�hlschrank erstellt. ");
									inventar.ger�teSpeichern();
									fensterSchlie�en();
								}
								else {
									JOptionPane.showMessageDialog(null, "Ung�ltige Temperatureingabe ");

								}
							}
							else {
								JOptionPane.showMessageDialog(null, "F�cherangabe fehlt ");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Ung�ltige Volumenangabe ");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Standort fehlt ");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Bezeichnung fehlt ");
				}
				
				
				
				
			}
		});
		btnKhlschrankErstellen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_1.add(btnKhlschrankErstellen);
	}
	
	private void fensterSchlie�en(){
		this.setVisible(false);
	}

}
