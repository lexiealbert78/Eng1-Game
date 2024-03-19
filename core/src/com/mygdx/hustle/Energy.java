package com.mygdx.hustle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Energy {
    //one of each interaction and then energy is drained
    //three times for food
    private final float x, y; // Position of the energy bar
    private final float width, height; // Dimensions of the energy bar
    private final float maxEnergy; // Maximum energy value
    private float currentEnergy; // Current energy value

    // Define color thresholds
    private static final float YELLOW_THRESHOLD = 0.6f;
    private static final float ORANGE_THRESHOLD = 0.4f;
    private static final float RED_THRESHOLD = 0.2f;

    public Energy(float x, float y, float width, float height, float maxEnergy) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy; // Start with full energy

    }

    // Reset the energy bar to its maximum value
    public void resetEnergy() {
        this.currentEnergy = this.maxEnergy;
    }

    public float getMaxEnergy(){ return this.maxEnergy;}

    public void setCurrentEnergy(float currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public float getCurrentEnergy() {
        return this.currentEnergy;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK); // Background color
        shapeRenderer.rect(x, y, width, height); // Draw the background

        // Calculate the width of the green energy bar based on the current energy level
        float energyBarWidth = (currentEnergy / maxEnergy) * (width-2);

        // Set color based on energy level
        if (currentEnergy >= YELLOW_THRESHOLD * maxEnergy) {
            shapeRenderer.setColor(Color.GREEN);
        } else if (currentEnergy >= ORANGE_THRESHOLD * maxEnergy) {
            shapeRenderer.setColor(Color.YELLOW);
        } else if (currentEnergy >= RED_THRESHOLD * maxEnergy) {
            shapeRenderer.setColor(Color.ORANGE);
        } else {
            shapeRenderer.setColor(Color.RED);
        }

        shapeRenderer.rect(x + 1, y + 1, energyBarWidth, height-2); // Draw the energy bar
//TODO energy bar scale when launching game ie resize screen and then press play to recreate.

    }

    public void decrementEnergy(float amount){
        // Decrement energy level
        float newEnergyLevel = this.getCurrentEnergy() - amount;
        // Ensure energy level doesn't go below zero
        newEnergyLevel = Math.max(newEnergyLevel, 0);
        this.setCurrentEnergy(newEnergyLevel);
    }

    public void incrementEnergy(float amount){
        //increment energy level
        if (this.getCurrentEnergy() < maxEnergy){
            this.setCurrentEnergy(this.getCurrentEnergy() + amount);
        }
    }

    public void drawBar(ShapeRenderer shapeRenderer){
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.draw(shapeRenderer);
        shapeRenderer.end();
    }

}
