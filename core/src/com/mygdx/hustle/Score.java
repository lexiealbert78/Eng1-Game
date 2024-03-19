package com.mygdx.hustle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

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

    public void renderScores(ExtendViewport viewport){
        scoreBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        scoreBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale((float) (viewport.getScreenWidth() + viewport.getScreenHeight()) /750); // Scale factor

        float factor= (float) viewport.getScreenHeight() /18;
        font.draw(scoreBatch, "Times Studied: " + studyCounter, 25, (float) (viewport.getScreenHeight())-factor);
        font.draw(scoreBatch, "Times Eaten: "+ eatCounter, 25, (float) (viewport.getScreenHeight())-2*factor);
        font.draw(scoreBatch, "Times had Fun: "+ recreationCounter, 25, (float) (viewport.getScreenHeight())-3*factor);

        scoreBatch.end();
    }

    public void renderFinalScores(ExtendViewport viewport){
        ScreenUtils.clear(0, 0.5f, 0, 1);
        scoreBatch = new SpriteBatch();
        font = new BitmapFont();
        // Begin drawing with the sprite batch
        scoreBatch.begin();

        // Set font color and scale
        font.setColor(1, 1, 1, 1); // White color
        font.getData().setScale((float) (viewport.getScreenWidth() + viewport.getScreenHeight()) /400);

        Texture background= new Texture("end.png");
        scoreBatch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float factorX= (float) viewport.getScreenWidth() /4;
        float factorY= (float) viewport.getScreenHeight() /9;
        font.draw(scoreBatch, "Times Studied: " + studyCounter, (((float)viewport.getScreenWidth()/2)-factorX), (float) (viewport.getScreenHeight()/2)+factorY);
        font.draw(scoreBatch, "Times Eaten: "+ eatCounter, (((float)viewport.getScreenWidth()/2)-factorX), (float) (viewport.getScreenHeight()/2));
        font.draw(scoreBatch, "Times had Fun: "+ recreationCounter, (((float)viewport.getScreenWidth()/2)-factorX), (float) (viewport.getScreenHeight()/2)-factorY);
        scoreBatch.end();
    }

}
