package listen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;

import lebensmittel.Aufschnitt;
import lebensmittel.Aufstrich;
import lebensmittel.Desserts;
import lebensmittel.Ei;
import lebensmittel.Fisch;
import lebensmittel.Fl�ssigkeit;
import lebensmittel.Gem�se;
import lebensmittel.Lebensmittel;
import lebensmittel.Lebensmittel_am_St�ck;
import lebensmittel.Obst;
import lebensmittel.Pilze;

public class Standard_Bestandsliste_erstellen {

	/* Diese Klasse dient zum Erstellen einer Standardm��igen Bestandsliste, wobei alle Lebensmittel die Anzahl 0 bekommen.
	 * Dadurch wird ein Einlagermn der Lebensmittel erleichtert und beschleunigt.
	 */
	
	private ArrayList<Lebensmittel> leereBestandsliste;
	private static Calendar fiktivesMHD;
	private static String wurstTyp = "Wurst";
	private static String k�seTyp = "K�se";
	private static String milchprodukt = "Milchprodukt";
	private static String pilz = "Speisepilz";
	private static String pflanzlich = "Obst/ Gem�se";
	private static String tierprodukt = "Tierprodukt";
	
	
	public Standard_Bestandsliste_erstellen(ArrayList<Lebensmittel> bestand){
		this.leereBestandsliste = bestand;
	}
	
