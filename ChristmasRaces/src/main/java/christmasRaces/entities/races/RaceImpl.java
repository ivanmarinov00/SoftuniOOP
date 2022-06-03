package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race{

    private String name;
    private int laps;
    private Collection<Driver> drivers ;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        drivers = new ArrayList<>();
    }

    private void setName(String name){
        if (name ==  null ||  name.trim().length() < 5){
            String argumentException = String.format(ExceptionMessages.INVALID_NAME, name, 5);
            throw new IllegalArgumentException(argumentException);
        }
        this.name = name;
    }
    private void setLaps(int laps){
        if (laps < 1){
            String argumentException = String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, 1);
            throw new IllegalArgumentException(argumentException);
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null){
            throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
        }else if (!driver.getCanParticipate()){
            String argument = String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE, driver.getName());
            throw new IllegalArgumentException(argument);
        }else if (drivers.contains(driver)){
            String argument = String.format(ExceptionMessages.DRIVER_ALREADY_ADDED, driver.getName(), this.getName());
            throw new IllegalArgumentException(argument);
        }
        drivers.add(driver);
    }
}
