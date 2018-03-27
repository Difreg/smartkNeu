package testdriver;



import geräte.Geräte;
import geräte.Kühlschrank;


public class Testdriver {

	public static void main(String[] args){
		/*
		 * Nur eine Testklasse, keine Relevanz für Software
		 * */
		
		Geräte inventar = new Geräte();
		/*inventar.kühlschrankHinzufügen("Tommi", "Garage", 100, 6, 7.0);
		inventar.displayGeräte();
		Kühlschrank einKühlschrank = inventar.getKühlschränke().get(0);
		
		//long ID, String name, Date mhd, String typ, String[] ihst, int lagerung, double inhalt, int scheiben
		String[] a = new String[2];
		a[0] = "A";
		a[1] = "B";
		einKühlschrank.aufschnittHinzufügen(01, "Salami", new Date(), "Wurst", a, 1, 200, 10);
		einKühlschrank.getBestände().displayUI();
		einKühlschrank.lebensmittelEntfernen(01);
		einKühlschrank.getBestände().displayUI();
		inventar.geräteSpeichern();*/
		
		//inventar.geräteLaden();
	//	inventar.kühlschrankHinzufügen("Tommi", "Garage", 100, 6, 7.0);
		//inventar.displayGeräte();
		inventar.geräteLaden();
		Kühlschrank einKühlschrank = inventar.getKühlschränke().get(0);
		//String[] a = new String[2];
		//a[0] = "A";
		//a[1] = "B";
		//einKühlschrank.newAufschnittHinzufügen(01, "Salami", new Date(), "Wurst", a, 1, 200, 10);
		//einKühlschrank.getBestände().displayUI();
		//inventar.geräteSpeichern();
	//	einKühlschrank.vorhandenenAufschnittHinzufügen(01);
		
		
	//	einKühlschrank.vorhandenenAufschnittHinzufügen(01);
		einKühlschrank.getBestände().displayUI();
		
	}
	
}
