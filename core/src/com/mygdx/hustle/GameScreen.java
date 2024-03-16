package com.mygdx.hustle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//import java.util.concurrent.TimeUnit;

public class GameScreen implements Screen {
    private final HeslingtonHustle parent;
    private final Viewport viewport;
    private final Camera camera;

    //TODO make sprite batch passed between all classes
    SpriteBatch batch;

    //from lexie's version
    Player player;
    private final Energy energy;
    private final ShapeRenderer shapeRenderer;
    // Define a boolean flag to track if the button was previously pressed
    private boolean enterPressed = false;
    private final DayTimer dayTimer = new DayTimer();
    private final Texture backgroundTexture;

    public GameScreen(final HeslingtonHustle heslingtonHustle, final ExtendViewport view, final OrthographicCamera cam) {
        parent = heslingtonHustle;
        viewport=view;
        camera=cam;

        //map background
        backgroundTexture = new Texture("map.png");

        batch = new SpriteBatch();
        batch.begin();

        //creating player from the Player class
        player = new Player(batch);

        // Set the camera's initial position
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();

        shapeRenderer = new ShapeRenderer();
        energy = new Energy(50, 50, 200, 20, 100); // Adjust position, size, and max energy as needed
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

        //////////////////////////////END OF DAY//////////////////////////////////////////////////

        dayTimer.update(Gdx.graphics.getDeltaTime(), energy);
        dayTimer.renderDayNumber(); // Render the day number above the energy bar

        //////////////////////////////INTERACTIONS//////////////////////////////////////////////////////////////

        // Update the energy bar's current energy level (example: decrease energy with each interaction)
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && !enterPressed){
            energy.decrementEnergy();
            enterPressed = true; //Sets flag so button cannot be held and all energy used
        }

        // Reset the flag when Enter is released
        if (!Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            enterPressed = false;
        }
        energy.drawBar(shapeRenderer);

        //////////////////////////////MOVEMENT//////////////////////////////////////////////////////////////////

        boolean noArrowKeyPressed = !(Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
                Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT));

        if (noArrowKeyPressed) {
            player.idle(batch);
        }

        //reading the arrow keys and moving the character
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.moveDown(Gdx.graphics.getDeltaTime(), batch);
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)) player.moveUp(Gdx.graphics.getDeltaTime(), batch);
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.moveLeft(Gdx.graphics.getDeltaTime(), batch);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.moveRight(Gdx.graphics.getDeltaTime(), batch);


    }

    @Override
    public void dispose () {
        batch.dispose();
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
