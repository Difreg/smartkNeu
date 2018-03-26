package listen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import java.util.ListIterator;

import comparator.IDComperator;
import daten.Datumsangaben;

import lebensmittel.Aufschnitt;
import lebensmittel.Lebensmittel;

public class Bestandsliste implements Serializable{
	
	/**
	 * Klasse zum Aufnehmen von lebensmitteln, die im Bestand des K�hlschranks vorhanden sind
	 */
	private static final long serialVersionUID = 1L;
	private int anzahl;
	private ArrayList<Lebensmittel> best�nde;
	private ListIterator<Lebensmittel> iterator;
	
	public Bestandsliste(){
		this.anzahl = 0;
		this.best�nde = new ArrayList<Lebensmittel>();
		this.initialisiereBestandsliste();
	}
	
	
	
	//Konstruktor: long ID, String name, Date mhd, String typ, int lagerung, double inhalt, int scheiben
	
	/*
	 * Methode zum Hinzuf�gen eines neuen Lebensmittels, momentan nicht durch User nutzbar
	 * */
	public void neuAufschnittHinzuf�gen(String name, Calendar mhd, String typ, double inhalt, int scheiben){
		this.best�nde.add(new Aufschnitt(name, mhd, typ, inhalt, scheiben));
		this.anzahl++;
	}
	
	/*
	 * Methode zum Hinzuf�gen eines in der Bestandsliste vorhandenen Lebensmittels (Suche �ber Namen)
	 * */
	public void vorhandenesLebensmittelHinzuf�gen(String neu, Calendar mhd){
		this.iterator = this.getBest�nde().listIterator();
		while(this.iterator.hasNext()){
			Lebensmittel pr�fung = (Lebensmittel) this.iterator.next();
			if(wortAusschneiden(pr�fung.getName()).equals(neu)){
				if(pr�fung.getAnzahl() == 0){
					pr�fung.anzahlErh�hen();
					pr�fung.getDaten().remove(0);
					pr�fung.getDaten().add(new Datumsangaben(mhd));
				}
				else{
					pr�fung.anzahlErh�hen();
					pr�fung.getDaten().add(new Datumsangaben(mhd));
				}
				this.anzahl ++;
			}
		}
	}
	
	
	// Anzahl des Lebensmittels wird dekrementiert
	public void lebensmittelEntfernen(String name){
		this.iterator = this.best�nde.listIterator();
		while(this.iterator.hasNext()){
			Lebensmittel pr�fung = (Lebensmittel)this.iterator.next();
			if(pr�fung.getName().equals(name)){
				pr�fung.anzahlVermindern();
				this.anzahl --;
			}
		}
	}
	
	// Lebensmittel mit Anzahl = 0 werden nicht aufgelistet
	public void displayUI(){
		if(this.anzahl == 0){
			System.out.println("Der K�hlschrank enth�lt keine Lebensmittel.");
		}
		else{
			Collections.sort(this.best�nde, new IDComperator());
			this.iterator = this.best�nde.listIterator();
			while(this.iterator.hasNext()){
				Lebensmittel pr�fung = (Lebensmittel)this.iterator.next();
				if(pr�fung.getAnzahl()>0){
				pr�fung.displayUI();
				}
			}
		}
	}
	
	
	/*
	 * Bestandsliste wird gef�llt, File wurde vorher von System geschrieben und gespeichert
	 * */
	
	@SuppressWarnings("unchecked")
	public void initialisiereBestandsliste(){
		try{
			FileInputStream fileIn = new FileInputStream("initBestandsliste.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			this.setBest�nde((ArrayList<Lebensmittel>)objectIn.readObject());
			objectIn.close();
		
		}
		catch(IOException r){
			
		}
		catch(ClassNotFoundException e){
			
		}
	}
	
	/*
	 * Laden einer beliebigen Bestandsliste
	 * */
	
	@SuppressWarnings("unchecked")
	public void best�ndeLaden(String dateiname){
		try{
			FileInputStream fileIn = new FileInputStream(dateiname);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			int anzahlNeu = objectIn.readInt();
			ArrayList<Lebensmittel> lebensmittelNeu =(ArrayList<Lebensmittel>) objectIn.readObject();
			this.setAnzahl(anzahlNeu);
			this.setBest�nde(lebensmittelNeu);
			objectIn.close();	
		
		}
		catch(IOException r){
			r.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public void best�ndeSpeichern(String dateiname){
		try{
			FileOutputStream fileOut = new FileOutputStream(dateiname);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeInt(this.getAnzahl());
			objectOut.writeObject(this.getBest�nde());
			objectOut.close();
			
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		}
	}



	
	// Zur R�ckgabe der Standardm��igen Bestandsliste
	public String[] toListedString(){
		
		String[] ausgabe = new String[this.getBest�nde().size()];
		
			ListIterator<Lebensmittel> it = this.getBest�nde().listIterator();
			for (int i = 0; i<this.getBest�nde().size(); i++){
				Lebensmittel pr�fung = it.next();
					ausgabe[i] = pr�fung.toLittleString();
			}
			
		
		
		return ausgabe;
	}
	
	// Zur R�ckgabe der Bestandsliste, R�ckgabe nur bei vorhandenen Lebensmitteln
	public String[] toSortedString(){
		if(this.getAnzahl() >0){
			String[] ausgabe = new String[this.getAnzahl()];
			ListIterator<Lebensmittel> it = this.getBest�nde().listIterator();
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
	
	/*
	 * Getter und Setter
	 * */
		
		public int getAnzahl() {
			return anzahl;
		}



		public void setAnzahl(int anzahl) {
			this.anzahl = anzahl;
		}



		public ArrayList<Lebensmittel> getBest�nde() {
	 		return best�nde;
		}



		public void setBest�nde(ArrayList<Lebensmittel> best�nde) {
			this.best�nde = best�nde;
		}
	

}