	public void ausf�hren() {
		
		aufschnitteErstellen(this.leereBestandsliste);
		aufstricheErstellen(this.leereBestandsliste);
		dessertsErstellen(this.leereBestandsliste);
		eiErstellen(this.leereBestandsliste);
		fischErstellen(this.leereBestandsliste);
		fl�ssigkeitErstellen(this.leereBestandsliste);
		gem�seErstellen(this.leereBestandsliste);
		lebensmittel_Am_St�ck_Erstellen(this.leereBestandsliste);
		obstErstellen(this.leereBestandsliste);
		pilzeErstellen(this.leereBestandsliste);
		
		ListIterator<Lebensmittel> it4 = this.leereBestandsliste.listIterator();
	     while(it4.hasNext()){
	            it4.next().setAnzahl(0);
	     }
	     
		this.standardErstellen();
	}
	
	
	
	
	// Konstruktor Aufschnitt: long ID, String name, Calendar mhd, String typ, double inhalt, int scheiben
	public static void aufschnitteErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] wurst = new String[41];
		wurst[0] = "Bierschinken";
		wurst[1] = "Bierwurst";
		wurst[2] = "Blutwurst";
		wurst[3] = "Bockwurst";
		wurst[4] = "Bratenaufschnitt";
		wurst[5] = "Cervelatwurst";
		wurst[6] = "Chorizo";
		wurst[7] = "Corned Beef";
		wurst[8] = "Eselssalami";
		wurst[9] = "Gefl�gelbierschinken";
		wurst[10] = "Hirschsalami";
		wurst[11] = "Kassler-Aufschnitt";
		wurst[12] = "Kochschinken";
		wurst[13] = "Lachsschinken";
		wurst[14] = "Leberk�se";
		wurst[15] = "Mortadella";
		wurst[16] = "Nusschinken";
		wurst[17] = "Paprika-Lyoner";
		wurst[18] = "Paprika-Salami";
		wurst[19] = "Parmaschinken";
		wurst[20] = "Pfeffersalami";
		wurst[21] = "Pferdesalami";
		wurst[22] = "Pizza-Wurst";
		wurst[23] = "Plockwurst";
		wurst[24] = "Praga-Schinken";
		wurst[25] = "Puten-Lyoner";
		wurst[26] = "Putensalami";
		wurst[27] = "Putenschinken";
		wurst[28] = "R�ucherspeck";
		wurst[29] = "Rinderschinken";
		wurst[30] = "Rindssalami";
		wurst[31] = "Rindswurst";
		wurst[32] = "Salami";
		wurst[33] = "Schinkenwurst";
		wurst[34] = "Schweineschinken, ger�uchert";
		wurst[35] = "Seranoschinken";
		wurst[36] = "Speck";
		wurst[37] = "Sucuk";
		wurst[38] = "Ungarische Salami";
		wurst[39] = "Wildschwein-Salami";
		wurst[40] = "Wildschwein-Schinken";
		
		for(int i = 0; i<wurst.length; i++){
			bestandsliste.add(new Aufschnitt(wurst[i], fiktivesMHD, wurstTyp, 200, 10));		
		}
		
		
		
		String[] k�se = new String[50];
		
		k�se[0] = "Gouda";
        k�se[1] = "Maasdamer";
        k�se[2] = "Tilsiter";
        k�se[3] = "Schafsk�se";
        k�se[4] = "Mozzarella";
        k�se[5] = "Harzer_K�se";
        k�se[6] = "Parmesan";
        k�se[7] = "Cheddar";
        k�se[8] = "Bergk�se";
        k�se[9] = "Camenbert";
        k�se[10] = "Gorgonzola";
        k�se[11] = "Edamer";
        k�se[12] = "Ziegenk�se";
        k�se[13] = "Appenzeller";
        k�se[14] = "Etorki";
        k�se[15] = "Fol_Epi";
        k�se[16] = "Leerdamer";
        k�se[17] = "Emmentaler";
        k�se[18] = "Gruy�re";
        k�se[19] = "Comt�";
        k�se[20] = "Pecorino";
        k�se[21] = "Provolone";
        k�se[22] = "Roquefort";
        k�se[23] = "Butterk�se";
        k�se[24] = "Saint_Albray_Klosterk�se";
        k�se[25] = "Esrom";
        k�se[26] = "Tomme_de_Savoie";
        k�se[27] = "Chavroux";
        k�se[28] = "Brie";
        k�se[29] = "G�ramont";
        k�se[30] = "Limburger";
        k�se[31] = "Chaumes";
        k�se[32] = "Romadur";
        k�se[33] = "Old_Amsterdam";
        k�se[34] = "Saint Agur";
        k�se[35] = "Bavaria blu";
        k�se[36] = "Kl�tzer Gold";
        k�se[37] = "Obatzda";
        k�se[38] = "Beaufort";
        k�se[39] = "Morbier";
        k�se[40] = "Scamorza";
        k�se[41] = "Dubliner";
        k�se[42] = "Orla";
        k�se[43] = "Stelvio";
        k�se[44] = "Langres";
        k�se[45] = "Allg�uer_Bergk�se";
        k�se[46] = "B�rdespeck";
        k�se[47] = "Nieheimer_K�se";
        k�se[48] = "Wei�lacker";
        k�se[49] = "Feta";
        
        for(int i = 0; i<k�se.length; i++){
               bestandsliste.add(new Aufschnitt(k�se[i], fiktivesMHD, k�seTyp, 200, 10));          
        }
        

		
	}
	
	private static void aufstricheErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] schmierwurst = new String[8];
        schmierwurst[0] = "Teewurst-Fein";
        schmierwurst[1] = "Teewurst-Grob";
        schmierwurst[2] = "Leberwurst-Fein";
        schmierwurst[3] = "Leberwurst-Grob";
        schmierwurst[4] = "Leberwurst-Kr�uter";
        schmierwurst[5] = "Mettwurst";
        schmierwurst[6] = "Zwiebelmettwurst";
        schmierwurst[7] = "Hackepeter";
        
        for(int i = 0; i<schmierwurst.length; i++){
               bestandsliste.add(new Aufstrich(schmierwurst[i], fiktivesMHD, wurstTyp, 200.0));             
        }
        
        
        String[] schmierk�se = new String[11];
        schmierk�se[0] = "Frischk�se-Kr�uter";
        schmierk�se[1] = "Frischk�se-Knoblauch";
        schmierk�se[2] = "Frischk�se-Chili";
        schmierk�se[3] = "Frischk�se-Meerrettich";
        schmierk�se[4] = "Frischk�se-Lachs";
        schmierk�se[5] = "Frischk�se-Sahne";
        schmierk�se[6] = "Frischk�se-Natur";
        schmierk�se[7] = "Ricotta";
        schmierk�se[8] = "Mascarpone";
        schmierk�se[9] = "Frischk�se-Tomate";
        schmierk�se[10] = "Queso_de_Burgos";
        
        for(int i = 0; i<schmierk�se.length; i++){
               bestandsliste.add(new Aufstrich(schmierk�se[i], fiktivesMHD, k�seTyp, 200.0));             
        }
       
        
        String[] marmelade = new String[10];
        marmelade[0] = "Erdbeermarmelade";
        marmelade[1] = "Himbeermarmelade";
        marmelade[2] = "Beerenmixmarmelade";
        marmelade[3] = "Pflaumenmus";
        marmelade[4] = "Quittengelee";
        marmelade[5] = "Heidelbeermarmelade";
        marmelade[6] = "Kirschmarmelade";
        marmelade[7] = "Mangomarmelade";
        marmelade[8] = "Holunderbl�tengelee";
        marmelade[9] = "Apfelgelee";
        
        for(int i = 0; i<marmelade.length; i++){
               bestandsliste.add(new Aufstrich(marmelade[i], fiktivesMHD, "pflanzlich", 200));             
        }
        
        

        
        String[] weitereAufstriche = new String[5];
        weitereAufstriche[0] = "Nuss-Nougat-Creme";
        weitereAufstriche[1] = "Butter";
        weitereAufstriche[2] = "Pesto-Basilikum";
        weitereAufstriche[3] = "Pesto-Tomate";
        weitereAufstriche[4] = "Pesto-Rucola K�se";
        
        for(int i = 0; i<weitereAufstriche.length; i++){
            bestandsliste.add(new Aufstrich(weitereAufstriche[i], fiktivesMHD, milchprodukt, 200));             
	     }
	    
        
        
        String[] pflanzlicheAufstriche = new String[8];
        pflanzlicheAufstriche[0] = "Humus";
        pflanzlicheAufstriche[1] = "Nuss-Nougat-Creme";
        pflanzlicheAufstriche[4] ="Hummus-Natur";
        pflanzlicheAufstriche[2] ="Hummus-Kr�uter";
        pflanzlicheAufstriche[3] ="Hummus-Pikant";
        pflanzlicheAufstriche[5] ="Guacamole";
        pflanzlicheAufstriche[6] ="Pesto-Basilikum";
        pflanzlicheAufstriche[7] ="Pesto-Rucola K�se";
        
        for(int i = 0; i<pflanzlicheAufstriche.length; i++){
            bestandsliste.add(new Aufstrich(pflanzlicheAufstriche[i], fiktivesMHD, "pflanzlich", 200));             
	     }
	     

	}
    
	private static void dessertsErstellen(ArrayList<Lebensmittel> bestandsliste){
        String[] dessert = new String[38];
        dessert[0] = "Schokopudding";
        dessert[1] = "Vanillepudding";
        dessert[2] = "Cappucchinopudding";
        dessert[3] = "Joghurt-Pfirsich Maracuja";
        dessert[4] = "Joghurt-Apfel";
        dessert[5] = "Joghurt-Aprikose";
        dessert[6] = "Joghurt-Ananas";
        dessert[7] = "Joghurt-Erdbeer";
        dessert[8] = "Joghurt-Russischer Zupfkuchen";
        dessert[9] = "Joghurt-Amarena-Kirsch";
        dessert[10] = "Joghurt-Schoko";
        dessert[11] = "Joghurt-Waldbeeren";
        dessert[12] = "Joghurt-Aprikose";
        dessert[13] = "Joghurt-Pfirsich";
        dessert[14] = "Joghurt-Apfel-Haferflocken";
        dessert[15] = "Joghurt-Mango-Orange";
        dessert[16] = "Joghurt-Haselnuss";
        dessert[17] = "Joghurt-Orange";
        dessert[18] = "Joghurt-Stracciatella";
        dessert[19] = "Joghurt-Rhabarber-Vanille";
        dessert[20] = "Joghurt-Vanille";
        dessert[21] = "Joghurt-Banane";
        dessert[22] = "Joghurt-Himbeer";
        dessert[23] = "Joghurt-Birne";
        dessert[24] = "Joghurt-Kirsch";
        dessert[25] = "Joghurt-Limette";
        dessert[26] = "Joghurt-Heidelbeer";
        dessert[27] = "Joghurt-Zitrone";
        dessert[28] = "Joghurt-Obstsalat";
        dessert[29] = "Joghurt-Kirsch-Banane";
        dessert[30] = "Joghurt-Kiwi-Stachelbeere";
        dessert[31] = "Joghurt-Apfel-Birne";
        dessert[32] = "Schleckerschnee-Zitrone";
        dessert[33] = "Schleckerschnee-Himbeere";
        dessert[34] = "Mousse_au_chocolat-Vollmilch";
        dessert[35] = "Mousse_au_chocolat-Zartbitter";
        dessert[36] = "Mousse_au_chocolat-Wei�e Schokolade";
        dessert[37] = "Creme_brulee";
        
        for(int i = 0; i<dessert.length; i++){
               bestandsliste.add(new Desserts(dessert[i], fiktivesMHD, milchprodukt, 200));             
        }
       
        
        /*Vegane Desserts*/
        for(int i = 0; i<dessert.length; i++){
            bestandsliste.add(new Aufschnitt(dessert[i], fiktivesMHD, pflanzlich, 200, 10));             
	     }
	     
	     

	}
	
	private static void eiErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] ei = new String[5];
        ei[0] = "H�hnereier";
        ei[1] = "Strau�enei";
        ei[2] = "Wachtelei";
        ei[3] = "G�nseei";
        ei[4] = "Fasan-/Rebhuhnei";

		
		for(int i = 0; i<ei.length; i++){
            bestandsliste.add(new Ei(ei[i], fiktivesMHD, tierprodukt, 10));             
	     }
		
	}
	
	private static void fischErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] fisch = new String[20];
        fisch[0] = "Lachs";
        fisch[1] = "Heilbutt";
        fisch[2] = "Forelle";
        fisch[3] = "Hering";
        fisch[4] = "Seelachs";
        fisch[5] = "Hecht";
        fisch[6] = "Dorsch";
        fisch[7] = "Makrele";
        fisch[8] = "Zander";
        fisch[9] = "Sardine";
        fisch[10] = "Thunfisch";
        fisch[11] = "Pangasius";
        fisch[12] = "Seelachs";
        fisch[13] = "Scholle";
        fisch[14] = "Rotbarsch";
        fisch[15] = "Karpfen";
        fisch[16] = "Sprotten";
        fisch[17] = "Garnelen";
        fisch[18] = "Muscheln";
        fisch[19] = "Seeteufel";
        
        for(int i = 0; i<fisch.length; i++){
            bestandsliste.add(new Fisch(fisch[i], fiktivesMHD, "Fisch", 100));             
	     }
		
	}
	
	private static void fl�ssigkeitErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] fl�ssigkeit = new String[39];
        fl�ssigkeit[0] = "Wei�bier";
        fl�ssigkeit[1] = "Cola";
        fl�ssigkeit[2] = "Fanta";
        fl�ssigkeit[3] = "Sprite";
        fl�ssigkeit[4] = "Apfelschorle";
        fl�ssigkeit[5] = "Mate";
        fl�ssigkeit[6] = "Sprudelwasser";
        fl�ssigkeit[7] = "Wasser";
        fl�ssigkeit[8] = "Apfelsaft";
        fl�ssigkeit[9] = "Orangensaft";
        fl�ssigkeit[10] = "ACE-Saft";
        fl�ssigkeit[11] = "Multivitaminsaft";
        fl�ssigkeit[12] = "Johannisbeersaft";
        fl�ssigkeit[13] = "Kiba";
        fl�ssigkeit[14] = "Bananensaft";
        fl�ssigkeit[15] = "Pfirsichsaft";
        fl�ssigkeit[16] = "Kirschsaft";
        fl�ssigkeit[17] = "Maracujasaft";
        fl�ssigkeit[18] = "Tomatensaft";
        fl�ssigkeit[19] = "Birnensaft";
        fl�ssigkeit[20] = "Himbeersaft";
        fl�ssigkeit[21] = "Brombeersaft";
        fl�ssigkeit[22] = "Heidelbeersaft";
        fl�ssigkeit[23] = "Schwarzbier";
        fl�ssigkeit[24] = "Granatapfelsaft";
        fl�ssigkeit[25] = "Erdbeersaft";
        fl�ssigkeit[26] = "Ananassaft";
        fl�ssigkeit[27] = "Grapefruitsaft";
        fl�ssigkeit[28] = "Cranberrysaft";
        fl�ssigkeit[29] = "Rotwein";
        fl�ssigkeit[30] = "Wei�wein";
        fl�ssigkeit[31] = "Sekt";
        fl�ssigkeit[32] = "Prosecco";
        fl�ssigkeit[33] = "Champagner";
        fl�ssigkeit[34] = "Cider";
        fl�ssigkeit[35] = "Ketchup";
        fl�ssigkeit[36] = "Sweetchiliso�e";
        fl�ssigkeit[37] = "Knoblauchso�e";
        fl�ssigkeit[38] = "Barbecueso�e";
        
        for(int i = 0; i<fl�ssigkeit.length; i++){
            bestandsliste.add(new Fl�ssigkeit(fl�ssigkeit[i], fiktivesMHD, "Fl�ssigkeit", 100));             
	     }
		

	}
	
	private static void gem�seErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] gem�se = new String[43];
        gem�se[0] = "Avocado";
        gem�se[1] = "Eisbergsalat";
        gem�se[2] = "Feldsalat";
        gem�se[3] = "Chinakohl";
        gem�se[4] = "Chicor�e";
        gem�se[5] = "Rucola";
        gem�se[6] = "Gurke";
        gem�se[7] = "Tomate";
        gem�se[8] = "Paprika";
        gem�se[9] = "Zucchini";
        gem�se[10] = "Spinat";
        gem�se[11] = "Zuckerschoten";
        gem�se[12] = "Erbsen";
        gem�se[13] = "Zwiebeln";
        gem�se[14] = "Kartoffeln";
        gem�se[15] = "Knoblauch";
        gem�se[16] = "Brokkoli";
        gem�se[17] = "Spargel-gr�n";
        gem�se[18] = "Spargel-wei�";
        gem�se[19] = "Schmorgurken";
        gem�se[20] = "Radieschen";
        gem�se[21] = "Peperoni";
        gem�se[22] = "Oliven";
        gem�se[23] = "Fr�hlingszwiebeln";
        gem�se[24] = "Mangold";
        gem�se[25] = "Schalotten";
        gem�se[26] = "Staudensellerie";
        gem�se[27] = "Fenchel";
        gem�se[28] = "Rettich";
        gem�se[29] = "S��kartoffeln";
        gem�se[30] = "M�hre";
        gem�se[31] = "Aubergine";
        gem�se[32] = "Kresse";
        gem�se[33] = "Kohlrabi";
        gem�se[34] = "Blumenkohl";
        gem�se[35] = "Rotkohl";
        gem�se[36] = "Rosenkohl";
        gem�se[37] = "Artischocke";
        gem�se[38] = "Romanesco";
        gem�se[39] = "Rote Bete";
        gem�se[40] = "Porree";
        gem�se[41] = "Bambussprossen";
        gem�se[42] = "Wei�kohl";
        
        for(int i = 0; i<gem�se.length; i++){
            bestandsliste.add(new Gem�se(gem�se[i], fiktivesMHD, "Gem�se", 100));             
	     }
		
	}
	
	private static void lebensmittel_Am_St�ck_Erstellen(ArrayList<Lebensmittel> bestandsliste){
        String[] lebensmittel_Am_St�ck = new String[36];
        lebensmittel_Am_St�ck[0] = "Wiener_W�rstchen";
        lebensmittel_Am_St�ck[1] = "K�se-Wiener";
        lebensmittel_Am_St�ck[2] = "Kassler";
        lebensmittel_Am_St�ck[3] = "Schweinelende";
        lebensmittel_Am_St�ck[4] = "Krakauer";
        lebensmittel_Am_St�ck[5] = "Krainer-Wurst";
        lebensmittel_Am_St�ck[6] = "Lammbratwurst";
        lebensmittel_Am_St�ck[7] = "Lammschinken";
        lebensmittel_Am_St�ck[8] = "Mettenden";
        lebensmittel_Am_St�ck[9] = "Minisalami";
        lebensmittel_Am_St�ck[10] = "N�rnberger_Bratw�rstchen";
        lebensmittel_Am_St�ck[11] = "Berner W�rstchen";
        lebensmittel_Am_St�ck[12] = "Nusschinken";
        lebensmittel_Am_St�ck[13] = "Salami";
        lebensmittel_Am_St�ck[14] = "Pf�lzer_Saumagen";
        lebensmittel_Am_St�ck[15] = "Pfefferbei�er";
        lebensmittel_Am_St�ck[16] = "Pfeffersalami";
        lebensmittel_Am_St�ck[17] = "Pferdebockwurst";
        lebensmittel_Am_St�ck[18] = "Pferdebratwurst";
        lebensmittel_Am_St�ck[19] = "Pferdesalami";
        lebensmittel_Am_St�ck[20] = "Prager_Schinken";
        lebensmittel_Am_St�ck[21] = "Presswurst";
        lebensmittel_Am_St�ck[22] = "Rauchfleisch";
        lebensmittel_Am_St�ck[23] = "Th�ringer Bratwurst";
        lebensmittel_Am_St�ck[24] = "Rinderschinken";
        lebensmittel_Am_St�ck[25] = "Sauerfleisch";
        lebensmittel_Am_St�ck[26] = "Schweineschinken";
        lebensmittel_Am_St�ck[27] = "Schinkenspie�";
        lebensmittel_Am_St�ck[28] = "Stockwurst";
        lebensmittel_Am_St�ck[29] = "Weckewurst";
        lebensmittel_Am_St�ck[30] = "Weinwurst";
        lebensmittel_Am_St�ck[31] = "Wei�wurst";
        lebensmittel_Am_St�ck[32] = "Westf�lischer Knochenschinken";
        lebensmittel_Am_St�ck[33] = "Wildschweinsalami";
        lebensmittel_Am_St�ck[34] = "Wollwurst";
        lebensmittel_Am_St�ck[35] = "Zungenwurst";

        for(int i = 0; i<lebensmittel_Am_St�ck.length; i++){
            bestandsliste.add(new Lebensmittel_am_St�ck(lebensmittel_Am_St�ck[i], fiktivesMHD, wurstTyp, 100));             
	     }
		
	}
	
	private static void obstErstellen(ArrayList<Lebensmittel> bestandsliste){
		
            String[] obst = new String[50];
            obst[0] = "Ananas";
            obst[1] = "Apfel";
            obst[2] = "Acerola";
            obst[3] = "Aprikose";
            obst[4] = "Birne";
            obst[5] = "Banane";
            obst[6] = "Bergamotte";
            obst[7] = "Brombeere";
            obst[8] = "Boysenbeere";
            obst[9] = "Cherimoya";
            obst[10] = "Clementine";
            obst[11] = "Datteln";
            obst[12] = "Drachenfrucht";
            obst[13] = "Erdbeere";
            obst[14] = "Feige";
            obst[15] = "Grapefruit";
            obst[16] = "Granatapfel";
            obst[17] = "Guave";
            obst[18] = "Wei�e_Trauben";
            obst[19] = "Heidelbeere";
            obst[20] = "Himbeere";
            obst[21] = "Zwetschge";
            obst[22] = "Honigmelone";
            obst[23] = "Kaki";
            obst[24] = "Kaktusfeige";
            obst[25] = "Kirsche";
            obst[26] = "Kiwi";
            obst[27] = "Kumquat";
            obst[28] = "Litschi";
            obst[29] = "Limette";
            obst[30] = "Mandarine";
            obst[31] = "Mango";
            obst[32] = "Maracuja";
            obst[33] = "Mirabelle";
            obst[34] = "Wassermelone";
            obst[35] = "Nektarine";
            obst[36] = "Orange";
            obst[37] = "Papaya";
            obst[38] = "Pfirsich";
            obst[39] = "Pflaume";
            obst[40] = "Physalis";
            obst[41] = "Pomelo";
            obst[42] = "Zitrone";
            obst[43] = "Quitte";
            obst[44] = "Rote_Trauben";
            obst[45] = "Johannisbeeren";
            obst[46] = "Sanddorn";
            obst[47] = "Stachelbeere";
            obst[48] = "Sternfrucht";
            obst[49] = "Sharonfrucht";
            
            for(int i = 0; i<obst.length; i++){
                bestandsliste.add(new Obst(obst[i], fiktivesMHD, "Obst", 100));             
    	     }
    		

	}
	
	private static void pilzeErstellen(ArrayList<Lebensmittel> bestandsliste){
        String[] pilze = new String[18];
        pilze[0] = "Maronen";
        pilze[1] = "Birkenpilz";
        pilze[2] = "Braunkappe";
        pilze[3] = "Breitbl�ttrige Glucke";
        pilze[4] = "Schwarzh�tiger Steinpilz";
        pilze[5] = "Butterpilz";
        pilze[6] = "Rotkappe";
        pilze[7] = "Steinpilz";
        pilze[8] = "Krause_Glucke";
        pilze[9] = "Kr�uterseitling";
        pilze[10] = "Champignon-Braun";
        pilze[11] = "Champignon-Wei�";
        pilze[12] = "Schirmpilz / Parasolpilz";
        pilze[13] = "Pfifferlinge";
        pilze[14] = "Shiitake";
        pilze[15] = "Tr�ffel";
        pilze[16] = "Austernpilze";
        pilze[17] = "Portobello";
        
        for(int i = 0; i<pilze.length; i++){
            bestandsliste.add(new Pilze(pilze[i], fiktivesMHD, pilz, 100));             
	     }

	}
	
	private void standardErstellen(){
		try{
			FileOutputStream fileOut = new FileOutputStream("initBestandsliste.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this.leereBestandsliste);
			objectOut.close();
			
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		}
	}
	
	/*
	 * Debug
	 * */
	public static void main(String[] args) {
		ArrayList<Lebensmittel> bestand= new ArrayList<Lebensmittel>();
		Standard_Bestandsliste_erstellen bla = new Standard_Bestandsliste_erstellen(bestand);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 30);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.YEAR, 2020);
		Standard_Bestandsliste_erstellen.fiktivesMHD = cal;
		
		
		bla.ausf�hren();
		
		bla.standardErstellen();

	}

}
