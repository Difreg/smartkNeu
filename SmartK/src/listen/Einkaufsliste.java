package listen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.ListIterator;


import lebensmittel.Lebensmittel;

public class Einkaufsliste implements Serializable{
	
	/**
	 * Klasse zum Aufnehmen von Lebensmitteln, die auf der Einkaufsliste des K�hlschranks/ aller K�hlschr�nke vorhanden sind
	 */
	 
	private static final long serialVersionUID = 1L;
	private int anzahl;
	private ArrayList<Lebensmittel> einkaufsliste;
	
	public Einkaufsliste(){
		this.einkaufsliste = new ArrayList<Lebensmittel>();
		this.initialisiereEinkaufsliste();
		this.anzahl = 0;
	}
	
	// Hinzuf�gen eines Lebensmittels
	public void lebensmittelHinzuf�gen(Lebensmittel lebensmittel){
		
			String neu = lebensmittel.getName();
			int z�hler = 0;
			boolean gefunden = false;
			while(z�hler<this.getEinkaufsliste().size()){
				Lebensmittel pr�fung = this.getEinkaufsliste().get(z�hler);
				if(wortAusschneiden(pr�fung.getName()).equals(neu)){
					pr�fung.anzahlErh�hen();
					this.anzahl ++;
					gefunden = true;
				}
				z�hler++;
			}
			if(gefunden==false) {
				this.einkaufsliste.add(lebensmittel);
				this.anzahl ++;
			}
	}
	
	//Entfernen eines Lebensmittels
	public void lebensmittelEntfernen(int index){
		this.einkaufsliste.remove(index);
		this.anzahl--;
	}
	
	public void lebensmittelVermindern(Lebensmittel lebensmittel) {
		if(lebensmittel.getAnzahl() > 0) {
			lebensmittel.anzahlVermindern();
		}
	}
	
	// Anzahl eines Lebensmittels ver�ndern
	public void lebensmittelAnzahlVer�ndern(int index, int anzahl){
		this.einkaufsliste.get(index).setAnzahl(anzahl);
	}
	
	// R�ckgabe der Einkaufsliste; R�ckgabe nur, wenn Anzahl >1
	public String[] toSortedString(ArrayList<Lebensmittel> liste){
		if(this.getAnzahl() >0){
			String[] ausgabe = new String[this.getAnzahl()];
			ListIterator<Lebensmittel> it = liste.listIterator();
			int z�hler = 0;
			while(it.hasNext()){
				Lebensmittel pr�fung = it.next();
				if(pr�fung.getAnzahl()>0){
					ausgabe[z�hler] = pr�fung.toLittleString();
					z�hler ++;
				}
			}
			return ausgabe;
			
		}
		else{
			String[] ausgabe = new String[1];
			ausgabe[0] = "Der K�hlschrank ist leer.";
			return ausgabe;
		}
		
	}
	
	// Serialisierung
	
	/*
	 * Bestandsliste wird gef�llt, File wurde vorher von System geschrieben und gespeichert
	 * */
	
	@SuppressWarnings("unchecked")
	public void initialisiereEinkaufsliste(){
		try{
			FileInputStream fileIn = new FileInputStream("initBestandsliste.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			this.setEinkaufsliste((ArrayList<Lebensmittel>)objectIn.readObject());
			objectIn.close();
		
		}
		catch(IOException r){
			
		}
		catch(ClassNotFoundException e){
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void eink�ufeLaden(String dateiname){
		try{
			FileInputStream fileIn = new FileInputStream(dateiname);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			int anzahlNeu = objectIn.readInt();
			ArrayList<Lebensmittel> lebensmittelNeu =(ArrayList<Lebensmittel>) objectIn.readObject();
			this.setAnzahl(anzahlNeu);
			this.setEinkaufsliste(lebensmittelNeu);
			objectIn.close();	
		
		}
		catch(IOException r){
			r.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public void eink�ufeSpeichern(String dateiname){
		try{
			FileOutputStream fileOut = new FileOutputStream(dateiname);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeInt(this.getAnzahl());
			objectOut.writeObject(this.getEinkaufsliste());
			objectOut.close();
			
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		}
	}
	
	/*
	 * Getter und Setter
	 * */

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public ArrayList<Lebensmittel> getEinkaufsliste() {
		return einkaufsliste;
	}

	public void setEinkaufsliste(ArrayList<Lebensmittel> einkaufsliste) {
		this.einkaufsliste = einkaufsliste;
	}
	
	// Hilfsmethode zum Ausschneiden eines Lebensmittelnamen
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
