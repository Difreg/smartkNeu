package listen;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import java.util.ListIterator;

import javax.swing.JOptionPane;

import comparator.DateComparator;
import daten.Datumsangaben;
import lebensmittel.Lebensmittel;

public class Entnahmeliste implements Serializable{
	
	/**
	 * Enth�lt alle Lebensmittel, die vorerst aus dem Bestand genommen wurden
	 * Verteilung auf die anderen Listen erfolgt hier
	 * Dient als Zwischenspeicher f�r den "Lebensmittelverteiler" (GUI-Klasse)
	 */
	private static final long serialVersionUID = 1L;
	private int anzahlEntnommen;
	private int anzahlLeer;
	private int anzahlEinkaufsliste;
	private ArrayList<Lebensmittel> entnahmeliste;
	private ArrayList<Lebensmittel> leereLebensmittel;
	private ArrayList<Lebensmittel> einkaufsliste;

	public Entnahmeliste(){
		this.anzahlEntnommen = 0;
		this.anzahlLeer = 0;
		this.anzahlEinkaufsliste = 0;
		this.entnahmeliste = new ArrayList<Lebensmittel>();
		this.leereLebensmittel = new ArrayList<Lebensmittel>();
		this.einkaufsliste = new ArrayList<Lebensmittel>();
	}
	
	
	// Lebensmittel zu Entnahmeliste hinzuf�gen hinzuf�gen
	public void lebensmittelHinzuf�gen(Lebensmittel lebensmittel){
		this.entnahmeliste.add(lebensmittel);
		this.anzahlEntnommen ++;
	}
	
	
	//Lebensmittel zu Liste leerer Lebensmittel hinzuf�gen
	public void leeresLebensmittelHinzuf�gen(Lebensmittel leeresLebensmittel){
		this.leereLebensmittel.add(leeresLebensmittel);
		this.anzahlLeer ++;
	}
	
	/*
	 * Keine Ahnung woher diese Methode kommt, muss eine �berlegung im fr�heren Stadium der Entwicklung gewesen sein
	 * Ich habe aber Angst, sie zu l�schen... 
	 * 		- Kommentar des Entwicklers
	 * */
	public Lebensmittel[] lebensmittelAbrufen(ArrayList<Lebensmittel> liste){
		ListIterator<Lebensmittel> it = liste.listIterator();
		Lebensmittel[] zs = new Lebensmittel[liste.size()];
		int i = 0;
		while(it.hasNext()){
			zs[i] = (Lebensmittel) it.next();
			i++;
		}
		return zs;
	}
	
