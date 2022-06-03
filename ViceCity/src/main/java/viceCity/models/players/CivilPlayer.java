package viceCity.models.players;

public class CivilPlayer extends BasePlayer{

    private static final int CIVIL_HP = 50;


    public CivilPlayer(String name) {
        super(name, CIVIL_HP);
    }
}
