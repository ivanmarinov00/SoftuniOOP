package aquarium;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    private String name;
    private int capacity;
    private int size;
    private List<Fish> fishInPool;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getFishInPool() {
        return fishInPool.size();
    }

    public void add(Fish fish) {
        //ако няма такава риба
        boolean haveSameFish = false;
        for (Fish f : fishInPool) {
            if (f.getName().equals(fish.getName())) {
                haveSameFish = true;
            }
        }
        //има място в аквариума: капацитетът > бр. рибите
        if (!haveSameFish && capacity >= getFishInPool() + 1) {
            this.fishInPool.add(fish);
        }
    }

    public boolean remove(String name) {
        //true -> ако успешно премахнем риба
        //false -> ако няма такава риба
        //1. намеря рибата която да премахна
        Fish removedFish = null; //риба, която трябва да премахна
        for (Fish fish : fishInPool) {
            if (fish.getName().equals(name)) {
                removedFish = fish;
            }
        }
        //знам коя е рибата,която искам да махна
        //null
        if (removedFish == null) {
            //няма такава риба
            return false;
        } else {
            fishInPool.remove(removedFish);
            return true;
        }
    }

    public Fish findFish(String name) {
        for (Fish fish : fishInPool) {
            if (fish.getName().equals(name)) {
                return fish;
            }
        }
        //не съм намерила рибата
        return null;
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append("Aquarium: " + name + " ^ Size: " + size).append(System.lineSeparator());
        for (Fish fish : fishInPool) {
            builder.append(fish.toString()).append(System.lineSeparator());
        }
        return builder.toString();
    }

}
