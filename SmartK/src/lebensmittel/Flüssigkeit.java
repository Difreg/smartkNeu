package lebensmittel;

import java.util.Calendar;

public class Fl�ssigkeit extends Lebensmittel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private int LAGERUNG = 1; // TODO Lagerung recherchieren
	private double inhalt; // in ml
	
	
	// Lebensmittel-Konstruktor:
	// long ID, String name, Date mhd, String typ, String[] ihst, Date einlagerung, int lagerung
	public Fl�ssigkeit(String name, Calendar mhd, String typ, double inhalt){
		super(name, mhd, typ);
		this.inhalt = inhalt;
		
		
	}

	
	
	public String toLittleString(){
		String ausgabe = "";
		ausgabe = ausgabe.concat(this.getName() + " ");
		ausgabe = ausgabe.concat(this.getTyp() + " ");
		ausgabe = ausgabe.concat("(" + this.getAnzahl() + ")" + " ");
		return ausgabe;
	}

	

	public static int getLAGERUNG() {
		return LAGERUNG;
	}

	public static void setLAGERUNG(int lAGERUNG) {
		LAGERUNG = lAGERUNG;
	}


	public double getInhalt() {
		return inhalt;
	}


	public void setInhalt(double inhalt) {
		this.inhalt = inhalt;
	}

	/*
	 * Debug
	 * */
	public void displayUI(){
		System.out.println("Name:" +"\t"+ this.getName());
		System.out.println("ID:" +"\t"+ this.getLebensmittelID());
		System.out.println("Anzahl:" +"\t"+ this.getAnzahl());
		System.out.println("Typ" +"\t"+ this.getTyp());
		/*for(int i = 0; i<this.getAnzahl();i++){
		//	this.getDaten().indexOf(i).displayUI();
		}*/
		
		System.out.println("Lagerungsempfehlung" +"\t"+ Fl�ssigkeit.getLAGERUNG()); // TODO: Lagerungsempfehlung
		System.out.println("Inhalt in Gramm:" +"\t"+ this.getInhalt());
		
	}
	
	

}
