package swe2016.fm.fahrzeuge.dao;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;

import java.util.List;

/**
 * Yevhen Baidiuk a1368277
 */
public interface FahrzeugDAO {

    List getFahrzeugList();

    Fahrzeug getFahrzeugbyId(int i);

    void speichereFahrzeug(Fahrzeug fahrzeug);

    void loescheFahrzeug(int i);
}



