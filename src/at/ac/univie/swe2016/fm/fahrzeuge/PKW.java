package at.ac.univie.swe2016.fm.fahrzeuge;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

/**
 * Yevhen Baidiuk a1368277
 */
public class PKW extends Fahrzeug implements Serializable {

    static final long serialVersionUID = 333L;

    /**
     * LocalDate so wie String mit Format (1998-02-30)
     * hat Funkrion  "parse"  mit  die kann  daten  from String "1998-02-30" einnehhmmen .
     */
    private LocalDate datum;
    private DecimalFormat df = new DecimalFormat(".00");


    public PKW(int id, String marke, String modell, int baujahr, double grundpreis, String datum) {
        super(id, marke, modell, baujahr, grundpreis);
        setDatum(datum);
    }

    public LocalDate getDatum() {
        return datum;
    }

    /**
     * @param d muss geparsed werden  for LocalDate else wird DateTimeParseException
     */
    public void setDatum(String d) {
        try {
            if (d.isEmpty()) {
                System.exit(1);
                throw new IllegalArgumentException("datum is empty");
            } else
                this.datum = LocalDate.parse(d);


        } catch (DateTimeParseException e) {
            System.err.println("Can't recognise Datum");
            System.exit(1);
        }
        if (datum.getYear() < getBaujahr()) {
            throw new IllegalArgumentException("Überprüfung Fatum is altere als Baujahr");
        }
    }

    /**
     * @return df.format macht double mit 2 Zeichen nach dem Punkt
     */
    @Override
    public String toString() {
        return "Typ:\t\t\t\t\tPKW" + '\n' +
                "Id:\t\t\t\t\t" + getId() + '\n' +
                "Marke:\t\t\t\t\t" + getMarke() + '\n' +
                "Modell:\t\t\t\t\t" + getModell() + '\n' +
                "Baujahr:\t\t\t\t" + getBaujahr() + '\n' +
                "Grundpreis:\t\t\t\t" + df.format(getGrundpreis()) + '\n' +
                "Überprüfungsdatum:\t\t\t" + getDatum() + '\n' +
                "Preis:\t\t\t\t\t" + df.format(getPreis()) + '\n';
    }

    /**
     * @return berechnet Rabatt in prozents for PKW
     * 0.12 means - 12 % Rabatt
     */
    @Override
    public double getRabatt() {
        double rab = getAlter() * 5 + (getCurrentYear() - datum.getYear()) * 2;

        if (rab < 0)
            return 0;
        else if (rab > 15)
            return 0.15;
        else
            return rab / 100;

    }


}
