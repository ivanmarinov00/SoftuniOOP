package parrots;

import java.util.ArrayList;
import java.util.List;

public class Cage {
    private List<Parrot> data;
    private String name;
    private int capacity;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Parrot parrot) {
        if (data.size() < capacity) {
            data.add(parrot);
        }
    }

    public boolean remove(String name) {
        for (Parrot parrot : data) {
            if (parrot.getName().equals(name)) {
                data.remove(parrot);
                return true;
            }
        }
        return false;
    }

    public Parrot sellParrot(String name) {
        Parrot soldParrot = null;
        for (Parrot parrot : data) {
            if (parrot.getName().equals(name)) {
                parrot.setAvailable(false);
                soldParrot = parrot;
                break;
            }
        }
        return soldParrot;
    }

    public List<Parrot> sellParrotBySpecies(String species) {
        List<Parrot> soldParrots = new ArrayList<>();
        for (Parrot parrot : data) {
            if (parrot.getSpecies().equals(species)) {
                soldParrots.add(parrot);
            }
        }
        return soldParrots;
    }

    public int count() {
        return data.size();
    }

    public String report() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Parrots available at %s:", this.name)).append(System.lineSeparator());
        for (Parrot parrot : data) {
            if (parrot.isAvailable()) {
                stringBuilder.append(parrot).append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
