package lebensmittel;

import java.util.Calendar;

// Zum Verst�ndnis: Aufschnitt = Wurst, K�se in Scheiben; Aufstrich = Leberwurst, Fritschk�se (also streichbar)

public class Aufstrich extends Lebensmittel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private int LAGERUNG; //TODO Recherchieren Lagerung
	private double menge;
	
	public Aufstrich(String name, Calendar mhd, String typ, double menge){
		super(name, mhd, typ);
		this.menge = menge;
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

	public double getMenge() {
		return menge;
	}

	public void setMenge(double menge) {
		this.menge = menge;
	}
	
	/* 
	 * Debug
	 * */
	public void displayUI(){
		System.out.println("Name:" +"\t"+ this.getName());
		System.out.println("ID:" +"\t"+ this.getLebensmittelID());
		System.out.println("Anzahl:" +"\t"+ this.getAnzahl());
		System.out.println("Typ" +"\t"+ this.getTyp());
		
		
		System.out.println("Lagerungsempfehlung" +"\t"+ Aufstrich.getLAGERUNG()); // TODO: Lagerungsempfehlung
		System.out.println("Inhalt in Gramm:" +"\t"+ this.getMenge());
		
	}
	
	
	
	
}
