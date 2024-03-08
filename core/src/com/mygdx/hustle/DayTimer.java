package com.mygdx.hustle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DayTimer {
    private static final float DAY_DURATION = 60.0f; // Duration of a day in seconds
    private float elapsedTime = 0.0f;// Elapsed time since the start of the day
    private int currentDay = 1; //start on day 1
    private SpriteBatch batch;
    private BitmapFont font;


    public boolean update(float deltaTime) {
        // Increment elapsed time
        elapsedTime += deltaTime;

        // Check if the day is over
        if (elapsedTime >= DAY_DURATION) {
            // Reset elapsed time for the next day
            elapsedTime = 0.0f;
            currentDay++;

            //if currentDay == 7
            //game over
            return true;

        }
        return false;
    }

    public int getCurrentDay() {
        return this.currentDay;
    }

    public void renderDayNumber(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        batch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale(1.5f); // Scale factor 1.5

        // Draw the day number text
        font.draw(batch, "Day " + currentDay, 50, 100);

        batch.end();
    }
}
