package gui.lebensmittelverwaltung;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import ger�te.K�hlschrank;
import lebensmittel.Lebensmittel;
import listen.Entnahmeliste;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_Entnahmeliste extends JFrame {

	/**
	 * Klasse f�r den Lebensmittelverteiler
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Entnahmeliste entnahmeliste;
	

	/**
	 * Create the frame.
	 */
	public Frame_Entnahmeliste(K�hlschrank ausgew�hlterK�hlschrank ,Entnahmeliste �bergebeneListe) {
		setTitle(ausgew�hlterK�hlschrank.getName() + " - Lebensmittelverteiler");
		
		this.entnahmeliste = new Entnahmeliste();
		this.entnahmeliste.setAnzahlEinkaufsliste(�bergebeneListe.getAnzahlEinkaufsliste());
		this.entnahmeliste.setAnzahlEntnommen(�bergebeneListe.getAnzahlEntnommen());
		this.entnahmeliste.setAnzahlLeer(�bergebeneListe.getAnzahlLeer());
		this.entnahmeliste.setEinkaufsliste(�bergebeneListe.getEinkaufsliste());
		this.entnahmeliste.setEntnahmeliste(�bergebeneListe.getEntnahmeliste());
		this.entnahmeliste.setLeereLebensmittel(�bergebeneListe.getLeereLebensmittel());
		
		
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 5, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230,230,250));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 0, 0, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(230,230,250));
		scrollPane.setBorder(new LineBorder(new Color(64, 64, 64)));
		panel.add(scrollPane);
		
		JList<String> listZur�ck = new JList<String>();
		scrollPane.setViewportView(listZur�ck);
		listZur�ck.setModel(createModel(entnahmeliste.getEntnahmeliste()));
		
		JLabel lblEntnommen = new JLabel("Zur\u00FCcklegen:");
		lblEntnommen.setBackground(new Color(230,230,250));
		scrollPane.setColumnHeaderView(lblEntnommen);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(new Color(64, 64, 64)));
		scrollPane_2.setBackground(new Color(230,230,250));
		panel.add(scrollPane_2);
		
		JLabel lblAusBestandEntfernen = new JLabel("Aus Bestand entfernen:");
		scrollPane_2.setColumnHeaderView(lblAusBestandEntfernen);
		
		JList<String> listLeer = new JList<String>();
		scrollPane_2.setViewportView(listLeer);
		listLeer.setModel(createModel(entnahmeliste.getLeereLebensmittel()));
		
		JButton btnAllesZurcklegen = new JButton("Alles zur\u00FCcklegen");
		btnAllesZurcklegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entnahmeliste.listenLeeren();
				fensterSchlie�en();
			}
		});
		panel.add(btnAllesZurcklegen);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230,230,250));
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 1, 0, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(64, 64, 64)));
		panel_1.add(scrollPane_1);
		
		JList<String> listEinkauf = new JList<String>();
		scrollPane_1.setViewportView(listEinkauf);
		listEinkauf.setModel(createModel(entnahmeliste.getEinkaufsliste()));
		
		JLabel lblAufEinkaufsliste = new JLabel("Auf Einkaufsliste:");
		scrollPane_1.setColumnHeaderView(lblAufEinkaufsliste);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230,230,250));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 0, 0, 10));
		
		JButton btnZur�cklegen = new JButton("Lebensmittel zur\u00FCcklegen");
		btnZur�cklegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�berpr�fung welches Lebensmittel auf welcher Liste ausgew�hlt wurde
				String markListeZur�ck = (String) listZur�ck.getSelectedValue();
				String markListeLeer= (String) listLeer.getSelectedValue();
				String markListeEinkauf = (String) listEinkauf.getSelectedValue();
				ArrayList <Lebensmittel> quelle = new ArrayList<Lebensmittel>();
				ArrayList<Lebensmittel> ziel = entnahmeliste.getEntnahmeliste();
				String lebensmittel = "";
				if(markListeZur�ck==null){
					if(markListeLeer == null){
						quelle = entnahmeliste.getEinkaufsliste();
						lebensmittel = wortAusschneiden(markListeEinkauf);
					}
					else{
						quelle = entnahmeliste.getLeereLebensmittel();
						lebensmittel = wortAusschneiden(markListeLeer);
					}
				}
				else{
					quelle = entnahmeliste.getEntnahmeliste();
					lebensmittel = wortAusschneiden(markListeZur�ck);
				}
				// Verschieben des Lebhensmittels von einer auf die andere Liste
				entnahmeliste.lebensmittelVerschieben(quelle, ziel, lebensmittel);
				
				// Aktualisieren der Listen im JFrame
				listZur�ck.setModel(createModel(entnahmeliste.getEntnahmeliste()));
				listLeer.setModel(createModel(entnahmeliste.getLeereLebensmittel()));
				listEinkauf.setModel(createModel(entnahmeliste.getEinkaufsliste()));
				
			}
		});
		panel_2.add(btnZur�cklegen);
		
		JButton btnAufDieEinkaufsliste = new JButton("Auf die Einkaufsliste");
		btnAufDieEinkaufsliste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�berpr�fung welches Lebensmittel auf welcher Liste ausgew�hlt wurde
				String markListeZur�ck = (String) listZur�ck.getSelectedValue();
				String markListeLeer= (String) listLeer.getSelectedValue();
				String markListeEinkauf = (String) listEinkauf.getSelectedValue();
				ArrayList <Lebensmittel> quelle = new ArrayList<Lebensmittel>();
				ArrayList<Lebensmittel> ziel = entnahmeliste.getEinkaufsliste();
				String lebensmittel = "";
				if(markListeZur�ck == null){
					if(markListeLeer == null){
						quelle = entnahmeliste.getEinkaufsliste();
						lebensmittel = wortAusschneiden(markListeEinkauf);
					}
					else{
						quelle = entnahmeliste.getLeereLebensmittel();
						lebensmittel = wortAusschneiden(markListeLeer);
					}
				}
				else{
					quelle = entnahmeliste.getEntnahmeliste();
					lebensmittel = wortAusschneiden(markListeZur�ck);
				}
				// Verschieben des Lebhensmittels von einer auf die andere Liste
				entnahmeliste.lebensmittelVerschieben(quelle, ziel, lebensmittel);
				
				// Aktualisieren der Listen im JFrame
				listZur�ck.setModel(createModel(entnahmeliste.getEntnahmeliste()));
				listLeer.setModel(createModel(entnahmeliste.getLeereLebensmittel()));
				listEinkauf.setModel(createModel(entnahmeliste.getEinkaufsliste()));
			}
		});
		panel_2.add(btnAufDieEinkaufsliste);
		
		JButton btnAusBestandEntfernen = new JButton("Aus Bestand entfernen");
		btnAusBestandEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�berpr�fung welches Lebensmittel auf welcher Liste ausgew�hlt wurde
				String markListeZur�ck = (String) listZur�ck.getSelectedValue();
				String markListeLeer= (String) listLeer.getSelectedValue();
				String markListeEinkauf = (String) listEinkauf.getSelectedValue();
				ArrayList <Lebensmittel> quelle = new ArrayList<Lebensmittel>();
				ArrayList<Lebensmittel> ziel = entnahmeliste.getLeereLebensmittel();
				String lebensmittel = "";
				if(markListeZur�ck == null){
					if(markListeLeer == null){
						quelle = entnahmeliste.getEinkaufsliste();
						lebensmittel = wortAusschneiden(markListeEinkauf);
					}
					else{
						quelle = entnahmeliste.getLeereLebensmittel();
						lebensmittel = wortAusschneiden(markListeLeer);
					}
				}
				else{
					quelle = entnahmeliste.getEntnahmeliste();
					lebensmittel = wortAusschneiden(markListeZur�ck);
				}
				// Verschieben des Lebhensmittels von einer auf die andere Liste
				entnahmeliste.lebensmittelVerschieben(quelle, ziel, lebensmittel);
				
				// Aktualisieren der Listen im JFrame
				listZur�ck.setModel(createModel(entnahmeliste.getEntnahmeliste()));
				listLeer.setModel(createModel(entnahmeliste.getLeereLebensmittel()));
				listEinkauf.setModel(createModel(entnahmeliste.getEinkaufsliste()));
			}
		});
		panel_2.add(btnAusBestandEntfernen);
		
		JButton btnnderungenSpeichern = new JButton("\u00C4nderungen speichern ");
		btnnderungenSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entnahmeliste.einkaufsliste�bertragen(entnahmeliste.getEinkaufsliste(), ausgew�hlterK�hlschrank.getEinkaufen().getEinkaufsliste());
				ausgew�hlterK�hlschrank.getEinkaufen().eink�ufeSpeichern(ausgew�hlterK�hlschrank.getName()+"Einkaufsliste.ser");
				entnahmeliste.best�ndeAktualisieren(entnahmeliste.getEntnahmeliste(),entnahmeliste.getLeereLebensmittel(),entnahmeliste.getEinkaufsliste() ,ausgew�hlterK�hlschrank.getBest�nde().getBest�nde());
				ausgew�hlterK�hlschrank.getBest�nde().best�ndeSpeichern(ausgew�hlterK�hlschrank.getName()+"Bestandsliste.ser");
				entnahmeliste.listenLeeren();
				fensterSchlie�en();
			}
		});
		panel_1.add(btnnderungenSpeichern);
	}
	
	private DefaultListModel<String> createModel(ArrayList<Lebensmittel> arraylist){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = this.entnahmeliste.toSortedString(arraylist);
		for(int i = 0; i<values.length;i++){
			model.addElement(values[i]);
		}
		return model;
	}
	
	private String wortAusschneiden(String eingabe){
		String zur�ck = "";
		int i = 0;
		while(!(eingabe.charAt(i)==' ')){
			zur�ck = zur�ck + eingabe.charAt(i);
			i++;
		}
		return zur�ck;
	
	}
	
	private void fensterSchlie�en(){
		this.setVisible(false);
	}

}
