import at.ac.univie.swe2016.fm.FahrzeugManagement;
import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
import at.ac.univie.swe2016.fm.fahrzeuge.LKW;
import at.ac.univie.swe2016.fm.fahrzeuge.PKW;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Yevhen Baidiuk a1368277
 */
public class FahrzeugClient {

// Diagramm

//    =============
//    find -name "*.java" > sources.txt
//    javac -d a -encoding ISO-8859-1  -cp a @sources.txt
//    javadoc -d doc -encoding ISO-8859-1  @sources.txt

//===================
//    java FahrzeugClient b add lkw 1 Iveco "Eurocargo ML80E" 2014 18000.5
//    java FahrzeugClient b add pkw 3 Volkswagen Beetle 2015 15000.00 2016-03-12

    /**
     * @param args args [0] Pfad der Datenquelle. Falls die Datei/Ordner nicht existiert soll sie/er erstellt werden.
     *             args [1] Parameter: show, add, del, count, meanage, meanprice, oldest.
     *             Pro Aufruf muss jeweils nur eine Funktionalitoet ausgeführt werden.
     *             <p>
     *             param show          --- Alle Daten eines Fahrzeuges ausgeben
     *             param add lkw       ---  id   marke   modell   baujahr   grundpreis  LKW persistent hinzufuegen
     *             param add pkw       ---  id   marke   modell   baujahr   grundpreis r PKW persistent hinzufuogen
     *             param del id        --- Fahrzeug loeschen
     *             param count         --- Gesamtzahl der erfassten Fahrzeuge berechnen
     *             param count pkw     --- Gesamtzahl der erfassten PLW berechnen
     *             param count lkw     --- Gesamtzahl der erfassten PKW berechnen
     *             param meanprise     --- Durchschnittspreis aller Fahrzeuge berechnen
     *             param meanprise lkw --- Durchschnittspreis aller lkws berechnen
     *             param meanprise pkw --- Durchschnittspreis aller pkws berechnen
     *             param oldest        --- oeltest(e) Fahrzeug(e) suchen
     */
    public static void main(String[] args) {
        try {

            FahrzeugManagement fahrzeugManagement = new FahrzeugManagement(args[0]);


            switch (args[1]) {
                case "show":
                    if (args.length == 2) {
                        List<Fahrzeug> list = fahrzeugManagement.getSavedIdList();

                        for (Fahrzeug f : list) {
                            System.out.println(f);
                        }


                    } else if (args.length == 3)
                        fahrzeugManagement.alleDatenEinesFahrzeugAusgeben(Integer.parseInt(args[2]));
                    else if (args.length > 3) throw new IllegalArgumentException("Arguments ist Falsh!");
                    break;


                case "add":
                    if (args[2].equals("lkw")) {
                        if (args.length != 8) throw new IllegalArgumentException("Arguments ist Falsh!");
                        fahrzeugManagement.neueFahrzeugeHinzufuegen(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]));
                    } else if (args[2].equals("pkw")) {
                        if (args.length != 9) throw new IllegalArgumentException("Arguments ist Falsh!");
                        fahrzeugManagement.neueFahrzeugeHinzufuegen(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]), args[8]);
                    }
                    break;


                case "del":
                    if (args.length != 3) throw new IllegalArgumentException("Arguments ist Falsh!");
                    fahrzeugManagement.bestehendeFahrzeugeLoeschen(Integer.parseInt(args[2]));
                    break;

                case "count":
                    if (args.length == 2)
                        System.out.println(fahrzeugManagement.gesamtzahlFahrzeugeBerechnen());
                    else if (args.length > 3)
                        throw new IllegalArgumentException("Arguments ist Falsh!");
                    else if (args[2].equals("lkw"))
                        System.out.println(fahrzeugManagement.gesamtzahlDerLKWBerechnen());
                    else if (args[2].equals("pkw"))
                        System.out.println(fahrzeugManagement.gesamtzahlDerPKWBerechnen());
                    else throw new IllegalArgumentException("Arguments ist Falsh");
                    break;

                case "meanage":
                    if (args.length != 2)
                        throw new IllegalArgumentException("Arguments ist Falsh!");
                    System.out.println(fahrzeugManagement.durchschnittsAlterAllerFahrzeugeBerechnen());
                    break;
                case "meanprice":
                    if (args.length == 2)
                        System.out.println(fahrzeugManagement.durchschnittsPreisallerFahrzeugeBerechnen());
                    else if (args.length != 3)
                        throw new IllegalArgumentException("Arguments ist Falsh!");
                    else if (args[2].equals("lkw"))
                        System.out.println(fahrzeugManagement.durchschnittsPreisAllerLkwBerechnen());
                    else if (args[2].equals("pkw"))
                        System.out.println(fahrzeugManagement.durchschnittsPreisAllerPkwBerechnen());
                    break;

                case "oldest":
                    if (args.length != 2) throw new IllegalArgumentException("Arguments ist Falsh!");
                    ArrayList<Integer> aList = fahrzeugManagement.aeltesteFahrzeugSuchen();
                    for (int i : aList)
                        System.out.println("Id: " + i);
                    break;
                default:
                    System.exit(1);
            }

        } catch (IndexOutOfBoundsException e) {
            System.err.println("Fellt Parameter");
            System.exit(1);
        }
    }
}