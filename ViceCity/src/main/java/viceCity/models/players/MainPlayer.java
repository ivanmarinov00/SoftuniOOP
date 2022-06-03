package viceCity.models.players;

public class MainPlayer extends BasePlayer{

    private static final int MAIN_HP = 100;
    private static final String MAIN_NAME = "Tommy Vercetti";

    public MainPlayer() {
        super(MAIN_NAME, MAIN_HP);
    }
}
