package at.ac.univie.swe2016.fm.fahrzeuge;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Yevhen Baidiuk a1368277
 */
public abstract class Fahrzeug implements Serializable, Comparable<Fahrzeug> {
    static final long serialVersionUID = 111L;


    private int id;
    private String marke;
    private String modell;
    private int baujahr;
    private double grundpreis;
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);


    /**
     * Constructor for Fahrzeug , the same hat class LKW.
     * zuweisung gefueert ducht setMetoden damit man  ueberprufungs Logik nicht 2 mal schreiben muss.
     *
     * @param id         Id
     * @param marke      Marke
     * @param modell     Model
     * @param baujahr    Baujahr
     * @param grundpreis Grundpreis
     */
    public Fahrzeug(int id, String marke, String modell, int baujahr, double grundpreis) {
        setId(id);
        setMarke(marke);
        setModell(modell);
        setBaujahr(baujahr);
        setGrundpreis(grundpreis);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        if (marke.isEmpty())
            throw new IllegalArgumentException("marke is empty");
        else
            this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        if (modell.isEmpty())
            throw new IllegalArgumentException("model is empty");
        else
            this.modell = modell;
    }

    public int getBaujahr() {
        return baujahr;
    }

    /**
     * @param baujahr mit ueberpruefung dass Baujahr steht nicht im Zukundt.
     */
    public void setBaujahr(int baujahr) {
        if (baujahr <= currentYear) {
            this.baujahr = baujahr;
        } else throw new IllegalArgumentException("Probleme mit baujahr");
    }

    /**
     * @param grundpreis it ueberpruefung dass Grundpreis ist positive.
     */
    public void setGrundpreis(double grundpreis) {
        if (grundpreis > 0)
            this.grundpreis = grundpreis;
        else throw new IllegalArgumentException("Grundpreis muss grosse als 0 sein");
    }

    public double getGrundpreis() {
        return grundpreis;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public abstract double getRabatt();

    /**
     * @return Preis mit Rabatt
     */
    public double getPreis() {
        return grundpreis * (1 - getRabatt());
    }

    /**
     * @return retuniert unterschied zwieschen Baujahr und current Year .
     */
    public int getAlter() {
        return currentYear - baujahr;
    }

    /**
     * //ascending order for unsere compare
     * @param f compare Fahrzeug
     * @return differenze
     */
    public int compareTo(Fahrzeug f) {
        return this.id - f.getId();
    }
}
