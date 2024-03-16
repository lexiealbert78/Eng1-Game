package com.mygdx.hustle;

import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.exit;


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


    };

    public boolean checkOverlaps(Player player){
        Rectangle playerBounds = player.returnBounds();
        return this.overlaps(playerBounds);
    };
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
        // Interaction logic specific to accommodation building
        if(!dayTimer.getEndOfDay()){
            energy.decrementEnergy();
            score.incrementRecreation();
        }
    }

}



//eating conditions - ten seconds since last one, check its not end of day (first check)















