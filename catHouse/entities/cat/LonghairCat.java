package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    // name, breed, kilograms, price
    private static final int LONGHAIR_CAT_KG = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, LONGHAIR_CAT_KG, price);
    }

    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 3);
    }
}
