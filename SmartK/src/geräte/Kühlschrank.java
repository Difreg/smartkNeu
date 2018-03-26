package ger�te;

import java.io.Serializable;
import java.util.Calendar;


import listen.Bestandsliste;
import listen.Einkaufsliste;
import listen.Entnahmeliste;

public class K�hlschrank implements Serializable{
	
	/**
	 * Klasse K�hlschrank
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String standort;
	private double gr��e; // in Liter
	private int f�cher;
	private boolean gefrierfachVorhanden;
	private double temperatur;
	
	private Bestandsliste best�nde;
	private Entnahmeliste entnommen;
	private Einkaufsliste einkaufen;
	
	public K�hlschrank(){
		
	}
	
	public K�hlschrank(String name, String standort, double gr��e, int f�cher,boolean gefrierfach, double temperatur){
		
		this.name = name;
		this.standort = standort;
		this.gr��e = gr��e;
		this.f�cher = f�cher;
		this.gefrierfachVorhanden = gefrierfach;
		this.temperatur = temperatur;
		
		this.best�nde = new Bestandsliste();
		this.best�nde.initialisiereBestandsliste();
		this.best�nde.best�ndeSpeichern(this.getName()+"Bestandsliste.ser");
		this.entnommen = new Entnahmeliste();
		this.einkaufen = new Einkaufsliste();
	}
	
	// Methode zum Anzeigen der K�hlschrankattribute im UI-Modus (textlich)
	public void displayUI(){
		System.out.println("Name: " +"\t"+"\t" + this.name);
		System.out.println("Standort: " +"\t"+ this.standort);
		System.out.println("Gr��e " +"\t"+"\t" + this.gr��e);
		System.out.println("F�cher: "+"\t" + this.f�cher);
		System.out.println("Temp:" +"\t"+"\t" + this.temperatur);
	}
	
	
	
	// Methode, um Lebensmittel zu Bestandsliste hinzuzuf�gen
	public void newAufschnittHinzuf�gen(String name, Calendar mhd, String typ, double inhalt, int scheiben){
		this.best�nde.neuAufschnittHinzuf�gen( name, mhd, typ, inhalt, scheiben);
	}
	
	// Methoden zum Einf�gen der Lebensmittel
	public void vorhandenenAufschnittHinzuf�gen(String name, Calendar mhd){
		this.best�nde.vorhandenesLebensmittelHinzuf�gen(name, mhd);
	}
	
	// Lebensmittel l�schen
	public void lebensmittelEntfernen(String name){
		this.best�nde.lebensmittelEntfernen(name);
	}
	
	
	
	
	
	// Getter und Setter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStandort() {
		return standort;
	}

	public void setStandort(String standort) {
		this.standort = standort;
	}

	public double getGr��e() {
		return gr��e;
	}

	public void setGr��e(double gr��e) {
		this.gr��e = gr��e;
	}

	public int getF�cher() {
		return f�cher;
	}

	public void setF�cher(int f�cher) {
		this.f�cher = f�cher;
	}

	public boolean isGefrierfachVorhanden() {
		return gefrierfachVorhanden;
	}

	public void setGefrierfachVorhanden(boolean gefrierfachVorhanden) {
		this.gefrierfachVorhanden = gefrierfachVorhanden;
	}

	public double getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(double temperatur) {
		this.temperatur = temperatur;
	}

	public Bestandsliste getBest�nde() {
		return best�nde;
	}

	public void setBest�nde(Bestandsliste best�nde) {
		this.best�nde = best�nde;
	}

	public Entnahmeliste getEntnommen() {
		return entnommen;
	}

	public void setEntnommen(Entnahmeliste entnommen) {
		this.entnommen = entnommen;
	}

	public Einkaufsliste getEinkaufen() {
		return einkaufen;
	}

	public void setEinkaufen(Einkaufsliste einkaufen) {
		this.einkaufen = einkaufen;
	}
	
	
	
	

}
