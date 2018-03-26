package gui.lebensmittelverwaltung;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listen.Bestandsliste;


import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.Color;

public class Frame_LebensmittelHinzuf�gen extends JFrame {

	/**
	 * Fenster, um Lebensmittel hinzuzuf�gen
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSuchen;
	private Bestandsliste best�nde;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Frame_LebensmittelHinzuf�gen(Bestandsliste best�nde�bergabe, String nameK�hlschrank) {
		setTitle(nameK�hlschrank + " - Lebensmittel hinzuf�gen");
		this.best�nde = new Bestandsliste();
		this.best�nde.setAnzahl(best�nde�bergabe.getAnzahl());
		this.best�nde.setBest�nde(best�nde�bergabe.getBest�nde());
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 10));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
	
		
		DefaultListModel<String> model = createModel();
		
		JList<String> list = new JList<String>();
		list.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		list.setModel(model);
		scrollPane.setViewportView(list);
		
	
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230,230,250));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(4, 0, 0, 10));
		
		txtSuchen = new JTextField();
		txtSuchen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
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
						best�nde.best�ndeLaden(nameK�hlschrank + "Bestandsliste.ser");
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
		txtSuchen.setText("Suchen...");
		panel.add(txtSuchen);
		txtSuchen.setColumns(10);
		
		JButton btnListeAktualisieren = new JButton("Liste aktualisieren");
		btnListeAktualisieren.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		btnListeAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSuchen.getText().equals("") || txtSuchen.getText().equals("Suchen...")){
					best�nde.best�ndeLaden(nameK�hlschrank + "Bestandsliste.ser");
					list.setModel(createModel());
				}
				else{
					String suchbegriff = txtSuchen.getText();
					String[] ergebnisse = lebensmittelSuchen(suchbegriff);
					
					list.setModel(createModel(ergebnisse));
				}
			}
		});
		
		
		panel.add(btnListeAktualisieren);
		
		JButton btnLebensmittelHinzufgen = new JButton("Ausgew\u00E4hltes Lebensmittel hinzuf\u00FCgen");
		
		btnLebensmittelHinzufgen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		btnLebensmittelHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String neuesLebensmittel = wortAusschneiden(list.getSelectedValue());
			
				
				String eingabeMHD = JOptionPane.showInputDialog("Mindesthaltbarkeitsdatum eingeben (TTMMJJ): ");
				if(eingabeMHD.length()==6) {
					String tag = eingabeMHD.substring(0,2);
					String monat = eingabeMHD.substring(2,4);
					String jahr = eingabeMHD.substring(4,6);
					Calendar mhd = Calendar.getInstance();
					mhd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tag));
					mhd.set(Calendar.MONTH, (Integer.parseInt(monat)-1));
					mhd.set(Calendar.YEAR, 2000+Integer.parseInt(jahr));
					best�nde.vorhandenesLebensmittelHinzuf�gen(neuesLebensmittel, mhd);
					list.setModel(createModel());
					best�nde.displayUI();
					best�nde.best�ndeSpeichern(nameK�hlschrank + "Bestandsliste.ser");
				}
				else {
					JOptionPane.showMessageDialog(null,"Datumsangabe ung�ltig. Bitte beachten Sie das Format TTMMJJ");
				}
			}
		});
		panel.add(btnLebensmittelHinzufgen);
		
		JButton btnZurck = new JButton("Zur\u00FCck");
		
		btnZurck.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fensterSchlie�en();
			}
		});
		panel.add(btnZurck);
	}
	
	
	
	private DefaultListModel<String> createModel(){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = best�nde.toListedString();
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
	
	private DefaultListModel<String> createModel(String[] eintr�ge){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = eintr�ge;
		for(int i = 0; i<values.length;i++){
			model.addElement(values[i]);
		}
		return model;
	}
	
	private String[] lebensmittelSuchen(String suche){
		
		String[] vorhanden = this.best�nde.toListedString();
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

}
