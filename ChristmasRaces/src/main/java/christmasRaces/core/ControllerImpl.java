package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.io.interfaces.OutputWriter;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;

import javax.swing.plaf.metal.MetalSeparatorUI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private DriverRepository driverRepository;
    private CarRepository carRepository;
    private RaceRepository raceRepository;

    public ControllerImpl(DriverRepository driverRepository, CarRepository carRepository, RaceRepository raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {
        if (driverRepository.getByName(driverName) != null) {
            String argument = String.format(ExceptionMessages.DRIVER_EXISTS, driverName);
            throw new IllegalArgumentException(argument);
        }
        Driver driver = new DriverImpl(driverName);
        driverRepository.add(driver);

        return String.format(OutputMessages.DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(ExceptionMessages.CAR_EXISTS);
        }
        Car car = null;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
        }
        carRepository.add(car);
        return String.format(OutputMessages.CAR_CREATED, type + "Car", model);
    }


    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            String argument = String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName);
            throw new IllegalArgumentException(argument);
        }
        Car car = carRepository.getByName(carModel);
        if (car == null) {
            String argument = String.format(ExceptionMessages.CAR_NOT_FOUND, carModel);
            throw new IllegalArgumentException(argument);
        }
        driver.addCar(car);
        return  String.format(OutputMessages.CAR_ADDED,driverName,carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            String argument = String.format(ExceptionMessages.RACE_EXISTS, raceName);
            throw new IllegalArgumentException(argument);
        }
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null){
                String argument = String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName);
                throw new IllegalArgumentException(argument);
            }
        race.addDriver(driver);
        return String.format(OutputMessages.DRIVER_ADDED,driverName,raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null){
            String argument = String.format(ExceptionMessages.RACE_NOT_FOUND,raceName);
            throw new IllegalArgumentException(argument);
        }
        if (race.getDrivers().size() < 3 ){
            String argument = String.format(ExceptionMessages.RACE_INVALID,raceName, 3);
            throw new IllegalArgumentException(argument);
        }

        Collection<Driver> drivers = race.getDrivers();
        int numberOfLaps = race.getLaps();
        List<Driver> winners = drivers.stream()
                .sorted((d1,d2) ->
                (int) (d2.getCar().calculateRacePoints(numberOfLaps) - d1.getCar().calculateRacePoints(numberOfLaps))
                ).limit(3).collect(Collectors.toList());

         raceRepository.remove(race);
         Driver firstDriver = winners.get(0);
         Driver secondDriver = winners.get(1);
         Driver thirdDriver = winners.get(2);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.DRIVER_FIRST_POSITION,firstDriver.getName(),race.getName()))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_SECOND_POSITION,secondDriver.getName(),race.getName()))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION,thirdDriver.getName(),race.getName()))
                .append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = raceRepository.getByName(name);
        if (race != null){
            String argument = String.format(ExceptionMessages.RACE_EXISTS,name);
            throw new IllegalArgumentException(argument);
        }
        Race newRace = new RaceImpl(name, laps);
        raceRepository.add(newRace);
        return String.format(OutputMessages.RACE_CREATED,name);
    }
}
