package at.ac.univie.swe2016.fm.fahrzeuge;

import java.io.Serializable;
import java.text.DecimalFormat;
/**
 * Yevhen Baidiuk a1368277
 */
public class LKW extends Fahrzeug implements Serializable {

    static final long serialVersionUID = 222L;
    private DecimalFormat df = new DecimalFormat("0.00");

    public LKW(int id, String marke, String modell, int baujahr, double grundpreis) {
        super(id, marke, modell, baujahr, grundpreis);
    }

    /**
     * @return berechnet Rabatt in prozents for LKW
     * 0.12 means - 12 % Rabatt
     */
    @Override
    public double getRabatt() {
        double rab = getAlter() * 5;

        if (rab < 0)
            return 0;
        else if (rab > 20)
            return 0.2;
        else
            return rab / 100;

    }


    /**
     * @return df.format macht double mit 2 Zeichen nach dem Punkt
     */

    @Override
    public String toString() {
        return "Typ:\t\t\t\t\tLKW" + '\n' +
                "Id:\t\t\t\t\t" + getId() + '\n' +
                "Marke:\t\t\t\t\t" + getMarke() + '\n' +
                "Modell:\t\t\t\t\t" + getModell() + '\n' +
                "Baujahr:\t\t\t\t" + getBaujahr() + '\n' +
                "Grundpreis:\t\t\t\t" + df.format(getGrundpreis()) + '\n' +
                "Preis:\t\t\t\t\t" + df.format(getPreis()) + '\n';
    }
}
