package groomingSaloon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSaloon {
    private List<Pet> data;
    private int capacity;

    public GroomingSaloon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }
    public void add(Pet pet){
        if (this.data.size() < capacity){
            this.data.add(pet);
        }
    }
    public boolean remove(String name){
        for (Pet pet: this.data ) {
            if (pet.getName().equals(name)){
                return this.data.remove(pet);
            }
        }
        return false;
    }
    public Pet getPet(String name, String owner){
        for (Pet pet: this.data) {
            if (pet.getName().equals(name) && pet.getOwner().equals(owner)){
                return pet;
            }
        }
        return null;
    }
    public int getCount(){
        return this.data.size();
    }
    public String getStatistics(){
        StringBuilder  sb = new StringBuilder();
        sb.append("The grooming salon has the following clients:").append("\n");
        for (Pet pet:this.data) {
            sb.append(pet.toString()).append("\n");
        }
        return sb.toString();
    }
}
