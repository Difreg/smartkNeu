package lebensmittel;

import java.util.Calendar;


public class Obst extends Lebensmittel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private int lagerung; // TODO Recherchieren
	private double inhalt;
		
	
	// Konstruktor Lebensmittel:
	// long ID, String name, Date mhd, String typ, String[] ihst, Date einlagerung, int lagerung
	
static private int LAGERUNG = 1; // TODO Lagerung recherchieren
	

	// Lebensmittel-Konstruktor:
	// long ID, String name, Date mhd, String typ, String[] ihst, Date einlagerung, int lagerung
	public Obst(String name, Calendar mhd, String typ, double inhalt){
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
	
	
	
	/**
	 * @return the lagerung
	 */
	public static int getLagerung() {
		return lagerung;
	}



	/**
	 * @param lagerung the lagerung to set
	 */
	public static void setLagerung(int lagerung) {
		Obst.lagerung = lagerung;
	}



	/**
	 * @return the inhalt
	 */
	public double getInhalt() {
		return inhalt;
	}



	/**
	 * @param inhalt the inhalt to set
	 */
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
	
		
	}
	
	
}
