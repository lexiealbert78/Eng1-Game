package com.mygdx.hustle;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Score {
    private int studyCounter;
    private int recreationCounter;
    private int eatCounter;
    private SpriteBatch scoreBatch;
    private BitmapFont font;

    public int incrementStudy(){
        return studyCounter++;
    }
    public int incrementRecreation(){
        return recreationCounter++;
    }

    public int incrementEat(){
        return eatCounter++;
    }

    public void renderFinalScores(){
        ScreenUtils.clear(0, 0.5f, 0, 1);
        scoreBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        scoreBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale(1.5f); // Scale factor 1.5

        // Draw the day number text
        font.draw(scoreBatch, "Times Studied: " + studyCounter, 50, 100);
        font.draw(scoreBatch, "Times eaten: "+ eatCounter, 50, 100);
        font.draw(scoreBatch, "Times had fun: "+ recreationCounter, 50, 100);

        scoreBatch.end();
    }

}
