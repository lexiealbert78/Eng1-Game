package com.mygdx.hustle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Rectangle {

    //Textures for each player movement and direction the player is facing
    //leftForward is left foot first, walking forward
    //rightBack is right foot first, walking backwards
    private int x;
    private int y;
    private final int width;
    private final int height;
    private final Texture stillMan;
    private final Texture leftForward;
    private final Texture rightForward;
    private final Texture stillBack;
    private final Texture leftBack;
    private final Texture rightBack;
    private final Texture stillLeft;
    private final Texture leftFootLeft;
    private final Texture rightFootLeft;
    private final Texture stillRight;
    private final Texture leftFootRight;
    private final Texture rightFootRight;
    //timer used for walking animation
    private float animationTimer = 0;

    // Constructor
    public Player(SpriteBatch batch) {
        //x = Gdx.graphics.getWidth() /2 - 64/2;
        //y=Gdx.graphics.getHeight() /2 - 64/2;

        x=900;
        y=-400;
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



    public void idle(SpriteBatch batch){
        batch.begin();
        batch.draw(stillMan, this.x, this.y);
        batch.end();
    }

    public Rectangle returnBounds(){
        Rectangle playerBounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
        return playerBounds;
    }

    // Move Down
    public void moveDown(float deltaTime, SpriteBatch batch) {
        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        this.y -= (int) (walkingSpeed * deltaTime);

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
    public void moveUp(float deltaTime, SpriteBatch batch) {

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        this.y += (int) (walkingSpeed * deltaTime);

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
    public void moveLeft(float deltaTime, SpriteBatch batch) {

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        this.x -= (int) (walkingSpeed * deltaTime);

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
    public void moveRight(float deltaTime, SpriteBatch batch) {

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        this.x += (int) (walkingSpeed * deltaTime);

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

    public float getHeight() { return height;}

    public float getWidth() {return width;}

}
