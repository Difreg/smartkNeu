package ger�te;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import lebensmittel.Lebensmittel;
import listen.Einkaufsliste;
import listen.Standard_Bestandsliste_erstellen;

// Sammlung der K�hlschr�nke, falls mehrere Verwaltet werden sollen

public class Ger�te implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int anzahl;
	private ArrayList<K�hlschrank> k�hlschr�nke;
	private Einkaufsliste einkaufen;
	
	public Ger�te(){
		this.anzahl = 0;
		this.k�hlschr�nke = new ArrayList<K�hlschrank>();
		this.einkaufen = new Einkaufsliste();
		Standard_Bestandsliste_erstellen sbe = new Standard_Bestandsliste_erstellen(new ArrayList<Lebensmittel>());
		sbe.ausf�hren();
		
	}
	
	// Hinzuf�gen eines K�hlschranks
	
	public boolean k�hlschrankHinzuf�gen(String name, String standort, double gr��e, int f�cher, boolean gefrierfach, double temp){
		//this.k�hlschr�nke.add(new K�hlschrank(name, standort, gr��e, f�cher, temp));
		this.k�hlschr�nke.add(anzahl, new K�hlschrank(name, standort, gr��e, f�cher,gefrierfach ,temp));
		if(this.getAnzahl() == 0) {
			Standard_Bestandsliste_erstellen sbe = new Standard_Bestandsliste_erstellen(new ArrayList<Lebensmittel>());
			sbe.ausf�hren();
		}
		this.k�hlschr�nke.get(this.getAnzahl()).getBest�nde().best�ndeSpeichern(name+"Bestandsliste.ser");
		this.k�hlschr�nke.get(this.getAnzahl()).getEinkaufen().eink�ufeSpeichern(name+"Einkaufsliste.ser");
		this.anzahl++;
		return true;
	}
	
	// Hinzuf�gen eines K�hlschranks
	public boolean k�hlschrankHinzuf�gen(K�hlschrank neuerK�hlschrank){
		this.k�hlschr�nke.add(neuerK�hlschrank);
		if(this.getAnzahl() == 0) {
			Standard_Bestandsliste_erstellen sbe = new Standard_Bestandsliste_erstellen(new ArrayList<Lebensmittel>());
			sbe.ausf�hren();
		}
		this.k�hlschr�nke.get(this.getAnzahl()).getBest�nde().best�ndeSpeichern(this.k�hlschr�nke.get(anzahl).getName()+"Bestandsliste.ser");
		this.k�hlschr�nke.get(this.getAnzahl()).getEinkaufen().eink�ufeSpeichern(this.k�hlschr�nke.get(anzahl).getName()+"Einkaufsliste.ser");
		this.anzahl++;
		return true;
	}
	
	
	// Entfernen eines K�hlschranks, falls der Index vorhanden ist
	public boolean k�hlschrankEntfernen(int index){
		if(index <= this.k�hlschr�nke.size()){
			K�hlschrank k�hlschrank = this.k�hlschr�nke.get(index);
			String nameK�hlschrank = k�hlschrank.getName();
			delete(nameK�hlschrank + "Bestandsliste.ser");
			delete(nameK�hlschrank + "Einkaufsliste.ser");
			this.k�hlschr�nke.remove(index);
			this.anzahl--;
			this.ger�teSpeichern();
			return true;
		}
		else{
			return false;
		}
	}
	
	// TODO Implementieren
	public void k�hlschrankKonfigurieren(){
		
	}
	
	
	
	// Serialisierung
	
	public void ger�teSpeichern(){
		
		try{
			FileOutputStream fileOut = new FileOutputStream("K�hlschr�nke.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeInt(this.getAnzahl());
			objectOut.writeObject(this.getK�hlschr�nke());
			objectOut.writeObject(this.getEinkaufen());
			objectOut.close();
			
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		}
		
	}
	
	public void ger�teLaden(){
		try{
			FileInputStream fileIn = new FileInputStream("K�hlschr�nke.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			this.setAnzahl(objectIn.readInt());
			@SuppressWarnings("unchecked")
			ArrayList<K�hlschrank> k�hlschr�nke2 = (ArrayList<K�hlschrank>) objectIn.readObject();
			this.setK�hlschr�nke(k�hlschr�nke2);
			this.setEinkaufen((Einkaufsliste)objectIn.readObject());
			objectIn.close();	
		
		}
		catch(IOException r){
			r.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	// Hilfsklasse zum Zusammenf�hren der einzelnen Einkaufslisten
	
	public void einkaufslisteZusammentragen(){
		ArrayList <Lebensmittel> zs = new ArrayList<Lebensmittel>();
		ListIterator<K�hlschrank> it = this.getK�hlschr�nke().listIterator();
		while(it.hasNext()){
			K�hlschrank k�hlschrank = it.next();
			k�hlschrank.getEinkaufen().eink�ufeLaden(k�hlschrank.getName()+"Einkaufsliste.ser");
			Einkaufsliste liste = k�hlschrank.getEinkaufen();
			if(liste.getEinkaufsliste().size() >0) {
				ListIterator <Lebensmittel> itLeb = liste.getEinkaufsliste().listIterator();
				while(itLeb.hasNext()){
					zs.add(itLeb.next());
				}
			}
		}
		this.einkaufen.setEinkaufsliste(zs);
	}
	
	public void einkaufslisteZusammentragen2(){
		
		Einkaufsliste zusammengef�gteEinkaufsliste = new Einkaufsliste();
		ListIterator<K�hlschrank> it = this.getK�hlschr�nke().listIterator();
		while(it.hasNext()){
			K�hlschrank k�hlschrank = it.next();
			k�hlschrank.getEinkaufen().eink�ufeLaden(k�hlschrank.getName()+"Einkaufsliste.ser");
			Einkaufsliste liste = k�hlschrank.getEinkaufen();
			ListIterator <Lebensmittel> itLeb = liste.getEinkaufsliste().listIterator();
			
			while(itLeb.hasNext()){
				Lebensmittel pr�fung = itLeb.next();
				if(pr�fung.getAnzahl()>0) {
					for (int i = 0; i<pr�fung.getAnzahl();i++) {
						zusammengef�gteEinkaufsliste.lebensmittelHinzuf�gen(pr�fung);
					}
				}
			}
			
		}
		this.setEinkaufen(zusammengef�gteEinkaufsliste);
	}
	
	public static void delete(String l�schen){
		File l�schenFile = new File(l�schen);
			l�schenFile.delete();
	}
	
	/*
	 * Debug
	 * */
	
	// Auflistung der vorhandenen K�hlschr�nke auf Konsole
		public void displayGer�te(){
			Iterator<K�hlschrank> en = this.k�hlschr�nke.iterator();
			while(en.hasNext()) {
				en.next().displayUI();
			}
			System.out.println("---------------------------------------");
		}
	
	
	
	// Getter und Setter

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public ArrayList<K�hlschrank> getK�hlschr�nke() {
		return k�hlschr�nke;
	}

	public void setK�hlschr�nke(ArrayList<K�hlschrank> k�hlschr�nke) {
		this.k�hlschr�nke = k�hlschr�nke;
	}

	public Einkaufsliste getEinkaufen() {
		return einkaufen;
	}

	public void setEinkaufen(Einkaufsliste einkaufen) {
		this.einkaufen = einkaufen;
	}
	
	

}
