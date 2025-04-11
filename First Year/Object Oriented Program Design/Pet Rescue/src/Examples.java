import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class Examples {

    PetRescue pr;


    public Examples() {

    }

    @Test
    public void testBiggestBird() {

        LinkedList<Integer> someBirdWeights = new LinkedList<Integer>();
        someBirdWeights.add(10);
        someBirdWeights.add(12);
        pr = new PetRescue(someBirdWeights, new LinkedList<Integer>(), "", new LinkedList<Coord>());

        assertEquals("Test biggest bird", 12, pr.biggestBird());
    }


    @Test
    public void testBiggestBirdEqual() {

        LinkedList<Integer> someBirdWeights = new LinkedList<Integer>();
        someBirdWeights.add(10);
        someBirdWeights.add(10);
        pr = new PetRescue(someBirdWeights, new LinkedList<Integer>(), "", new LinkedList<Coord>());

        assertEquals("Test a biggest bird with 2 equal numbers", 10, pr.biggestBird());
    }

    @Test
    public void testBiggestBirdEmpty() {

        LinkedList<Integer> someBirdWeights = new LinkedList<Integer>();

        pr = new PetRescue(someBirdWeights, new LinkedList<Integer>(), "", new LinkedList<Coord>());

        assertEquals("Test an empty biggest bird", 0, pr.biggestBird());
    }

    @Test
    public void testDogYearsNonEmpty() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        dogYears.add(2);
        dogYears.add(3);
        pr = new PetRescue(birdWeight, dogYears, "", catCoords);

        LinkedList<Integer> someHumanYears = new LinkedList<>();
        someHumanYears.add(14);
        someHumanYears.add(21);

        assertEquals("Dog years should be 14 and 21 respectively", someHumanYears, pr.inHumanYears());


    }

    @Test
    public void testDogYearsNeg() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        dogYears.add(-2);
        dogYears.add(-3);
        pr = new PetRescue(birdWeight, dogYears, "", catCoords);

        LinkedList<Integer> someHumanYears = new LinkedList<>();
        someHumanYears.add(-14);
        someHumanYears.add(-21);

        assertEquals("Dog years should be -14 and -21 respectively", someHumanYears, pr.inHumanYears());
    }

    @Test
    public void testDogYearsEmpty() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "", catCoords);

        LinkedList<Integer> someHumanYears = new LinkedList<>();

        assertEquals("Dog years should be 0", someHumanYears, pr.inHumanYears());
    }

    @Test
    public void testBestowHonorFull() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);
        pr.bestowHonor("Dr.", "PHD");
        assertEquals("Tests all parameters of bestowHonor", "Dr. Pepper, PHD", pr.petOfTheMonth);
    }

    @Test
    public void testBestowHonorEmpty() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);
        pr.bestowHonor("", "");
        assertEquals("Tests all parameters of bestowHonor empty", "Pepper", pr.petOfTheMonth);
    }

    @Test
    public void testBestowHonorEmptyTitle() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);
        pr.bestowHonor("", "PHD");
        assertEquals("Tests title parameter of bestowHonor empty", "Pepper, PHD", pr.petOfTheMonth);
    }

    @Test
    public void testBestowHonorEmptyCredentials() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);
        pr.bestowHonor("Dr.", "");
        assertEquals("Tests credentials parameter of bestowHonor empty", "Dr. Pepper", pr.petOfTheMonth);
    }

    @Test
    public void testBestowHonorEmptyPotm() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "", catCoords);
        pr.bestowHonor("", "");
        assertEquals("Tests credentials parameter of bestowHonor empty", "", pr.petOfTheMonth);
    }

    @Test
    public void testBestowHonorMultiCred() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);
        pr.bestowHonor("Dr.", "Phd, MD");
        assertEquals("Tests credentials parameter of bestowHonor empty", "Dr. Pepper, Phd MD", pr.petOfTheMonth);
    }

    @Test
    public void testFeedNormal() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);

        assertEquals("Tests a full version of the feedChinchilla method", "Chinchilla: 6 pellets, 4 hay", pr.feedChinchillas(6, 4));
    }

    @Test
    public void testFeedNegPel() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);

        assertEquals("Tests a full version of the feedChinchilla method", "Chinchilla: unknown pellets, 4 hay", pr.feedChinchillas(-6, 4));
    }

    @Test
    public void testFeedNegHay() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);

        assertEquals("Tests a full version of the feedChinchilla method", "Chinchilla: 6 pellets, unknown hay", pr.feedChinchillas(6, -4));
    }

    @Test
    public void testFeedDoubleNeg() {
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);

        assertEquals("Tests a full version of the feedChinchilla method", "Chinchilla: unknown pellets, unknown hay", pr.feedChinchillas(-6, -4));
    }


    @Test
    public void testClosestNorm(){
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        Coord Fluffy = new Coord("Fluffy", 2,3);
        Coord Mittens = new Coord("Mittens", 5,8);
        catCoords.add(Fluffy);
        catCoords.add(Mittens);

        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);

        assertEquals("General test for the \"closestTo\" function ", Fluffy.name ,pr.closestTo(0,0));
    }
    @Test
    public void testClosestTie(){
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        Coord Fluffy = new Coord("Fluffy", 2,3);
        Coord Mittens = new Coord("Mittens", 2,3);
        catCoords.add(Fluffy);
        catCoords.add(Mittens);

        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);

        assertEquals("General test for the \"closestTo\" function ", Fluffy.name ,pr.closestTo(0,0));
    }

    @Test
    public void testClosestNoCats(){
        LinkedList<Integer> birdWeight = new LinkedList<>();
        LinkedList<Integer> dogYears = new LinkedList<>();
        LinkedList<Coord> catCoords = new LinkedList<>();
        Coord Fluffy = new Coord("Fluffy", 2,3);
        Coord Mittens = new Coord("Mittens", 2,3);

        pr = new PetRescue(birdWeight, dogYears, "Pepper", catCoords);

        assertEquals("General test for the \"closestTo\" function ", "Conspiciously Catless",pr.closestTo(0,0));
    }
}