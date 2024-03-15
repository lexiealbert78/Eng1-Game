package com.mygdx.hustle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DayTimer {
    private static final float DAY_DURATION = 60.0f; // Duration of a day in seconds
    private float elapsedTime = 0.0f;// Elapsed time since the start of the day
    private int currentDay = 1; //start on day 1
    private SpriteBatch textBatch;
    private BitmapFont font;
    private Score score = new Score();


    public boolean update(float deltaTime, Energy energy) {
        // Increment elapsed time
        elapsedTime += deltaTime;

        // Check if the day is over
        if (elapsedTime >= DAY_DURATION || energy.getCurrentEnergy() == 0) {
            renderSleepReminder();
            //not allowed any other interactions but sleep
            //if(interaction with accommodation){
            //   elapsedTime = 0.0f;
            //   currentDay++;
            //   energy.resetEnergy()
            //   }

            if (currentDay == 7){
               score.renderFinalScores();
            }
            //game over
            return true;

        }
        return false;
    }

    public int getCurrentDay() {
        return this.currentDay;
    }

    public void renderDayNumber(){
        textBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        textBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale(1.5f); // Scale factor 1.5

        // Draw the day number text
        font.draw(textBatch, "Day " + currentDay, 50, 100);

        textBatch.end();
    }

    public void renderSleepReminder(){
        textBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        textBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale(1.5f); // Scale factor 1.5

        // Draw the day number text
        font.draw(textBatch, "That's all you can do today! You should get to sleep now.", 50, 130);

        textBatch.end();
    }
}
