package viceCity.models.guns;

public class Pistol extends BaseGun{

    private static final int PISTOL_BARREL_COUNT = 10;
    private static  final int PISTOL_TOTAL_BULLETS = 100;
    private static  final int PISTOL_BULLETS_PER_SHOT = 1;

    public Pistol(String name) {
        super(name, PISTOL_BARREL_COUNT, PISTOL_TOTAL_BULLETS);
    }
    @Override
    public int fire() {
        if (getBulletsPerBarrel() > 0 ) {
            setBulletsPerBarrel(getBulletsPerBarrel() - PISTOL_BULLETS_PER_SHOT);
        }
            reload();

        return  PISTOL_BULLETS_PER_SHOT;
    }
    private void reload(){
        setTotalBullets(getTotalBullets() - PISTOL_BARREL_COUNT);
        setBulletsPerBarrel(PISTOL_BARREL_COUNT);
    }
}
