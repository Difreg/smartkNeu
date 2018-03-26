package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ger�te.Ger�te;

public class Prototyp {
	
	/*
	 * Prototyp-Klasse f�r Review in Vorlesung
	 * */

	//private Ger�te inventar;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Ger�te inventar = new Ger�te();
		inventar.ger�teLaden();
		boolean wdh;
		do{
			wdh = hauptmenu(in, inventar);
		}
		while(wdh);

	}
	
	public static boolean hauptmenu(BufferedReader in, Ger�te inventar) throws IOException{
		System.out.println("Willkommen. W�hlen Sie eine Option: ");
		System.out.println("K�hlschrank-Info (1)");
		System.out.println("Best�nde anzeigen (2)");
		System.out.println("Lebensmittel hinzuf�gen (3)");
		System.out.println("Lebensmittel entfernen (4)");
		int auswahl=0;
		try{
		auswahl = Integer.parseInt(in.readLine());
		}
		catch(IOException e){
			System.out.println("Fehler");
		}
		switch(auswahl){
		case 1: k�hlschrankInfo(inventar); return true;
		case 2: best�ndeInfo(inventar); return true;
		case 3: lebensmittelHinzuf�gen(inventar, in); return true;
		case 4: lebensmittelEntfernen(inventar, in); return true;
		default: System.out.println("Tsch�"); return false;
		}
		
		
	}
	
	
	public static void k�hlschrankInfo(Ger�te inventar){
		inventar.displayGer�te();
	}
	
	public static void best�ndeInfo(Ger�te inventar){
		inventar.getK�hlschr�nke().get(0).getBest�nde().displayUI();
	}
	
	public static void lebensmittelHinzuf�gen(Ger�te inventar, BufferedReader in) throws IOException{
		inventar.getK�hlschr�nke().get(0).getBest�nde().displayUI();;
		
		System.out.println("W�hlen Sie die ID des Lebensmittels, welches hinzugef�gt werden soll.");
	//	long einf�gen = Long.parseUnsignedLong(in.readLine());
	//	inventar.getK�hlschr�nke().get(0).vorhandenenAufschnittHinzuf�gen(einf�gen);
	}
	
	public static void lebensmittelEntfernen(Ger�te inventar, BufferedReader in) throws IOException{
		inventar.getK�hlschr�nke().get(0).getBest�nde().displayUI();
		System.out.println("W�hlen Sie die ID des Lebensmittels, welches entfernt werden soll.");
	//	long entfernen = Integer.parseInt(in.readLine());
	//	inventar.getK�hlschr�nke().get(0).lebensmittelEntfernen(entfernen);
	}

}
