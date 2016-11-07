import at.ac.univie.swe2016.fm.FahrzeugManagement;
import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Yevhen Baidiuk a1368277
 */
public class FahrzeugClient {

// Diagramm

//    =============
//    find -name "*.java" > sources.txt
//    javac -d bin -encoding ISO-8859-1  -cp a @sources.txt
//    javadoc -d doc -encoding ISO-8859-1  @sources.txt

//===================
//    java FahrzeugClient a/b add lkw 1 Iveco "Eurocargo ML80E" 2014 18000.5
//    java FahrzeugClient a/b add pkw 3 Volkswagen Beetle 2015 15000.00 2015-03-12

    /**
     * @param args args [0] Pfad der Datenquelle. Falls die Datei/Ordner nicht existiert soll sie/er erstellt werden.
     *             args [1] Parameter: show, add, del, count, meanage, meanprice, oldest.
     *             Pro Aufruf muss jeweils nur eine Funktionalitoet ausgeführt werden.
     *
     *             auf 52 zeile nutze ich lambda expessions !!!!!!
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
            DecimalFormat df = new DecimalFormat("0.00");
            FahrzeugManagement fahrzeugManagement = new FahrzeugManagement(args[0]);


            switch (args[1]) {
                case "show":
                    if (args.length == 2) {
                        List<Fahrzeug> list = fahrzeugManagement.getSavedIdList();
                        list.forEach(System.out::println);

                    } else if (args.length == 3)
                        fahrzeugManagement.alleDatenEinesFahrzeugAusgeben(Integer.parseInt(args[2]));
                    else if (args.length > 3) throw new IllegalArgumentException("Arguments ist Falsh!");
                    break;


                case "add":
                    switch (args[2]) {
                        case "lkw":
                            if (args.length != 8) throw new IllegalArgumentException("Arguments ist Falsh!");
                            fahrzeugManagement.neueFahrzeugeHinzufuegen(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]));
                            break;
                        case "pkw":
                            if (args.length != 9) throw new IllegalArgumentException("Arguments ist Falsh!");
                            fahrzeugManagement.neueFahrzeugeHinzufuegen(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]), args[8]);
                            break;
                        default:
                            throw new IllegalArgumentException("Arguments ist Falsh!");
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
                    System.out.println(df.format(fahrzeugManagement.durchschnittsAlterAllerFahrzeugeBerechnen()));
                    break;
                case "meanprice":
                    if (args.length == 2)
                        System.out.println(df.format(fahrzeugManagement.durchschnittsPreisallerFahrzeugeBerechnen()));
                    else if (args.length != 3)
                        throw new IllegalArgumentException("Arguments ist Falsh!");
                    else if (args[2].equals("lkw"))
                        System.out.println(df.format(fahrzeugManagement.durchschnittsPreisAllerLkwBerechnen()));
                    else if (args[2].equals("pkw"))
                        System.out.println(df.format(fahrzeugManagement.durchschnittsPreisAllerPkwBerechnen()));
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