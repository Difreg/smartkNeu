package gui;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ger�te.Ger�te;
import ger�te.K�hlschrank;
import lebensmittel.Lebensmittel;
import listen.Einkaufsliste;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_Einkaufsliste extends JFrame {

	/**
	 * Klasse f�r die GUI der Einkaufslisten (Gesamt und K�hlschrakspezifisch)
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Einkaufsliste einkaufsliste;


	/**
	 * Create the frame.
	 */
	
	/**
	 * @wbp.parser.constructor
	 */
	public Frame_Einkaufsliste(Ger�te inventar) {
		setTitle("Gesamte Einkaufsliste");
		inventar.einkaufslisteZusammentragen2();
		this.einkaufsliste = inventar.getEinkaufen();
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JList<String> list = new JList<String>();
		list.setModel(createModel(einkaufsliste.getEinkaufsliste()));
		scrollPane.setViewportView(list);
	
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 3, 0, 0));
		
		JButton btnAnzahlErh�hen = new JButton("Anzahl erh\u00F6hen");
		btnAnzahlErh�hen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eingabe = list.getSelectedValue();
				String nameLebensmittel = wortAusschneiden(eingabe);
				Lebensmittel pr�fung = lebensmittelErzeugen(einkaufsliste.getEinkaufsliste(), nameLebensmittel);
				einkaufsliste.lebensmittelHinzuf�gen(pr�fung);
				list.setModel(createModel(einkaufsliste.getEinkaufsliste()));

			}
		});
		panel_1.add(btnAnzahlErh�hen);
		
		JButton btnAnzahlVermindern = new JButton("Anzahl vermindern");
		btnAnzahlVermindern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eingabe = list.getSelectedValue();
				String nameLebensmittel = wortAusschneiden(eingabe);
				Lebensmittel pr�fung = lebensmittelErzeugen(einkaufsliste.getEinkaufsliste(), nameLebensmittel);
				einkaufsliste.lebensmittelVermindern(pr�fung);
				list.setModel(createModel(einkaufsliste.getEinkaufsliste()));

			}
		});
		panel_1.add(btnAnzahlVermindern);
		
		JButton btnZurck = new JButton("Zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fensterSchlie�en();
			}
		});
		panel_1.add(btnZurck);
	}
	
	public Frame_Einkaufsliste(K�hlschrank k�hlschrank) {
		setTitle(k�hlschrank.getName() + " - Einkaufsliste");
		k�hlschrank.getEinkaufen().eink�ufeLaden(k�hlschrank.getName()+"Einkaufsliste.ser");
		this.einkaufsliste = k�hlschrank.getEinkaufen();
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JList<String> list = new JList<String>();
		list.setModel(createModel(einkaufsliste.getEinkaufsliste()));
		scrollPane.setViewportView(list);
	
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 3, 0, 0));
		
		JButton btnAnzahlErh�hen = new JButton("Anzahl erh\u00F6hen");
		btnAnzahlErh�hen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eingabe = list.getSelectedValue();
				String nameLebensmittel = wortAusschneiden(eingabe);
				Lebensmittel pr�fung = lebensmittelErzeugen(einkaufsliste.getEinkaufsliste(), nameLebensmittel);
				pr�fung.anzahlErh�hen();
				list.setModel(createModel(einkaufsliste.getEinkaufsliste()));
				k�hlschrank.getEinkaufen().eink�ufeSpeichern(k�hlschrank.getName()+"Einkaufsliste.ser");
			}
		});
		panel_1.add(btnAnzahlErh�hen);
		
		JButton btnAnzahlVermindern = new JButton("Anzahl vermindern");
		btnAnzahlVermindern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eingabe = list.getSelectedValue();
				String nameLebensmittel = wortAusschneiden(eingabe);
				Lebensmittel pr�fung = lebensmittelErzeugen(einkaufsliste.getEinkaufsliste(), nameLebensmittel);
				if(pr�fung.getAnzahl() > 0) {
					pr�fung.anzahlVermindern();
				}
				list.setModel(createModel(einkaufsliste.getEinkaufsliste()));
				k�hlschrank.getEinkaufen().eink�ufeSpeichern(k�hlschrank.getName()+"Einkaufsliste.ser");
			}
		});
		panel_1.add(btnAnzahlVermindern);
		
		JButton btnZurck = new JButton("Zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fensterSchlie�en();
			}
		});
		panel_1.add(btnZurck);
	}
	
	private DefaultListModel<String> createModel(ArrayList<Lebensmittel> arraylist){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = this.einkaufsliste.toSortedString(arraylist);
		for(int i = 0; i<values.length;i++){
			model.addElement(values[i]);
		}
		return model;
	}
	
	private String wortAusschneiden(String eingabe){
		String zur�ck = "";
		int i = 0;
		while(i<eingabe.length() && !(eingabe.charAt(i)==' ')){
			zur�ck = zur�ck + eingabe.charAt(i);
			i++;
		}
		return zur�ck;
	
	}
	
	private void fensterSchlie�en(){
		this.setVisible(false);
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


