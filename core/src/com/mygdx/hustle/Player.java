package com.mygdx.hustle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Rectangle {
    SpriteBatch batch;
    //Textures for each player movement and direction the player is facing
    //leftForward is left foot first, walking forward
    //rightBack is right foot first, walking backwards
    private int x;
    private int y;
    private int width;
    private int height;
    private Texture stillMan;
    private Texture leftForward;
    private Texture rightForward;
    private Texture stillBack;
    private Texture leftBack;
    private Texture rightBack;
    private Texture stillLeft;
    private Texture leftFootLeft;
    private Texture rightFootLeft;
    private Texture stillRight;
    private Texture leftFootRight;
    private Texture rightFootRight;
    //timer used for walking animation
    private float animationTimer = 0;

    // Constructor
    public Player() {
        x = 800 / 2 - 64 / 2;
        y=200;
        width = 64;
        height = 64;
        batch = new SpriteBatch();

        batch.begin();
        //initialising each texture with image of avatar moving
        stillMan = new Texture(Gdx.files.internal("still man.png"));
        leftForward = new Texture(Gdx.files.internal("left forward.png"));
        rightForward = new Texture(Gdx.files.internal("right forward.png"));
        stillBack = new Texture(Gdx.files.internal("still back.png"));
        leftBack = new Texture(Gdx.files.internal("left back.png"));
        rightBack = new Texture(Gdx.files.internal("right back.png"));
        stillLeft = new Texture(Gdx.files.internal("still left.png"));
        leftFootLeft = new Texture(Gdx.files.internal("leftFoot left.png"));
        rightFootLeft = new Texture(Gdx.files.internal("rightFoot left.png"));
        stillRight = new Texture(Gdx.files.internal("still right.png"));
        leftFootRight = new Texture(Gdx.files.internal("leftFoot right.png"));
        rightFootRight = new Texture(Gdx.files.internal("rightFoot right.png"));
        batch.end();

    }



    public void idle(){
        batch.begin();
        batch.draw(stillMan, this.x, this.y);
        batch.end();
    }
    // Move Down
    public void moveDown(float deltaTime) {
        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        this.y -= walkingSpeed * deltaTime;

        batch.begin();

        // Draw the appropriate frame based on the walking animation
        if (animationTimer < 0.2) {
            batch.draw(leftForward, this.x, this.y);
        } else if (animationTimer < 0.4) {
            batch.draw(stillMan, this.x, this.y);
        } else {
            batch.draw(rightForward, this.x, this.y);
        }

        batch.end();

        // Increment the animation timer
        animationTimer += deltaTime;

        // Reset the animation timer if it exceeds the total animation time
        if (animationTimer > 0.6) {
            animationTimer = 0;
        }
    }
    // Move up
    public void moveUp(float deltaTime) {

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        this.y += walkingSpeed * deltaTime;

        batch.begin();

        // Draw the appropriate frame based on the walking animation
        if (animationTimer < 0.2) {
            batch.draw(leftBack, this.x, this.y);
        } else if (animationTimer < 0.4) {
            batch.draw(stillBack, this.x, this.y);
        } else {
            batch.draw(rightBack, this.x, this.y);
        }

        batch.end();

        // Increment the animation timer
        animationTimer += deltaTime;

        // Reset the animation timer if it exceeds the total animation time
        if (animationTimer > 0.6) {
            animationTimer = 0;
        }
    }
    public void moveLeft(float deltaTime) {

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        this.x -= walkingSpeed * deltaTime;

        batch.begin();

        // Draw the appropriate frame based on the walking animation
        if (animationTimer < 0.2) {
            batch.draw(leftFootLeft, this.x, this.y);
        } else if (animationTimer < 0.4) {
            batch.draw(stillLeft, this.x, this.y);
        } else {
            batch.draw(rightFootLeft, this.x, this.y);
        }

        batch.end();

        // Increment the animation timer
        animationTimer += deltaTime;

        // Reset the animation timer if it exceeds the total animation time
        if (animationTimer > 0.6) {
            animationTimer = 0;
        }
    }
    public void moveRight(float deltaTime) {

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        this.x += walkingSpeed * deltaTime;

        batch.begin();

        // Draw the appropriate frame based on the walking animation
        if (animationTimer < 0.2) {
            batch.draw(leftFootRight, this.x, this.y);
        } else if (animationTimer < 0.4) {
            batch.draw(stillRight, this.x, this.y);
        } else {
            batch.draw(rightFootRight, this.x, this.y);
        }

        batch.end();

        // Increment the animation timer
        animationTimer += deltaTime;

        // Reset the animation timer if it exceeds the total animation time
        if (animationTimer > 0.6) {
            animationTimer = 0;
        }
    }

    // Getter method for x coordinate
    public float getX() {
        return x;
    }

    // Getter method for y coordinate
    public float getY() {
        return y;
    }

}
