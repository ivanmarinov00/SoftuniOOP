package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class GarageTests {
    //TODO: Test Garage class
    private Car skoda;
    private Car golf;
    private Car audi;

    @Before
    public void setup() {
        this.skoda = new Car("skoda", 200, 1000);
        this.golf = new Car("golf", 220, 1500);
        this.audi = new Car("audi", 210, 300);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarsException() {
        Garage garage = new Garage();
        garage.addCar(null);
    }

    @Test
    public void testAddCarsSuccessfully() {
        Garage garage = new Garage();
        garage.addCar(skoda);
    }

    @Test
    public void testGetCount() {
        Garage garage = new Garage();
        garage.addCar(skoda);
        Assert.assertEquals(1, garage.getCount());
    }

    @Test
    public void testGetCars() {
        Garage garage = new Garage();
        garage.addCar(skoda);
        List<Car> carsInGarage = garage.getCars();
        Assert.assertEquals(1, garage.getCount());
        Assert.assertEquals(skoda.getBrand(), carsInGarage.get(0).getBrand());
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAbove() {
        Garage garage = new Garage();
        garage.addCar(skoda);
        garage.addCar(golf);
        garage.addCar(audi);
        List<Car> carsWithSpeedAboveValue = garage.findAllCarsWithMaxSpeedAbove(210);
        Assert.assertEquals(golf.getBrand(), carsWithSpeedAboveValue.get(0).getBrand());
    }
    @Test
    public void testGetMostExpensiveCar(){
        Garage garage = new Garage();
        garage.addCar(skoda);
        garage.addCar(golf);
        garage.addCar(audi);
        Car mostExpCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(this.golf, mostExpCar);

    }
    @Test
    public void testFindAllCarsByBrand(){
        Garage garage = new Garage();
        garage.addCar(skoda);
        garage.addCar(golf);
        garage.addCar(audi);
        List<Car> carsWithBrand = garage.findAllCarsByBrand("skoda");
        Assert.assertEquals(this.skoda.getBrand(), carsWithBrand.get(0).getBrand());
    }
}