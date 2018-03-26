package gui.lebensmittelverwaltung;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ger�te.K�hlschrank;
import lebensmittel.Lebensmittel;


import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JLabel;

public class Frame_Bestandsverwaltung extends JFrame {

	/**
	 * Klasse f�r die Bestandsverwaltung
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private K�hlschrank ausgew�hlterK�hlschrank;
	private JTextField txtSuchen;

	
	

	/**
	 * Create the frame.
	 */
	public Frame_Bestandsverwaltung(K�hlschrank k�hlschrank) {
		
		setTitle(k�hlschrank.getName() + " - Bestandsverwaltung");
		this.ausgew�hlterK�hlschrank = new K�hlschrank();
		
		this.ausgew�hlterK�hlschrank.setName(k�hlschrank.getName());
		this.ausgew�hlterK�hlschrank.setStandort(k�hlschrank.getStandort());
		this.ausgew�hlterK�hlschrank.setBest�nde(k�hlschrank.getBest�nde());
		this.ausgew�hlterK�hlschrank.setEinkaufen(k�hlschrank.getEinkaufen());
		this.ausgew�hlterK�hlschrank.setEntnommen(k�hlschrank.getEntnommen());
		this.ausgew�hlterK�hlschrank.setF�cher(k�hlschrank.getF�cher());
		this.ausgew�hlterK�hlschrank.setGefrierfachVorhanden(k�hlschrank.isGefrierfachVorhanden());
		this.ausgew�hlterK�hlschrank.setGr��e(k�hlschrank.getGr��e());
		this.ausgew�hlterK�hlschrank.setTemperatur(k�hlschrank.getTemperatur());
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<String>();
		
		scrollPane.setViewportView(list);
		
		list.setModel(createModel());
		
		
		
		list.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230,230,250));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230,230,250));
		panel.add(panel_2);
		
		txtSuchen = new JTextField();
		txtSuchen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSuchen.selectAll();
			}
		});
		txtSuchen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(txtSuchen.getText().equals("") || txtSuchen.getText().equals("Suchen...")){
						ausgew�hlterK�hlschrank.getBest�nde().best�ndeLaden(ausgew�hlterK�hlschrank.getName() + "Bestandsliste.ser");
						list.setModel(createModel());
					}
					else{
						String suchbegriff = txtSuchen.getText();
						String[] ergebnisse = lebensmittelSuchen(suchbegriff);
						
						list.setModel(createModel(ergebnisse));
					}
				}
			}
		});
		panel_2.setLayout(new GridLayout(4, 1, 10, 10));
		txtSuchen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		txtSuchen.setText("Suchen...");
		panel_2.add(txtSuchen);
		txtSuchen.setColumns(10);
		
		JButton btnLebensmittelHinzufgen = new JButton("Lebensmittel hinzuf\u00FCgen");
		btnLebensmittelHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame neu = new Frame_LebensmittelHinzuf�gen(ausgew�hlterK�hlschrank.getBest�nde(), ausgew�hlterK�hlschrank.getName());
				
				neu.setVisible(true);
			}
		});
		btnLebensmittelHinzufgen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_2.add(btnLebensmittelHinzufgen);
		
		JButton btnLebensmittelinformationenAnzeigen = new JButton("<html><body>Lebensmittelinforma-<br>tionen anzeigen</body></html>");
		btnLebensmittelinformationenAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String eingabe = wortAusschneiden(list.getSelectedValue());
				ListIterator<Lebensmittel> it = ausgew�hlterK�hlschrank.getBest�nde().getBest�nde().listIterator();
				while(it.hasNext()){
					Lebensmittel pr�fung = it.next();
					if(pr�fung.getName().equals(eingabe)){
						JFrame neu = new Frame_Lebensmittelinformationen(pr�fung);
						neu.setVisible(true);
					}
				}

			}
		});
		btnLebensmittelinformationenAnzeigen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		panel_2.add(btnLebensmittelinformationenAnzeigen);
		
		JLabel label = new JLabel("");
		panel_2.add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(230,230,250));
		panel.add(panel_3);
		
		JButton btnListeAktualisieren = new JButton("Liste aktualisieren");
		btnListeAktualisieren.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnListeAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSuchen.getText().equals("") || txtSuchen.getText().equals("Suchen...")){
					ausgew�hlterK�hlschrank.getBest�nde().best�ndeLaden(ausgew�hlterK�hlschrank.getName() + "Bestandsliste.ser");
					list.setModel(createModel());
				}
				else{
					String suchbegriff = txtSuchen.getText();
					String[] ergebnisse = lebensmittelSuchen(suchbegriff);
					
					list.setModel(createModel(ergebnisse));
				}
			}
		});
		panel_3.setLayout(new GridLayout(4, 1, 10, 10));
		panel_3.add(btnListeAktualisieren);
		
		JButton btnLebensmittelEntnehmen = new JButton("<html><body>Markierte Lebensmittel <br>entnehmen</body></html>");
		btnLebensmittelEntnehmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�bertragen der Lebensmittel auf die Entnahmeliste
				List<String> eingabeListe = list.getSelectedValuesList(); 
				String[] eingabe = new String[list.getSelectedIndices().length];
				Iterator <String> itList = eingabeListe.iterator();
				int i = 0;
				while(itList.hasNext()){
					eingabe[i] = wortAusschneiden(itList.next());
					i++;
				}
				ListIterator<Lebensmittel> it = ausgew�hlterK�hlschrank.getBest�nde().getBest�nde().listIterator();
				i=0;
				while(it.hasNext()){
					Lebensmittel pr�fung = it.next();
					if(i<eingabe.length && eingabe[i].equals(wortAusschneiden(pr�fung.getName()))){
						ausgew�hlterK�hlschrank.getEntnommen().lebensmittelHinzuf�gen(pr�fung);
						i++;
					}
				}
				ausgew�hlterK�hlschrank.getEntnommen().toLittleString(ausgew�hlterK�hlschrank.getEntnommen().getEntnahmeliste());
				
				// �ffnen des Frames der Entnahmeliste
				JFrame neu = new Frame_Entnahmeliste(ausgew�hlterK�hlschrank ,ausgew�hlterK�hlschrank.getEntnommen());
				neu.setVisible(true);
				
			}
		});
		btnLebensmittelEntnehmen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		panel_3.add(btnLebensmittelEntnehmen);
		
		JButton btnZurckZumHauptmen = new JButton("Zur\u00FCck zum Hauptmen\u00FC");
		btnZurckZumHauptmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fensterSchlie�en();
			}
		});
		
		JButton btnLebensmittelAufEinkaufsliste = new JButton("<html><body>Lebensmittel auf <br>Einkaufsliste \u00FCbertragen</body></html>");
		btnLebensmittelAufEinkaufsliste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eingabe = list.getSelectedValue();
				String nameLebensmittel = wortAusschneiden(eingabe);
				Lebensmittel �bergabe = lebensmittelErzeugen(k�hlschrank.getBest�nde().getBest�nde(), nameLebensmittel).clone();
				�bergabe.setAnzahl(1);
				k�hlschrank.getEinkaufen().lebensmittelHinzuf�gen(�bergabe);
				k�hlschrank.getEinkaufen().eink�ufeSpeichern(k�hlschrank.getName()+"Einkaufsliste.ser");
			}
		});
		btnLebensmittelAufEinkaufsliste.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		panel_3.add(btnLebensmittelAufEinkaufsliste);
		btnZurckZumHauptmen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		panel_3.add(btnZurckZumHauptmen);
	}
	
	private DefaultListModel<String> createModel(){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = ausgew�hlterK�hlschrank.getBest�nde().toSortedString();
		for(int i = 0; i<values.length;i++){
			model.addElement(values[i]);
		}
		return model;
	}
	
	private DefaultListModel<String> createModel(String[] eintr�ge){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = eintr�ge;
		for(int i = 0; i<values.length;i++){
			model.addElement(values[i]);
		}
		return model;
	}
	
	private String[] lebensmittelSuchen(String suche){
		
		String[] vorhanden = this.ausgew�hlterK�hlschrank.getBest�nde().toSortedString();
		String[] ausgabe = new String[vorhanden.length];
		int durchlauf = 0;
		int gefunden = 0;
		
		
		while(durchlauf < vorhanden.length && !(vorhanden[durchlauf]==null)){
			for (int i=0; i<=(vorhanden[durchlauf].length() - suche.length()); i++){
				if(suche.equals(vorhanden[durchlauf].substring(i, suche.length()+i))){
					ausgabe[gefunden] = vorhanden[durchlauf];
					gefunden++;
				}
			}
			durchlauf ++;
		}
		return ausgabe;
	}
	
	
	private void fensterSchlie�en(){
		this.setVisible(false);
	}
	
	private String wortAusschneiden(String eingabe){
		String zur�ck = "";
		int i = 0;
		while(i<eingabe.length()&&!(eingabe.charAt(i)==' ')){
			zur�ck = zur�ck + eingabe.charAt(i);
			i++;
		}
		return zur�ck;
	
	}
	
	private Lebensmittel lebensmittelErzeugen(ArrayList<Lebensmittel> liste, String lebensmittel){
		ListIterator<Lebensmittel> itListe = liste.listIterator();
		while(itListe.hasNext()){
			Lebensmittel pr�fung = itListe.next();
			if(wortAusschneiden(pr�fung.getName()).equals(lebensmittel)){
				return pr�fung;
			}
		}
		return null;
	
	}
	

}
