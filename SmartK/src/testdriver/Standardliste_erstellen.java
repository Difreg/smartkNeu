package testdriver;



import ger�te.Ger�te;


public class Standardliste_erstellen {
	
	/*
	 * Diese Klasse ist ein �berbleibsel aus der Prototypentwicklung
	 * */

	public static void main(String[] args) {
		
		
		Ger�te inventar = new Ger�te();
		inventar.k�hlschrankHinzuf�gen("Tommi", "Garage", 100, 6,true, 7.0);
		inventar.k�hlschrankHinzuf�gen("Olli", "Keller", 60.0, 5, false, 7.0);
	
		inventar.ger�teSpeichern();
		
		System.out.println("Fertig");

	}

}
