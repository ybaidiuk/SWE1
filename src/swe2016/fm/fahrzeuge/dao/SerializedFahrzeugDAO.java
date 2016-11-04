package swe2016.fm.fahrzeuge.dao;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;

import java.io.*;
import java.util.*;

/**
 * Yevhen Baidiuk a1368277
 * Die Klasse SerializedFahrzeugDAO implementiert das Interface FahrzeugDAO.
 */
public class SerializedFahrzeugDAO implements FahrzeugDAO {

    private String file;
    private List<Fahrzeug> savedIdList;

    public SerializedFahrzeugDAO(String s) {
        file = s;
        restoreData();
    }

    private void restoreData() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream os = new ObjectInputStream(fileInputStream);
            savedIdList = (ArrayList<Fahrzeug>) os.readObject();
            os.close();
        } catch (Exception e) {
            savedIdList = new ArrayList<>();
        }

    }

    private void saveData() {
        try {
            File outFile = new File(file);
            if (outFile.getParentFile() != null)
                outFile.getParentFile().mkdirs();

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
            os.writeObject(savedIdList);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Die Methode getFahrzeugList()  eine Liste (z. B. java.util.ArrayList) benutzt,
     * um alle persistent gespeicherten Fahrzeugobjekte zurueckzugeben.
     */
    @Override
    public List<Fahrzeug> getFahrzeugList() {
        return savedIdList;
    }

    /**
     * Die Methode getFahrzeugbyId(… id) gib, anhand der Fahrzeugnummer, ein Fahrzeug-Objekt zurueck.
     * Falls das Fahrzeug nicht gefunden wird, soll die Methode null zurueckgeben.
     */
    @Override
    public Fahrzeug getFahrzeugbyId(int id) {
        for (Object o : savedIdList) {
            Fahrzeug f = (Fahrzeug) o;
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    /**
     * aud 93 zeile nutze ich lambda expessions !!!!!!
     * speichereFahrzeug(Fahrzeug …)), alle Instanzvariablen des Fahrzeugobjektes gesetzt bzw. nicht leer sind.
     * Falls ein Wert fehlt, soll eine entsprechende IllegalArgumentException geworfen werden.
     * <p>
     * ein Fahrzeugobjekt persistent speichert.
     * Stellen Sie sicher, dass beim Speichern eines neuen Fahrzeuges nicht die Fahrzeugnummer eines bereits
     * gespeicherten Fahrzeug verwendet wird. Werfen Sie in diesem Fall eine IllegalArgumentException mit einer
     * entsprechenden Fehlermeldung.
     */

    @Override
    public void speichereFahrzeug(Fahrzeug fahrzeug) {
        for (Fahrzeug f : savedIdList) {
            if (f.getId() == fahrzeug.getId()) {
                throw new IllegalArgumentException("Fahrzeug mit diese Id alredy besetz");
            }
        }
        savedIdList.add(fahrzeug);

        Collections.sort(savedIdList, ((o1, o2) -> o1.getId() - o2.getId()));
        saveData();
    }

    /**
     * Die Methode loescheFahrzeug(Fahrzeug …) loescht  ein bestehendes Fahrzeug von der persistenten Datenquelle .
     * Falls es kein solches Fahrzeug gibt, soll IllegalArgumentException (mit entsprechender Fehlermeldung) geworfen werden.
     */
    @Override
    public void loescheFahrzeug(int i) {
        for (Fahrzeug f : savedIdList) {
            if (f.getId() == i) {
                savedIdList.remove(f);
                saveData();
                return;
            }
        }
        throw new IllegalArgumentException("Thre's no Fahrzeug with this Id");
    }

}
