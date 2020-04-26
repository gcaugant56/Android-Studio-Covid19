package com.example.COVID;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestUnitaire {
    //Test de la classe GlobalStats
    GlobalStats testGlobalStats = new GlobalStats(10,11,12,13,14,15);
    Countries testCountries = new Countries("tata","France",10,11,12,13,14,15);
    @Test
    public void testGlobalsStats()
    {
        //On test les getter sur un objet prédéfini
        assertEquals(10, testGlobalStats.getNewConfirmed());
        assertEquals(11, testGlobalStats.getTotalConfirmed());
        assertEquals(12, testGlobalStats.getNewDeaths());
        assertEquals(13, testGlobalStats.getTotalDeaths());
        assertEquals(14, testGlobalStats.getNewRecovered());
        assertEquals(15, testGlobalStats.getTotalRecovered());
        assertNotEquals(20, testGlobalStats.getNewConfirmed());
        assertNotEquals(21, testGlobalStats.getNewConfirmed());
        assertNotEquals(22, testGlobalStats.getNewConfirmed());
        assertNotEquals(23, testGlobalStats.getNewConfirmed());
        assertNotEquals(24, testGlobalStats.getNewConfirmed());
        assertNotEquals(125, testGlobalStats.getNewConfirmed());

        //On utilise les setter pour vérifier qu'ils fonctionnent
        testGlobalStats.setNewConfirmed(100);
        testGlobalStats.setTotalConfirmed(110);
        testGlobalStats.setNewDeaths(120);
        testGlobalStats.setTotalDeaths(130);
        testGlobalStats.setNewRecovered(140);
        testGlobalStats.setTotalRecovered(150);

        //On réutilise les getter pour validé les changement dans l'objet
        assertEquals(100, testGlobalStats.getNewConfirmed());
        assertEquals(110, testGlobalStats.getTotalConfirmed());
        assertEquals(120, testGlobalStats.getNewDeaths());
        assertEquals(130, testGlobalStats.getTotalDeaths());
        assertEquals(140, testGlobalStats.getNewRecovered());
        assertEquals(150, testGlobalStats.getTotalRecovered());
        assertNotEquals(10, testGlobalStats.getNewConfirmed());
        assertNotEquals(11, testGlobalStats.getNewConfirmed());
        assertNotEquals(12, testGlobalStats.getNewConfirmed());
        assertNotEquals(13, testGlobalStats.getNewConfirmed());
        assertNotEquals(14, testGlobalStats.getNewConfirmed());
        assertNotEquals(15, testGlobalStats.getNewConfirmed());

    }
    @Test
    public void testCountries()
    {
        //On test les getter sur un objet prédéfini
        //Les valeur souhaiter
        assertEquals("France", testCountries.getCountry());
        assertEquals("tata", testCountries.getSlug());
        assertEquals(10, testCountries.getNewConfirmed());
        assertEquals(11, testCountries.getTotalConfirmed());
        assertEquals(12, testCountries.getNewDeath());
        assertEquals(13, testCountries.getTotalDeath());
        assertEquals(14, testCountries.getNewRecovered());
        assertEquals(15, testCountries.getTotalRecovered());
        assertEquals(testCountries.getCountry() +"\nNew Confirmed : "+testCountries.getNewConfirmed()+"\nTotal Confirmed :"+testCountries.getTotalConfirmed(), testCountries.toString());

        //Les valeurs qu'on ne doit pas avoir
        assertNotEquals("Allemagne", testCountries.getCountry());
        assertNotEquals("toto", testCountries.getSlug());
        assertNotEquals(20, testCountries.getNewConfirmed());
        assertNotEquals(21, testCountries.getTotalConfirmed());
        assertNotEquals(22, testCountries.getNewDeath());
        assertNotEquals(23, testCountries.getTotalDeath());
        assertNotEquals(24, testCountries.getNewRecovered());
        assertNotEquals(25, testCountries.getTotalRecovered());
        assertNotEquals("n'importe quoi", testCountries.toString());


        //On utilise les setter pour vérifier qu'ils fonctionnent
        testCountries.setCountry("Allemagne");
        testCountries.setSlug("toto");
        testCountries.setNewConfirmed(20);
        testCountries.setTotalConfirmed(21);
        testCountries.setNewDeath(22);
        testCountries.setTotalDeath(23);
        testCountries.setNewRecovered(24);
        testCountries.setTotalRecovered(25);


        //On réutilise les getter pour validé les changement dans l'objet
        assertEquals("Allemagne", testCountries.getCountry());
        assertEquals("toto", testCountries.getSlug());
        assertEquals(20, testCountries.getNewConfirmed());
        assertEquals(21, testCountries.getTotalConfirmed());
        assertEquals(22, testCountries.getNewDeath());
        assertEquals(23, testCountries.getTotalDeath());
        assertEquals(24, testCountries.getNewRecovered());
        assertEquals(25, testCountries.getTotalRecovered());
        assertEquals(testCountries.getCountry() +"\nNew Confirmed : "+testCountries.getNewConfirmed()+"\nTotal Confirmed :"+testCountries.getTotalConfirmed(), testCountries.toString());


        assertNotEquals("France", testCountries.getCountry());
        assertNotEquals("tata", testCountries.getSlug());
        assertNotEquals(10, testCountries.getNewConfirmed());
        assertNotEquals(11, testCountries.getTotalConfirmed());
        assertNotEquals(12, testCountries.getNewDeath());
        assertNotEquals(13, testCountries.getTotalDeath());
        assertNotEquals(14, testCountries.getNewRecovered());
        assertNotEquals(15, testCountries.getTotalRecovered());
        assertNotEquals("n'importe quoi", testCountries.toString());




    }


}