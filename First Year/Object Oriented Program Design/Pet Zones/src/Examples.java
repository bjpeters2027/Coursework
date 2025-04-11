import org.junit.Test;

import javax.xml.catalog.Catalog;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Examples {

    public Examples(){

    }

    /* ANIMAL TESTS */


//Bird tests
    @Test
    public void testBirdEats(){
        Bird b = new Bird("Blue",4,12,new Coord2D(2,2),false);
        assertEquals(1,b.eats("seeds"));
    }

    @Test
    public void testBirdName(){
        Bird b = new Bird("Blue",4,12,new Coord2D(2,2),false);
        assertEquals("Blue",b.getName());
    }

    @Test
    public void testBirdAge(){
        Bird b = new Bird("Blue",4,12,new Coord2D(2,2),false);
        assertEquals(4,b.getAge());
    }

    @Test
    public void testBirdWeight(){
        Bird b = new Bird("Blue",4,12,new Coord2D(2,2),false);
        assertEquals(12,b.getWeightInOz());
    }
//Cat test
    @Test
    public void testCatEatsYesPet(){
        Cat c = new Cat("Aria",4,12,new Coord2D(2,2),true);
        assertEquals(2,c.eats("cans"));
    }

    @Test
    public void testCatEatsNoPet(){
        Cat c = new Cat("Aria",4,12,new Coord2D(2,2),false);
        assertEquals(1,c.eats("cans"));
    }

    @Test
    public void testCatEatsSeeds(){
        Cat c = new Cat("Aria",4,12,new Coord2D(2,2),false);
        assertEquals(0,c.eats("seeds"));
    }

    @Test
    public void testCatEatsTreats(){
        Cat c = new Cat("Fluffy",4,32,new Coord2D(2,2),true);
        assertEquals(5,c.eats("treats"));
    }

    @Test
    public void testCatEatsTreatsBaby(){
        Cat c = new Cat("Kitten",4,6,new Coord2D(2,2),true);
        assertEquals(1,c.eats("treats"));
    }

//Chinchilla tests
    @Test
    public void testChinchillaWeightInOz(){
        Chinchilla ch = new Chinchilla("Dusty",4,20,new Coord2D(0,0), 4);
        assertEquals(24, ch.getWeightInOz());
    }

    @Test
    public void testChinchillaAge(){
        Chinchilla ch = new Chinchilla("Dusty",4,20,new Coord2D(0,0), 4);
        assertEquals(4, ch.getAge());
    }

    @Test
    public void testChinchillaEatsPellets(){
        Chinchilla ch = new Chinchilla("Dusty",4,20,new Coord2D(0,0), 4);
        assertEquals(3, ch.eats("pellets"));
    }

    @Test
    public void testChinchillaEatsHay(){
        Chinchilla ch = new Chinchilla("Dusty",4,20,new Coord2D(0,0), 4);
        assertEquals(1, ch.eats("hay"));
    }


//SuperPetRescue tests

    @Test
    public void testChonkiest(){
        Bird b = new Bird("Blue",4,12,new Coord2D(2,2),false);
        Bird p = new Bird("Parrot", 16, 22, new Coord2D(3,3),true);
        LinkedList<Bird> birdList = new LinkedList<Bird>();
        Cat a = new Cat("Aria",4,12,new Coord2D(2,2),false);
        Cat k = new Cat("Kitten",4,6,new Coord2D(2,2),true);
        LinkedList<Cat> catList = new LinkedList<Cat>();
        Chinchilla d = new Chinchilla("Dusty",4,20,new Coord2D(0,0), 4);
        Chinchilla s = new Chinchilla("Sandy",6,24,new Coord2D(0,0), 4);
        LinkedList<Chinchilla> chList = new LinkedList<Chinchilla>();
        birdList.add(b);
        birdList.add(p);
        BirdZone chonkyBZ = new BirdZone(birdList);
        catList.add(a);
        catList.add(k);
        CatZone chonkyCZ = new CatZone(catList);
        chList.add(d);
        chList.add(s);
        ChinchillaZone chonkyChZ = new ChinchillaZone(chList);
        LinkedList<Zoneable> chonks = new LinkedList<Zoneable>();
        chonks.add(chonkyBZ);
        chonks.add(chonkyCZ);
        chonks.add(chonkyChZ);
        SuperPetRescue chonk = new SuperPetRescue(chonks);

        assertEquals("Tests heaviest pet", "Sandy", chonk.getHeaviestPetsName());
    }

    @Test
    public void animalGetName(){
        Bird b = new Bird("Blue",4,3,new Coord2D(0,0), true);
        Cat c = new Cat("Aria",4,20,new Coord2D(0,0), true);
        Chinchilla ch = new Chinchilla("Dusty",4,20,new Coord2D(0,0), 4);
        assertEquals("Blue", b.getName());
        assertEquals("Aria", c.getName());
        assertEquals("Dusty", ch.getName());
    }

    /** ZONE Tests */

// Bird zones

    @Test
    public void birdZoneTestClosestClipped(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        Bird blue = new Bird("Blue",1,3,new Coord2D(2,2),true);
        Bird red = new Bird("Red",1,3,new Coord2D(1,2),false);
        birds.add(blue);
        birds.add(red);
        BirdZone bz = new BirdZone(birds);
        assertEquals("Red", bz.closestPet(0,0));
    }

    @Test
    public void birdZoneTestClosestClippedEmpty(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
       // birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        assertEquals("No Pets Found", bz.closestPet(4,5));
    }

    @Test
    public void birdZoneTestHumanYears(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        assertEquals(9, bz.inHumanYears("Blue"));
    }

    @Test
    public void birdZoneTestHumanYearsNotIn(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        assertEquals(-1, bz.inHumanYears("Red"));
    }

    @Test
    public void birdZoneTestPetsIn(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        Bird p = new Bird("Parrot",12,20,new Coord2D(4,6),true);
        birds.add(p);
        BirdZone bz = new BirdZone(birds);
        assertEquals(2, bz.petsInZone());
    }

    @Test
    public void birdZoneTestHeaviest1Bird(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        assertEquals("Blue", bz.heaviestPet().getName());
    }

    @Test
    public void birdZoneTestRestock(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        bz.restockPetFood("seeds", 6);
        assertEquals("Bird: 6 seeds", bz.getPantryLabel());
    }

    @Test
    public void birdZoneTestFeedZone(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        bz.restockPetFood("seeds", 6);
        bz.feedZone();
        assertEquals("Bird: 5 seeds", bz.getPantryLabel());
    }

    @Test
    public void birdZoneTestFeedZoneEmpty(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        bz.feedZone();
        assertEquals("Bird: 0 seeds", bz.getPantryLabel());
    }

    @Test
    public void birdZoneTestGetPet(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        Bird p = new Bird("Parrot",12,20,new Coord2D(4,6),true);
        birds.add(p);
        BirdZone bz = new BirdZone(birds);
        assertEquals(p, bz.getPet("Parrot"));
    }

    @Test
    public void birdZoneTestGetPetNotIn(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        Bird p = new Bird("Parrot",12,20,new Coord2D(4,6),true);
        birds.add(p);
        BirdZone bz = new BirdZone(birds);
        assertEquals(null, bz.getPet("Red"));
    }

    //ChinchillaZone Tests

    @Test
    public void testChillaZoneNumberOfPetsEmpty(){
        LinkedList<Chinchilla> chillas = new LinkedList<>();

        ChinchillaZone cz = new ChinchillaZone(chillas);
        assertEquals(0,cz.petsInZone());
    }

    @Test
    public void testChillaZoneNumberOfPetsNonEmpty(){
        LinkedList<Chinchilla> chillas = new LinkedList<>();
        chillas.add(new Chinchilla("Chillin", 3, 20,
                new Coord2D(3,4),2));
        ChinchillaZone cz = new ChinchillaZone(chillas);
        assertEquals(1,cz.petsInZone());


    }
    
    @Test
    public void testHeaviestChilla(){
        LinkedList<Chinchilla> chillas = new LinkedList<>();
        Chinchilla chillin = new Chinchilla("Chillin", 3, 20,
                new Coord2D(3,4),2);
        chillas.add(chillin);
        Chinchilla bigChilla = new Chinchilla("BigChilla", 6, 30,
                new Coord2D(1,1),2);
        chillas.add(bigChilla);
        ChinchillaZone cz = new ChinchillaZone(chillas);
        assertEquals(bigChilla, cz.heaviestPet());
    }

    @Test
    public void testHeaviestChillaEmpty(){
        LinkedList<Chinchilla> chillas = new LinkedList<>();
        ChinchillaZone cz = new ChinchillaZone(chillas);
        assertEquals(null, cz.heaviestPet());
    }

    @Test
    public void testChillaZoneHumanYears(){
        LinkedList<Chinchilla> chillas = new LinkedList<>();
        chillas.add(new Chinchilla("Chillin", 3, 20,
                new Coord2D(3,4),2));
        ChinchillaZone cz = new ChinchillaZone(chillas);
        assertEquals(30,cz.inHumanYears("Chillin"));
    }

    @Test
    public void testChillaZoneHumanYearsNotIn(){
        LinkedList<Chinchilla> chillas = new LinkedList<>();
        chillas.add(new Chinchilla("Chillin", 3, 20,
                new Coord2D(3,4),2));
        ChinchillaZone cz = new ChinchillaZone(chillas);
        assertEquals(-1,cz.inHumanYears("BigChilla"));
    }

    @Test
    public void testChillaClosest(){
        LinkedList<Chinchilla> chillas = new LinkedList<>();
        chillas.add(new Chinchilla("Chillin", 3, 20,
                new Coord2D(3,4),2));
        chillas.add(new Chinchilla("BigChilla", 6, 30,
                new Coord2D(1,1),2));
        ChinchillaZone cz = new ChinchillaZone(chillas);
        assertEquals("BigChilla", cz.closestPet(0,0));
    }

    LinkedList<Chinchilla> chillas = new LinkedList<>();
    Chinchilla chillin = new Chinchilla("Chillin", 3, 20,
            new Coord2D(3,4),2);

    Chinchilla bigChilla = new Chinchilla("BigChilla", 6, 30,
            new Coord2D(1,1),2);

    ChinchillaZone cz = new ChinchillaZone(chillas);

    @Test
    public void testChillaRestockPellets(){
        cz.restockPetFood("pellets", 6);
        assertEquals("Chinchilla: 6 pellets, 0 hay", cz.getPantryLabel());
    }

    @Test
    public void testChillaRestockHay(){
        cz.restockPetFood("hay", 3);
        assertEquals("Chinchilla: 0 pellets, 3 hay", cz.getPantryLabel());
    }
    
    @Test
    public void testChillaFeedZone(){
        chillas.add(bigChilla);
        chillas.add(chillin);
        cz.restockPetFood("pellets",9);
        cz.restockPetFood("hay", 4);
        cz.feedZone();
        assertEquals("Chinchilla: 3 pellets, 2 hay",cz.getPantryLabel());
    }

    @Test
    public void testChillaFeedZoneEmpty(){
        chillas.add(bigChilla);
        chillas.add(chillin);
        cz.feedZone();
        assertEquals("Chinchilla: 0 pellets, 0 hay",cz.getPantryLabel());
    }

    //Cat zone test

    LinkedList<Cat> fluffs = new LinkedList<>();
    Cat fluffy = new Cat("Fluffy", 3, 16, //3 treats 2 cans
            new Coord2D(3,4),true);

    Cat mittens = new Cat("Mittens", 6, 30, //4 treats 1 can
            new Coord2D(1,1),false);

    CatZone caz = new CatZone(fluffs);

    @Test
    public void testCatPetIn(){
        fluffs.add(fluffy);
        fluffs.add(mittens);
        assertEquals(2,caz.petsInZone());
    }

    @Test
    public void testCatPetInEmpty(){
        assertEquals(0,caz.petsInZone());
    }

    @Test
    public void testHeaviestCat(){
        fluffs.add(fluffy);
        fluffs.add(mittens);
        assertEquals(mittens, caz.heaviestPet());
    }

    @Test
    public void testHeaviestCatEmpty(){
        assertEquals(null, caz.heaviestPet());
    }

    @Test
    public void testCatInHuman(){
        fluffs.add(fluffy);
        fluffs.add(mittens);
        assertEquals(18, caz.inHumanYears("Fluffy"));
    }

    @Test
    public void testCatInHumanNotIn(){
        fluffs.add(fluffy);
        fluffs.add(mittens);
        assertEquals(-1, caz.inHumanYears("Beans"));
    }

    @Test
    public void testRestockCatCans(){
        caz.restockPetFood("cans", 6);
        assertEquals("Cat: 6 cans, 0 treats", caz.getPantryLabel());
    }

    @Test
    public void testRestockCatTreats(){
        caz.restockPetFood("treats", 6);
        assertEquals("Cat: 0 cans, 6 treats", caz.getPantryLabel());
    }

    @Test
    public void testFeedZoneCats(){
        fluffs.add(fluffy);
        fluffs.add(mittens);
        caz.restockPetFood("cans", 5);
        caz.restockPetFood("treats", 12);
        caz.feedZone();
        assertEquals("Cat: 2 cans, 5 treats", caz.getPantryLabel());
    }

    @Test
    public void testCatGetPet(){
        fluffs.add(fluffy);
        fluffs.add(mittens);
        assertEquals(fluffy, caz.getPet("Fluffy"));
    }

    @Test
    public void testCatGetPetNotIn(){
        assertEquals(null, caz.getPet("Fluffy"));
    }

    @Test
    public void testClosestCat(){
        fluffs.add(fluffy);
        fluffs.add(mittens);
        assertEquals("Mittens", caz.closestPet(0,0));
    }

    @Test
    public void testClosestCatEmpty(){
        assertEquals("No Pets Found", caz.closestPet(0,0));
    }



    public Boolean closerPoint(Coord2D base, Coord2D a, Coord2D b){
        int distanceAX = (base.x - a.x);
        int distanceAY = (base.y - a.y);
        int distanceBX = (base.x - b.x);
        int distanceBY = (base.y - b.y);
        double vectorA = Math.sqrt(((distanceAX*distanceAX)+(distanceAY*distanceAY)));
        double vectorB = Math.sqrt(((distanceBX*distanceBX)+(distanceBY*distanceBY)));
        return vectorB < vectorA;
    }


    @Test
    public void testCloserPoint(){
        assertEquals(true, closerPoint(new Coord2D(0,0), new Coord2D(5,5), new Coord2D(3,3)));
    }
}