	// Vielleicht l�schbar, dient als Hilfsklasse zur R�ckgabe der standardm��igen Entnahmeliste
	public String[] toLittleString(ArrayList<Lebensmittel> liste){
		String[] ausgabe = new String[this.getAnzahlEntnommen()];
		ListIterator <Lebensmittel>it = liste.listIterator();
		
		for(int i =0; i<liste.size();i++){
			Lebensmittel pr�fung = it.next();
			ausgabe[i] = pr�fung.getName();
			System.out.println(ausgabe[i]);
		}
		return ausgabe;
	}
	
	
	/**
	 * Verschieben der Lebensmittel innerhalb der Entnahmeliste -> Unterscheidung zwischen Zur�cklegen,
	 * Auf die Einkaufsliste schrieben und Aus Bestand entfernen.
	 * */
	public void lebensmittelVerschieben(ArrayList<Lebensmittel> quelle, ArrayList<Lebensmittel> ziel, String lebensmittel){
		
		// Wird nur ausgef�hrt, wenn Quelle und Ziel ungleich sind -> ungewollte Erh�hung der Anzahl der Lebensmittel vermeiden
		if(!(quelle==ziel)){
			// �berpr�fung, wie oft Lebensmittel in Quelle und Ziel sind (�ber Hilfsmethode siehe unten)
			int anzahlVorhandenQuelle = lebensmittelSuchen(quelle, lebensmittel);
			int anzahlVorhandenZiel = lebensmittelSuchen(ziel, lebensmittel);
			// Wenn das Lebensmittel 1 Mal in der Quell liste vorhanden ist
			if(anzahlVorhandenQuelle == 1){
				// Erzeugung des Lebensmittelobjekts �ber eine Hilfsmethode (siehe weiter unten im Code)
				Lebensmittel lebensmittelObjekt = lebensmittelErzeugen(quelle, lebensmittel);
				// Entfernen des Lebensmittels aus der Quelle, da Anzahl = 0;
				lebensmittelObjekt.anzahlVermindern();
				Datumsangaben entferntesDatum = findOldestDate(lebensmittelObjekt.getDaten());
				lebensmittelObjekt.getDaten().remove(entferntesDatum);
				quelle.remove(lebensmittelObjekt);
				// �berprufung, ob Lebensmittel in Ziel enthalten ist
				if(anzahlVorhandenZiel < 1){
					// Wenn nicht: Hinzuf�gen des Lebensmittels mit der Anzahl 1
				/*	Lebensmittel lebensmittelObjektDuplikat = lebensmittelObjekt.clone();
					lebensmittelObjektDuplikat.setAnzahl(1);
					lebensmittelObjektDuplikat.getDaten().add(entferntesDatum);
					ziel.add(lebensmittelObjektDuplikat); */
					
					lebensmittelObjekt.setAnzahl(1);
					lebensmittelObjekt.getDaten().add(entferntesDatum);
					ziel.add(lebensmittelObjekt);
					
				}
				else{
					// Wenn doch: Erh�hung der Anzahl des Lebensmittels nach Ermittlung des Lebensmittelobjekts
					Lebensmittel lebensmittelObjektZiel = lebensmittelErzeugen(ziel,lebensmittel);
					lebensmittelObjektZiel.getDaten().add(entferntesDatum);
					lebensmittelObjektZiel.setAnzahl(anzahlVorhandenZiel+1);
				}
			}
			// Wenn das Lebensmittel mehrmals in der Quellliste liegt:
			else if (anzahlVorhandenQuelle >1){
				// Ermittlung des Objektes in der Quellliste und Vermiderung der Anzahl
				Lebensmittel lebensmittelObjekt = lebensmittelErzeugen(quelle, lebensmittel);
				Datumsangaben entferntesDatum = findOldestDate(lebensmittelObjekt.getDaten());
				lebensmittelObjekt.getDaten().remove(entferntesDatum);
				lebensmittelObjekt.anzahlVermindern();
				// Wenn Lebensmittel in Zielliste noch nicht vorhanden
				if(anzahlVorhandenZiel<1){
					// Duplizieren des Quellobjektes, da 2 unterschiedliche Anzahlen ben�tigt werden
					Lebensmittel lebensmittelObjektDuplikat = lebensmittelObjekt.clone();
					// Einf�gen des duplizierten Objektes in die neue Liste mit der ANzhal 1
				//	lebensmittelObjektDuplikat.getDaten().add(entferntesDatum);
					lebensmittelObjektDuplikat.setAnzahl(1);
					ziel.add(lebensmittelObjektDuplikat);
				}
				// Wenn Lebensmittel bereits vorhanden
				else{
					// Ermittlung des Zielobjektes �ber die o.g. Hilfsmethode und Erh�hung der Anzahl um 1
					Lebensmittel lebensmittelObjektZiel = lebensmittelErzeugen(ziel, lebensmittel);
					lebensmittelObjektZiel.getDaten().add(entferntesDatum);
					lebensmittelObjektZiel.setAnzahl(anzahlVorhandenZiel+1);
				}
			}
			else{
				// Fehlermeldung, falls das Lebensmittel nicht in der Quellliste gefunden werden kann
				JOptionPane.showMessageDialog(null, "Das Lebensmittel konnte nicht Gefunden werden.");
			}
		}
		
		
	}
	
	public void einkaufsliste�bertragen(ArrayList<Lebensmittel> quelle, ArrayList<Lebensmittel> ziel){
		
		
		for(int i = 0; i<quelle.size(); i++) {
			Lebensmittel pr�fung = quelle.get(i);
			for(int a = 0; a<pr�fung.getAnzahl();a++){
				lebensmittelVerschieben(quelle, ziel, wortAusschneiden(pr�fung.getName()));
				this.leereLebensmittel.add(pr�fung);
			}
			
		}
		
		
	}
	
	// Methode zum Aktualisieren der Best�nde (Ausf�hren der �nderungen durch Verschieben der Lebensmittel)
	public void best�ndeAktualisieren (ArrayList<Lebensmittel> zur�cklegen,ArrayList<Lebensmittel> entfernen , ArrayList<Lebensmittel> einkaufen,ArrayList<Lebensmittel> bestand){
		ListIterator <Lebensmittel> itBestand = bestand.listIterator();
		while(itBestand.hasNext()) {
			Lebensmittel pr�fung = itBestand.next();
			String namePr�fung = wortAusschneiden(pr�fung.getName());
			int anzahlZur�ck = lebensmittelSuchen(zur�cklegen, namePr�fung);
			int anzahlEntfernen = lebensmittelSuchen(entfernen, namePr�fung);
			int anzahlEinkaufen = lebensmittelSuchen(einkaufen, namePr�fung);
			int anzahlVorhanden = lebensmittelSuchen(bestand, namePr�fung);
			
			if(anzahlVorhanden > 0) {	
				if(anzahlZur�ck > 0) {
					pr�fung.setAnzahl(anzahlZur�ck);
				}
				else {
					if(anzahlEntfernen > 0) {
						pr�fung.setAnzahl(0);
					}
					else {
						if(anzahlEinkaufen > 0) {
							pr�fung.setAnzahl(0);
						}
					}
				}
			}
		}
	}
	
