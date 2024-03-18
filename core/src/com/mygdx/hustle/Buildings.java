package com.mygdx.hustle;

import com.badlogic.gdx.math.Rectangle;


public abstract class Buildings extends Rectangle{

    public Buildings(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    // Override interact method if specific interaction logic is needed
    public void interact(Energy energy, DayTimer dayTimer, Score score){
        //for recreation and study
        //if not end of day
        //cannot be here as score needs to increase counter for recreation and food differently


    }

    public boolean checkOverlaps(Player player){
        Rectangle playerBounds = player.returnBounds();
        return this.overlaps(playerBounds);
    }
}

class AccommodationBuilding extends Buildings{
    public AccommodationBuilding(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    @Override
    public void interact(Energy energy, DayTimer dayTimer, Score score) {
        // Interaction logic specific to accommodation building
        dayTimer.setElapsedTime(0.0f);
        dayTimer.setCurrentDay(dayTimer.getCurrentDay()+1);
        //last day
        if(dayTimer.getCurrentDay() == 7){
            score.renderFinalScores();
        }
        energy.resetEnergy();
    }

}

class RecreationBuilding extends Buildings{
    public RecreationBuilding(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    @Override
    public void interact(Energy energy, DayTimer dayTimer, Score score) {
        // Interaction logic specific to recreational building
        if(energy.getCurrentEnergy()>=10){
            energy.decrementEnergy(10);
            score.incrementRecreation();
        }
    }

}

class StudyBuilding extends Buildings {
    public StudyBuilding(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void interact(Energy energy, DayTimer dayTimer, Score score) {
        // Interaction logic specific to study building
        if (energy.getCurrentEnergy() >= 25) {
            energy.decrementEnergy(25);
            score.incrementStudy();
        }
    }
}
class EatBuilding extends Buildings {
    public EatBuilding(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    private float timeLastEaten = -100;
    @Override
    public void interact(Energy energy, DayTimer dayTimer, Score score) {
        // Interaction logic specific to study building
        // Must be more than 10 seconds since last eaten (unfinished feature)
        if (energy.getCurrentEnergy() <= energy.getMaxEnergy()) {
            //timeLastEaten = dayTimer.getElapsedTime();
            energy.incrementEnergy(5);
            score.incrementEat();
        }
    }
}

















