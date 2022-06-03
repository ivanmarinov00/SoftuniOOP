package aquarium;

public class Main {
    public static void main(String[] args) {
        // Initialize Aquarium
        Aquarium aquarium = new Aquarium("Ocean", 5, 15);
        Fish secondFish = new Fish("Dory", "blue", 2);
        Fish thirdFish = new Fish("Nemo", "orange", 5);
        aquarium.add(secondFish);
        aquarium.add(thirdFish);

        // Print Aquarium report
        System.out.println(aquarium.report());

        //Aquarium Info:
        //Aquarium: Ocean ^ Size: 15
        //Fish: Dory
        //Color: blue
        //Number of fins: 2
        //Fish: Nemo
        //Color: orange
        //Number of fins: 5
    }
}
