package viceCity.models.players;

import viceCity.common.ExceptionMessages;
import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player{
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;


    public BasePlayer(String name, int lifePoints) {
        this.setName(name);
        this.setLifePoints(lifePoints);
    }


    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }
        this.name = name;
    }

    public void setLifePoints(int lifePoints) {
        if (lifePoints < 0 ){
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO);

        }
        this.lifePoints = lifePoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        return lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        this.lifePoints = this.lifePoints - points;
        if(this.lifePoints < 0){
            this.lifePoints = 0;
        }
    }
}
