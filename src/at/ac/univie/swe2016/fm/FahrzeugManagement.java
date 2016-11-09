package at.ac.univie.swe2016.fm;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
import at.ac.univie.swe2016.fm.fahrzeuge.LKW;
import at.ac.univie.swe2016.fm.fahrzeuge.PKW;
import swe2016.fm.fahrzeuge.dao.FahrzeugDAO;
import swe2016.fm.fahrzeuge.dao.SerializedFahrzeugDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Yevhen Baidiuk a1368277
 * Die Klasse FahrzeugManagement soll die Logik der Fahrzeugverwaltung implementieren.
 * Die Klasse soll eine Instanzvariable fahrzeugDAO, die ein FahrzeugDAO-Objekt referenziert, besitzen.
 * Der Zugriff auf diese Instanzvariable soll mittels set- und get-Methoden zur Verfuegung gestellt werden.
 */
public class FahrzeugManagement {
    private FahrzeugDAO fahrzeugDAO;
    private List<Fahrzeug> savedIdList;

    public FahrzeugManagement(String pfad) {
        fahrzeugDAO = new SerializedFahrzeugDAO(pfad);
        savedIdList = fahrzeugDAO.getFahrzeugList();
    }

    /**
     * @param id id des retunierte Fahrzeug
     *           Alle Daten eines Fahrzeug ausgeben
     */
    public void alleDatenEinesFahrzeugAusgeben(int id) {
        Fahrzeug fahrzeug = fahrzeugDAO.getFahrzeugbyId(id);
        System.out.println(fahrzeug);

    }


    //    /**
//     * Neue Fahrzeuge hinzufuegen
//     *
//     * @param f Fahrzeug das man speichern muss.
//     */
//
//    public void neueFahrzeugeHinzufuegen(Fahrzeug f) {
//        fahrzeugDAO.speichereFahrzeug(f);
//    }
//    java FahrzeugClient a add lkw 1 Iveco "Eurocargo ML80E" 2014 18000.5
//    java FahrzeugClient a add pkw 2 Volkswagen Beetle 2015 15000.00 2016-03-12
    public void neueFahrzeugeHinzufuegen(int id, String marke, String modell, int baujahr, double grundpreis) {
        Fahrzeug lkw = new LKW(id, marke, modell, baujahr, grundpreis);
        fahrzeugDAO.speichereFahrzeug(lkw);
    }


    public void neueFahrzeugeHinzufuegen(int id, String marke, String modell, int baujahr, double grundpreis, String datum) {
        Fahrzeug pkw = new PKW(id, marke, modell, baujahr, grundpreis, datum);
        fahrzeugDAO.speichereFahrzeug(pkw);
    }

    /**
     * Bestehende Fahrzeuge loeschen
     *
     * @param i id des Fahrzeug
     */
    public void bestehendeFahrzeugeLoeschen(int i) {
        fahrzeugDAO.loescheFahrzeug(i);
    }


    /**
     * @return Gesamtzahl der erfassten Fahrzeuge berechnen
     */
    public int gesamtzahlFahrzeugeBerechnen() {
        return savedIdList.size();
    }

    /**
     * @return Gesamtzahl der PKW berechnen
     */
    public int gesamtzahlDerPKWBerechnen() {
        int i = 0;

        for (Fahrzeug o : savedIdList) {
            if (o instanceof PKW) i++;
        }
        return i;
    }

    /**
     * @return Gesamtzahl der LKW berechnen
     */
    public int gesamtzahlDerLKWBerechnen() {
        int i = 0;

        for (Fahrzeug o : savedIdList) {
            if (o instanceof LKW) i++;
        }
        return i;
    }

    /**
     * @return DurchschnittsALTER aller Fahrzeuge berechnen
     */
    public double durchschnittsAlterAllerFahrzeugeBerechnen() {
        if (gesamtzahlFahrzeugeBerechnen() == 0) return 0;
        double sum = 0;
        for (Fahrzeug f : savedIdList) {
            sum += f.getAlter();
        }

        return sum / gesamtzahlFahrzeugeBerechnen();
    }

    /**
     * @return Durchschnittspreis aller Fahrzeuge berechnen
     */
    public double durchschnittsPreisallerFahrzeugeBerechnen() {
        if (gesamtzahlFahrzeugeBerechnen() == 0) return 0;
        double sum = 0;
        for (Fahrzeug f : savedIdList) {
            sum += f.getPreis();
        }

        return sum / gesamtzahlFahrzeugeBerechnen();
    }

    /**
     * @return Durchschnittspreis aller PLW berechnen
     */
    public double durchschnittsPreisAllerPkwBerechnen() {
        if (gesamtzahlDerPKWBerechnen() == 0) return 0;
        double sum = 0;
        for (Fahrzeug f : savedIdList) {
            if (f instanceof PKW)
                sum += f.getPreis();
        }
        return sum / gesamtzahlDerPKWBerechnen();
    }

    /**
     * @return Durchschnittspreis aller LKW berechnen
     */

    public double durchschnittsPreisAllerLkwBerechnen() {
        if (gesamtzahlDerLKWBerechnen() == 0) return 0;
        double sum = 0;
        for (Fahrzeug f : savedIdList) {
            if (f instanceof LKW)
                sum += f.getPreis();
        }
        return sum / gesamtzahlDerLKWBerechnen();
    }

    /**
     * @return Aelteste(s) Fahrzeug(e) suchen
     */
    public ArrayList<Integer> aeltesteFahrzeugSuchen() {
        int old;
        ArrayList<Integer> arr = new ArrayList<>();
        Fahrzeug f = savedIdList.get(0);
        arr.add(f.getId());
        old = f.getAlter();

        for (int i = 1; i < savedIdList.size(); i++) {
            if (savedIdList.get(i).getAlter() > old) {
                old = savedIdList.get(i).getAlter();
                arr.clear();
            }
            if (savedIdList.get(i).getAlter() == old)
                arr.add(savedIdList.get(i).getId());

        }
        return arr;
    }

    /**
     * @return savedIdList from SerializedFahrzeugDAO
     */
    public List getSavedIdList() {
        return savedIdList;
    }


    public ArrayList<Integer> filter(int i, int k) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (Fahrzeug f : savedIdList) {
            if (f.getAlter() >= i && f.getAlter() <= k) {
                integerArrayList.add(f.getId());
            }
        }
        return integerArrayList;
    }

}











