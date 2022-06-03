package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.lang.invoke.ConstantBootstraps;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private ToyRepository toys; //съвкупност от играчки
    private Collection<House> houses; //съвкупност от къщички

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        //1. създаваме къща
        House house;
        //type -> "ShortHouse" или "LongHouse"
        switch (type) {
            case "ShortHouse": //type.equals(ShortHouse.class.getSimpleName())
                //къща от клас ShortHouse
                house = new ShortHouse(name);
                break;
            case "LongHouse": //type.equals(LongHouse.class.getSimpleName())
                //къща от клас LongHouse
                house = new LongHouse(name);
                break;
            default:
                //невалиден тип на къщата
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        //2. добавяме къщата към списъка с къщи
        this.houses.add(house);

        //"%s is successfully added."
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        //type -> "Ball" and "Mouse"
        //1. създаваме играчка
        Toy toy;
        if (type.equals("Ball")) {
            toy = new Ball();
        } else if (type.equals("Mouse")) {
            toy = new Mouse();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        //2. купувам toy
        this.toys.buyToy(toy);

        //"%s is successfully added.";
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        //1. взимам играчката от списъка с играчките
        Toy toy = this.toys.findFirst(toyType);
        if (toy == null) {
            //"Toy of type %s is missing."
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        //2. купя играчката за къщата -> buyToy
        House house = getHouseByName(houseName);
        house.buyToy(toy);
        //3. премахвам играчката от toyRepository
        this.toys.removeToy(toy);

        //•	"Successfully added {toyType} to {houseName}."
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType, houseName);
    }

    private House getHouseByName(String houseName) {
        //връща къща от списъка по име
        return this.houses.stream()
                .filter(house -> house.getName().equals(houseName))
                .findFirst()
                .get();
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        //1. създавам котка -> по тип ("ShorthairCat" или "LonghairCat")
        Cat cat;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        //2. намираме къща с даденото име
        House house = getHouseByName(houseName);
        //3. добавяме котката в къщата

        //cat: Shorthair -> house: ShortHouse
        //cat: LongHair -> house: LongHouse
        boolean checkShort = catType.startsWith("Short") && house.getClass().getSimpleName().startsWith("Short");
        boolean checkLong = catType.startsWith("Long") && house.getClass().getSimpleName().startsWith("Long");
        if (checkShort || checkLong) {
            //котката да влезе в къщата
            house.addCat(cat);
        } else {
            //котката не може да влезе в къщата
            return ConstantMessages.UNSUITABLE_HOUSE;
        }
        //•	"Successfully added {catType} to {houseName}."
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        //1. взима къщата с име houseName
        House house = getHouseByName(houseName);
        house.feeding();

        //"Feeding a cat: %d";
        return String.format(ConstantMessages.FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        //1. взимам къщата с даденото име
        House house = getHouseByName(houseName);
        double priceCats = house.getCats().stream().mapToDouble(Cat::getPrice).sum(); //сума от всички котки
        double priceToys = house.getToys().stream().mapToDouble(Toy::getPrice).sum(); //сума от всички играчки
        double priceAll = priceCats + priceToys; //обща сума от котки и играчки в къщата
        //•	"The value of House {houseName} is {value}."
        return String.format(ConstantMessages.VALUE_HOUSE, houseName, priceAll);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        for (House house : this.houses) {
            builder.append(house.getStatistics()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
