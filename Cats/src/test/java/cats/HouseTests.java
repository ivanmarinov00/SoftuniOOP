package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    Cat first;
    Cat second;
    Cat third;
    House house;
    @Before
    public void setup(){
        this.first = new Cat("Pesho");
        this.second = new Cat("Sasho");
        this.third = new Cat("Ivan");
        this.house = new House("NewHouse", 15);
    }

    @Test
    public void testGetName(){
        House helo = new House("helo", 15);
        Assert.assertEquals("helo", helo.getName());

    }
    @Test
    public void testGetCapacity(){
        House helo = new House("helo", 15);
        Assert.assertEquals(15, helo.getCapacity());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetCapacityFailValue(){
        House helo = new House("helo", -15);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNullException(){
        House helo = new House(null, 15);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameEmptyException(){
        House helo = new House("", 15);
    }
    @Test
    public void testGetCount(){
        house.addCat(first);
        house.addCat(second);
        Assert.assertEquals(2, house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddCatCapacityFull(){
        House helo2 = new House("asd" ,2);
        helo2.addCat(first);
        helo2.addCat(second);
        helo2.addCat(third);
    }
    @Test
    public void testRemoveCat(){
        House helo2 = new House("asd" ,4);
        helo2.addCat(first);
        helo2.addCat(second);
        helo2.addCat(third);
        Assert.assertEquals(3, helo2.getCount());
        helo2.removeCat("Ivan");
        Assert.assertEquals(2, helo2.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatEception(){
        House helo2 = new House("asd" ,4);
        helo2.addCat(first);
        helo2.addCat(second);
        helo2.addCat(third);
        helo2.removeCat("Patkan");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleCatNonExisting(){
        House helo2 = new House("asd" ,4);
        helo2.addCat(first);
        helo2.addCat(second);
        helo2.addCat(third);
        helo2.catForSale("stoiobby");
    }
    @Test
    public void testCatForSale(){
        House helo2 = new House("asd" ,4);
        helo2.addCat(first);
        helo2.addCat(second);
        helo2.addCat(third);
        helo2.catForSale("Ivan");
        Assert.assertEquals(false, third.isHungry());
    }
    @Test
    public void testGetStatistics(){
        House helo2 = new House("asd" ,4);
        helo2.addCat(first);
        helo2.addCat(second);
        Assert.assertEquals("The cat Pesho, Sasho is in the house asd!",helo2.statistics());
    }
}
