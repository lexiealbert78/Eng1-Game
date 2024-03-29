package com.mygdx.hustle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class DayTimer {
    private static final float DAY_DURATION = 60.0f; // Duration of a day in seconds
    private float elapsedTime = 0.0f;// Elapsed time since the start of the day
    private float time;
    private int currentDay = 1; //start on day 1
    private SpriteBatch textBatch;
    private BitmapFont font;
    private boolean endOfDay;
    private ExtendViewport viewport;


    public void update(float deltaTime, Energy energy, final HeslingtonHustle heslingtonHustle, final ExtendViewport view, final OrthographicCamera cam, final Score score, Music music) {
        viewport=view;
        endOfDay = false;
        //check if game is over
        if (currentDay>7) {
            music.stop();
            heslingtonHustle.setScreen(new EndScreen(heslingtonHustle, view, cam, score));
        }

        // Increment time
        time += deltaTime;
        elapsedTime+=deltaTime;

        if (time>=0.285) {
            energy.decrementEnergy(0.5f);
            time=0;
        }

        // Check if the day is over
        if (elapsedTime >= DAY_DURATION || energy.getCurrentEnergy() == 0) {
            renderSleepReminder();
            energy.decrementEnergy(10000000);
            endOfDay = true;
        }


    }

    public int getCurrentDay() {
        return this.currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public boolean getEndOfDay(){
        return this.endOfDay;
    }
    public void setElapsedTime(float elapsedTime){
        this.elapsedTime = elapsedTime;
    }
    public float getElapsedTime(){ return this.elapsedTime; }

    public void renderDayNumber(){
        textBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        textBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale((float) (viewport.getScreenWidth() + viewport.getScreenHeight()) /1000); // Scale factor 1.5

        // Draw the day number text
        font.draw(textBatch, "Day " + currentDay, (float) viewport.getScreenWidth() /80, (float) viewport.getScreenHeight() /7);

        textBatch.end();
    }

    public void renderSleepReminder(){
        textBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        textBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale((float) (viewport.getScreenWidth() + viewport.getScreenHeight()) /1000); // Scale factor 1.5

        // Draw the day number text
        font.draw(textBatch, "That's all you can do today! You should get to sleep now.", 50, (float) viewport.getScreenHeight() /18);

        textBatch.end();
    }
}