	// Leeren der Listen, dienten vorher als Zwischenspeicher
	public void listenLeeren(){
		for(int i = 0; i<this.getEinkaufsliste().size();i++){
			this.getEinkaufsliste().remove(i);
		}
		
		for(int i = 0; i<this.getEntnahmeliste().size();i++){
			this.getEntnahmeliste().remove(i);
		}
		
		for(int i = 0; i<this.getLeereLebensmittel().size();i++){
			this.getLeereLebensmittel().remove(i);
		}
	}
	
	// R�ckgabe der Lebensmittel mit Anzahl > 1
	public String[] toSortedString(ArrayList<Lebensmittel> liste){
		String[] ausgabe = new String[1+liste.size()];
		if(liste.size() >0){
			ListIterator<Lebensmittel> it = liste.listIterator();
			for (int i = 0; i<liste.size(); i++){
				Lebensmittel pr�fung = it.next();
				if(pr�fung.getAnzahl()>0){
					ausgabe[i] = pr�fung.toLittleString();
				}
			}
			
		}
		else{
			
			ausgabe[0] = "Die Liste ist leer.";
		}
		return ausgabe;
	}
	
	/** 
	 * Hilfsmethode f�r die Ermittlung der Anzahl der Lebensmittel in einer Liste
	 * **/
	
	private int lebensmittelSuchen(ArrayList<Lebensmittel> liste, String lebensmittel){
		ListIterator<Lebensmittel> itListe = liste.listIterator();
		while(itListe.hasNext()){
			Lebensmittel pr�fung = itListe.next();
			if(wortAusschneiden(pr�fung.getName()).equals(lebensmittel)){
				return pr�fung.getAnzahl();
			}
		}
		return 0;
	}
	
	/**
	 * Hilsmethode zum finden des Lebensmittelobjektes in einer Liste
	 *  **/
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
	
	private Datumsangaben findOldestDate(ArrayList<Datumsangaben> daten){
		
		Collections.sort(daten, new DateComparator());
		
		return daten.get(0);
	}
	
	
	/*
	 * Getter und Setter
	 * */

	

	public int getAnzahlEntnommen() {
		return anzahlEntnommen;
	}

	public void setAnzahlEntnommen(int anzahlEntnommen) {
		this.anzahlEntnommen = anzahlEntnommen;
	}

	public int getAnzahlLeer() {
		return anzahlLeer;
	}

	public void setAnzahlLeer(int anzahlLeer) {
		this.anzahlLeer = anzahlLeer;
	}

	public ArrayList<Lebensmittel> getLeereLebensmittel() {
		return leereLebensmittel;
	}

	public void setLeereLebensmittel(ArrayList<Lebensmittel> leereLebensmittel) {
		this.leereLebensmittel = leereLebensmittel;
	}

	public ArrayList<Lebensmittel> getEntnahmeliste() {
		return entnahmeliste;
	}

	public void setEntnahmeliste(ArrayList<Lebensmittel> entnahmeliste) {
		this.entnahmeliste = entnahmeliste;
	}

	public int getAnzahlEinkaufsliste() {
		return anzahlEinkaufsliste;
	}

	public void setAnzahlEinkaufsliste(int anzahlEinkaufsliste) {
		this.anzahlEinkaufsliste = anzahlEinkaufsliste;
	}

	public ArrayList<Lebensmittel> getEinkaufsliste() {
		return einkaufsliste;
	}

	public void setEinkaufsliste(ArrayList<Lebensmittel> einkaufsliste) {
		this.einkaufsliste = einkaufsliste;
	}
	
	private String wortAusschneiden(String eingabe){
		String zur�ck = "";
		int i = 0;
		while(i<eingabe.length()&& !(eingabe.charAt(i)==' ')){
			zur�ck = zur�ck + eingabe.charAt(i);
			i++;
		}
		return zur�ck;
	
	}
	
	
	
	
}
