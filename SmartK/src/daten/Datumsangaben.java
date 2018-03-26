package daten;

import java.io.Serializable;
import java.util.Calendar;


public class  Datumsangaben implements Serializable{
	
	/**
	 * Sammlung der Datumsangaben �ffnung, Mindesthaltbarkeitsdatum, Eingelagert
	 */
	private static final long serialVersionUID = 1L;
	private Calendar eingelagert;
	private Calendar mhd;
	private Calendar �ffnung;
	
	public Datumsangaben(Calendar mhd){
		this.eingelagert = Calendar.getInstance();
		this.mhd = mhd;
	}


	public void �ffnen(){
		
		Calendar neu = Calendar.getInstance();
		neu.setTime(neu.getTime());
		
		this.�ffnung = neu;
	}
	
	//Getter und Setter

	public Calendar getEingelagert() {
		return eingelagert;
	}

	public void setEingelagert(Calendar eingelagert) {
		this.eingelagert = eingelagert;
	}

	public Calendar getMhd() {
		return mhd;
	}

	public void setMhd(Calendar mhd) {
		this.mhd = mhd;
	}

	public Calendar get�ffnung() {
		return �ffnung;
	}

	public void set�ffnung(Calendar �ffnung) {
		this.�ffnung = �ffnung;
	}
	
	
}
