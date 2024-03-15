package com.mygdx.hustle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//import java.util.concurrent.TimeUnit;

public class GameScreen implements Screen {
    private final HeslingtonHustle parent;
    private final Viewport viewport;
    private final Camera camera;

    SpriteBatch batch;
    private final Rectangle player;
    //Textures for each player movement and direction the player is facing
    //leftForward is left foot first, walking forward
    //rightBack is right foot first, walking backwards
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
    private final Texture backgroundTexture;

    public GameScreen(final HeslingtonHustle heslingtonHustle) {
        parent = heslingtonHustle;

        //map background
        backgroundTexture = new Texture("map.png");

        batch = new SpriteBatch();

        // Initialize the camera and viewport
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);


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

        //creating player from the Rectangle class
        player = new Rectangle();
        player.x = (float) 800 / 2 - (float) 64 / 2;
        player.y = 200;
        player.width = 64;
        player.height = 64;

        // Set the camera's initial position
        camera.position.set(player.x + player.width / 2, player.y + player.height / 2, 0);
        camera.update();
    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(0, 0.5f, 0, 1);
        // Update the viewport and camera
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        camera.update();

        //camera follow player
        camera.position.x = player.getX() + player.getWidth()/2;
        camera.position.y = player.getY() + player.getHeight()/2;
        camera.update();
        // Set the batch's projection matrix to the camera's combined matrix
        batch.setProjectionMatrix(camera.combined);

        //render map
        batch.begin();
        batch.draw(backgroundTexture, -620, -2020, 3000, 3000);
        batch.end();

        boolean noArrowKeyPressed = !(Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
                Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT));

        if (noArrowKeyPressed) {
            batch.begin();
            batch.draw(stillMan, player.x, player.y);
            batch.end();
        }

        //reading the arrow keys and moving the character
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) moveDown();
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)) moveUp();
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) moveLeft();
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveRight();


    }

    public void moveDown() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        // Assuming you have a walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        player.y -= walkingSpeed * deltaTime;

        batch.begin();

        // Draw the appropriate frame based on the walking animation
        if (animationTimer < 0.2) {
            batch.draw(leftForward, player.x, player.y);
        } else if (animationTimer < 0.4) {
            batch.draw(stillMan, player.x, player.y);
        } else {
            batch.draw(rightForward, player.x, player.y);
        }

        batch.end();

        // Increment the animation timer
        animationTimer += deltaTime;

        // Reset the animation timer if it exceeds the total animation time
        if (animationTimer > 0.6) {
            animationTimer = 0;
        }
    }
    public void moveUp() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        player.y += walkingSpeed * deltaTime;

        batch.begin();

        // Draw the appropriate frame based on the walking animation
        if (animationTimer < 0.2) {
            batch.draw(leftBack, player.x, player.y);
        } else if (animationTimer < 0.4) {
            batch.draw(stillBack, player.x, player.y);
        } else {
            batch.draw(rightBack, player.x, player.y);
        }

        batch.end();

        // Increment the animation timer
        animationTimer += deltaTime;

        // Reset the animation timer if it exceeds the total animation time
        if (animationTimer > 0.6) {
            animationTimer = 0;
        }
    }
    public void moveLeft() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        player.x -= walkingSpeed * deltaTime;

        batch.begin();

        // Draw the appropriate frame based on the walking animation
        if (animationTimer < 0.2) {
            batch.draw(leftFootLeft, player.x, player.y);
        } else if (animationTimer < 0.4) {
            batch.draw(stillLeft, player.x, player.y);
        } else {
            batch.draw(rightFootLeft, player.x, player.y);
        }

        batch.end();

        // Increment the animation timer
        animationTimer += deltaTime;

        // Reset the animation timer if it exceeds the total animation time
        if (animationTimer > 0.6) {
            animationTimer = 0;
        }
    }
    public void moveRight() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        // walkingSpeed variable
        float walkingSpeed = 200;

        // Update the player's position based on deltaTime and walkingSpeed
        player.x += walkingSpeed * deltaTime;

        batch.begin();

        // Draw the appropriate frame based on the walking animation
        if (animationTimer < 0.2) {
            batch.draw(leftFootRight, player.x, player.y);
        } else if (animationTimer < 0.4) {
            batch.draw(stillRight, player.x, player.y);
        } else {
            batch.draw(rightFootRight, player.x, player.y);
        }

        batch.end();

        // Increment the animation timer
        animationTimer += deltaTime;

        // Reset the animation timer if it exceeds the total animation time
        if (animationTimer > 0.6) {
            animationTimer = 0;
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        stillMan.dispose();
    }


    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}