package com.mygdx.hustle;

import com.badlogic.gdx.Gdx;
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

    public void renderScores(){
        scoreBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        scoreBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale(1.5f); // Scale factor 1.5

        // Draw the day number text
        font.draw(scoreBatch, "Times Studied: " + studyCounter, 50, 240);
        font.draw(scoreBatch, "Times Eaten: "+ eatCounter, 50, 280);
        font.draw(scoreBatch, "Times had Fun: "+ recreationCounter, 50, 320);

        scoreBatch.end();
    }

    public void renderFinalScores(){
        ScreenUtils.clear(0, 0.5f, 0, 1);
        scoreBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        scoreBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale(2f); // Scale factor 1.5

        // Draw the day number text
        font.draw(scoreBatch, "Times Studied: " + studyCounter, (float) Gdx.graphics.getWidth() /2, (float) Gdx.graphics.getHeight() /2);
        font.draw(scoreBatch, "Times Eaten: "+ eatCounter, (float) Gdx.graphics.getWidth() /2, (float) (Gdx.graphics.getHeight() /2)-50);
        font.draw(scoreBatch, "Times had Fun: "+ recreationCounter, (float) Gdx.graphics.getWidth() /2, (float) (Gdx.graphics.getHeight() /2)-100);
        scoreBatch.end();
    }

}
