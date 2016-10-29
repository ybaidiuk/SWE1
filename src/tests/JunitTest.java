package tests;

import at.ac.univie.swe2016.fm.FahrzeugManagement;
import at.ac.univie.swe2016.fm.fahrzeuge.LKW;
import at.ac.univie.swe2016.fm.fahrzeuge.PKW;
import swe2016.fm.fahrzeuge.dao.SerializedFahrzeugDAO;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by y.baidiuk on 07/10/2016.
 */
public class JunitTest {


    @Test
    public void testPkwSerialisation() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(1, "Audi", "A6", 2015, 995, "2015-01-20");
        fm.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2011, 6666);
        SerializedFahrzeugDAO sfd = new SerializedFahrzeugDAO("Junit.ser");
        assertEquals(995.00, sfd.getFahrzeugbyId(1).getGrundpreis(), 0);
        assertEquals(6666.00, sfd.getFahrzeugbyId(2).getGrundpreis(), 0);
        File file = new File("Junit.ser");
        file.delete();
    }


    @Test
    public void testDel() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000);
        fm.bestehendeFahrzeugeLoeschen(1);
        assertEquals(0, fm.getSavedIdList().size());
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testCount() {
        FahrzeugManagement fm2 = new FahrzeugManagement("Junit.ser");
        fm2.getSavedIdList().clear();

        fm2.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000);
        fm2.bestehendeFahrzeugeLoeschen(1);
        fm2.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000);
        fm2.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2011, 10000);
        fm2.neueFahrzeugeHinzufuegen(5, "Mercedes", "Fyra", 2011, 10000, "2015-08-12");
        assertEquals(3, fm2.gesamtzahlFahrzeugeBerechnen());
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testCountLkw() {
        FahrzeugManagement fm2 = new FahrzeugManagement("Junit.ser");
        fm2.getSavedIdList().clear();

        fm2.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000);
        fm2.bestehendeFahrzeugeLoeschen(1);
        fm2.neueFahrzeugeHinzufuegen(99, "Mercedes", "Fyra", 2011, 10000); // +1
        fm2.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2011, 10000);  // +1
        fm2.neueFahrzeugeHinzufuegen(5, "Mercedes", "Fyra", 2011, 10000, "2015-08-12"); // pkw
        assertEquals(2, fm2.gesamtzahlDerLKWBerechnen());
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testCountPkw() {
        FahrzeugManagement fm2 = new FahrzeugManagement("Junit.ser");
        fm2.getSavedIdList().clear();

        fm2.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000, "2015-08-12");
        fm2.bestehendeFahrzeugeLoeschen(1);
        fm2.neueFahrzeugeHinzufuegen(123, "Mercedes", "Fyra", 2011, 10000, "2015-08-12");
        fm2.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2011, 10000, "2015-08-12");
        fm2.neueFahrzeugeHinzufuegen(5, "Mercedes", "Fyra", 2011, 10000);
        assertEquals(2, fm2.gesamtzahlDerPKWBerechnen());
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testMeanAge() {
        FahrzeugManagement fm2 = new FahrzeugManagement("Junit.ser");
        fm2.getSavedIdList().clear();

        fm2.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000, "2015-08-12");
        fm2.bestehendeFahrzeugeLoeschen(1);
        fm2.neueFahrzeugeHinzufuegen(123, "Mercedes", "Fyra", 2001, 10);
        fm2.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2006, 29, "2015-08-12");
        assertEquals(12.5, fm2.durchschnittsAlterAllerFahrzeugeBerechnen(), 0);
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testMeanPrice() {
        FahrzeugManagement fm2 = new FahrzeugManagement("Junit.ser");
        fm2.getSavedIdList().clear();

        fm2.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000, "2015-08-12");
        fm2.bestehendeFahrzeugeLoeschen(1);
        fm2.neueFahrzeugeHinzufuegen(123, "Mercedes", "Fyra", 2001, 10);
        fm2.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2006, 29, "2015-08-12");
        assertEquals((10 * 0.8 + 29 * 0.85) / 2, fm2.durchschnittsPreisallerFahrzeugeBerechnen(), 0);
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testMeanPricePkw() {
        FahrzeugManagement fm2 = new FahrzeugManagement("Junit.ser");
        fm2.getSavedIdList().clear();

        fm2.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000, "2015-08-12");
        fm2.bestehendeFahrzeugeLoeschen(1);
        fm2.neueFahrzeugeHinzufuegen(123, "Mercedes", "Fyra", 2001, 10);
        fm2.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2006, 29, "2016-08-12"); //   24.65  15%
        fm2.neueFahrzeugeHinzufuegen(3, "Mercedes", "Fyra", 2015, 249, "2015-08-12"); //7% 231.57
        assertEquals((249 * 0.93 + 29 * 0.85) / 2.0, fm2.durchschnittsPreisAllerPkwBerechnen(), 0.1);
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testMeanPriceLkw() {
        FahrzeugManagement fm2 = new FahrzeugManagement("Junit.ser");
        fm2.getSavedIdList().clear();

        fm2.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2011, 10000);
        fm2.bestehendeFahrzeugeLoeschen(1);
        fm2.neueFahrzeugeHinzufuegen(123, "Mercedes", "Fyra", 2001, 10);
        fm2.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2014, 29);
        fm2.neueFahrzeugeHinzufuegen(3, "Mercedes", "Fyra", 2014, 249, "2015-08-12");
        assertEquals((10 * 0.80 + 29 * 0.90) / 2.0, fm2.durchschnittsPreisAllerLkwBerechnen(), 0);
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testOldest() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();

        fm.neueFahrzeugeHinzufuegen(1, "Mercedes", "Fyra", 2001, 10000);
        fm.neueFahrzeugeHinzufuegen(2, "Mercedes", "Fyra", 2000, 10000);
        fm.bestehendeFahrzeugeLoeschen(1);
        fm.neueFahrzeugeHinzufuegen(3, "Mercedes", "Fyra", 2000, 10);
        fm.neueFahrzeugeHinzufuegen(4, "Mercedes", "Fyra", 2002, 29);
        fm.neueFahrzeugeHinzufuegen(5, "Mercedes", "Fyra", 2004, 29);
        fm.neueFahrzeugeHinzufuegen(6, "Mercedes", "Fyra", 2000, 249, "2015-08-12");
        //oldest 2 3 6
        List<Integer> expected = Arrays.asList(2, 3, 6);
        assertEquals(expected, fm.aeltesteFahrzeugSuchen());
        File file = new File("Junit.ser");
        file.delete();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetMarke() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(6, "", "Fyra", 2015, 249, "2014-08-12");
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetModel() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(6, "Ford", "", 2013, 249, "2014-08-12");
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetYear() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(6, "Ford", "Fokus", 2018, 249);
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetGrundPrise() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(6, "Ford", "Fokus", 2014, -249);
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testPkwPrise() {
        PKW pkw = new PKW(1, "Audi", "A6", 2014, 1000, "2014-01-20");
//        System.out.println("Rabatt: " + pkw.getRabatt());
//        System.out.println("Prise: " + pkw.getPreis());
        assertEquals(860, pkw.getPreis(), 0);
    }

    @Test
    public void testPkwPrise2() {
        PKW pkw = new PKW(1, "Audi", "A6", 2010, 1000, "2014-01-20");
//        System.out.println("Rabatt: " + pkw.getRabatt());
//        System.out.println("Prise: " + pkw.getPreis());
        assertEquals(850, pkw.getPreis(), 0);
    }

    @Test
    public void testLkwPrise() {
        LKW lkw = new LKW(1, "Mercedes", "Fyra", 2015, 10000);
//        System.out.println("Rabatt: " + lkw.getRabatt());
//        System.out.println("Prise: " + lkw.getPreis());
        assertEquals(9500, lkw.getPreis(), 0);
    }

    @Test
    public void testLkwPrise2() {
        LKW lkw = new LKW(1, "Mercedes", "Fyra", 2011, 10000);
//        System.out.println("Rabatt: " + lkw.getRabatt());
//        System.out.println("Prise: " + lkw.getPreis());
        assertEquals(8000, lkw.getPreis(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetId() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(6, "Ford", "Fokus", 2014, 249);
        fm.neueFahrzeugeHinzufuegen(6, "Ford", "Fokus", 2014, 249);
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataIsLessAsYear() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(6, "Mercedes", "Fyra", 2015, 249, "2014-08-12");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetPkwDatum() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(6, "Ford", "Fokus", 2014, 249, "");
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPkwDatum2() {
        FahrzeugManagement fm = new FahrzeugManagement("Junit.ser");
        fm.getSavedIdList().clear();
        fm.neueFahrzeugeHinzufuegen(6, "Ford", "Fokus", 2014, 249, "adfafaf3d");
        File file = new File("Junit.ser");
        file.delete();
    }

    @Test
    public void testDoubleFormatPriseLkw() {
        LKW p1 = new LKW(2, "Tesla Motors", "Model S", 2015, 1000);
        assertEquals(true, p1.toString().contains("950.00"));
    }

    @Test
    public void testDoubleFormatGrundPriseLkw() {
        LKW p1 = new LKW(2, "Tesla Motors", "Model S", 2015, 1000);
        assertEquals(true, p1.toString().contains("1000.00"));
    }

    @Test
    public void testDoubleFormatPrisePkw() {
        PKW p1 = new PKW(2, "Tesla Motors", "Model S", 2015, 1000, "2016-08-12");
        assertEquals(true, p1.toString().contains("950.00"));
    }

    @Test
    public void testDoubleFormatGrundPrisePkw() {
        PKW p1 = new PKW(2, "Tesla Motors", "Model S", 2015, 1000, "2016-08-12");
        assertEquals(true, p1.toString().contains("1000.00"));
    }


}

