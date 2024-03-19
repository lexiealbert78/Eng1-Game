package com.mygdx.hustle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

//import java.util.concurrent.TimeUnit;

public class GameScreen implements Screen{
    private final HeslingtonHustle parent;
    private final ExtendViewport viewport;
    private final OrthographicCamera camera;

    SpriteBatch batch;

    //from lexie's version
    Player player;
    private final Energy energy;
    private final ShapeRenderer shapeRenderer;
    // Define a boolean flag to track if the button was previously pressed
    private boolean enterPressed = false;
    private final DayTimer dayTimer = new DayTimer();
    private final Score score = new Score();

    private final AccommodationBuilding accommodationBuilding = new AccommodationBuilding(-100,350,600,550);
    private final RecreationBuilding recreationBuilding = new RecreationBuilding(528, -1252, 692, 467);
    private final StudyBuilding studyBuilding = new StudyBuilding(-253,-1901,489,511);
    private final EatBuilding eatBuilding = new EatBuilding(1525,-895,697,490);
    private final Texture backgroundTexture;
    private final BitmapFont font = new BitmapFont();
    private final KeyboardController controller = new KeyboardController();
    SpriteBatch textBatch;



    public GameScreen(final HeslingtonHustle heslingtonHustle, final ExtendViewport view, final OrthographicCamera cam) {
        parent = heslingtonHustle;
        viewport=view;
        camera=cam;

        //controller
        Gdx.input.setInputProcessor(controller);

        //map background
        backgroundTexture = new Texture("map.png");

        //creating player from the Player class
        batch = new SpriteBatch();
        batch.begin();
        player = new Player(batch);
        batch.end();

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
            camera.position.x = player.getX() + player.getWidth() / 2;
            camera.position.y = player.getY() + player.getHeight() / 2;
            camera.update();
            // Set the batch's projection matrix to the camera's combined matrix
            batch.setProjectionMatrix(camera.combined);

            //render map
            batch.begin();
            batch.draw(backgroundTexture, -620, -2020, 3000, 3000);
            batch.end();

            //////////////////////////////END OF DAY//////////////////////////////////////////////////

            dayTimer.update(Gdx.graphics.getDeltaTime(), energy, parent, viewport, camera, score);
            dayTimer.renderDayNumber(); // Render the day number above the energy bar


            //////////////////////////////INTERACTIONS//////////////////////////////////////////////////////////////

            //set font color and scale
            font.setColor(1, 1, 1, 1); // White color
            font.getData().setScale(1.5f); // Scale factor 1.5


            //draw interact text
            if (accommodationBuilding.checkOverlaps(player)) {
                textBatch = new SpriteBatch();
                textBatch.begin();
                font.draw(textBatch, "Go to sleep?", (float) Gdx.graphics.getWidth() / 2 + 50, (float) Gdx.graphics.getHeight() / 2 + 50);
                textBatch.end();
            }

            if (recreationBuilding.checkOverlaps(player) && energy.getCurrentEnergy() > 10) {
                textBatch = new SpriteBatch();
                textBatch.begin();
                font.draw(textBatch, "Do recreational activity?", (float) Gdx.graphics.getWidth() / 2 + 50, (float) Gdx.graphics.getHeight() / 2 + 50);
                textBatch.end();

            }

            if (studyBuilding.checkOverlaps(player) && energy.getCurrentEnergy() > 25) {
                textBatch = new SpriteBatch();
                textBatch.begin();
                font.draw(textBatch, "Study for a bit?", (float) Gdx.graphics.getWidth() / 2 + 50, (float) Gdx.graphics.getHeight() / 2 + 50);
                textBatch.end();
            }

            if (eatBuilding.checkOverlaps(player)) {
                textBatch = new SpriteBatch();
                textBatch.begin();
                font.draw(textBatch, "Eat a meal?", (float) Gdx.graphics.getWidth() / 2 + 50, (float) Gdx.graphics.getHeight() / 2 + 50);
                textBatch.end();
            }


            // Update the energy bar's current energy level (example: decrease energy with each interaction)
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && !enterPressed) {
                enterPressed = true; //Sets flag so button cannot be held and all energy used
            }

            // Reset the flag when Enter is released
            if (!Gdx.input.isKeyPressed(Input.Keys.ENTER) && enterPressed) {
                enterPressed = false;

                //check if overlapping any buildings

                if (accommodationBuilding.checkOverlaps(player)) {
                    accommodationBuilding.interact(energy, dayTimer, score);
                }

                if (recreationBuilding.checkOverlaps(player)) {
                    recreationBuilding.interact(energy, dayTimer, score);
                }

                if (studyBuilding.checkOverlaps(player)) {
                    studyBuilding.interact(energy, dayTimer, score);
                }

                if (eatBuilding.checkOverlaps(player)) {
                    eatBuilding.interact(energy, dayTimer, score);
                }
            }
            energy.drawBar(shapeRenderer);
            score.renderScores();

            //////////////////////////////MOVEMENT//////////////////////////////////////////////////////////////////

            //TODO: add diagonal animations if you want when pressing two movement directions
            //single button pressed
            if (controller.up) player.moveUp(Gdx.graphics.getDeltaTime(), batch);
            else if (controller.left) player.moveLeft(Gdx.graphics.getDeltaTime(), batch);
            else if (controller.down) player.moveDown(Gdx.graphics.getDeltaTime(), batch);
            else if (controller.right) player.moveRight(Gdx.graphics.getDeltaTime(), batch);
            //idle
            else player.idle(batch);

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

//    @Override
//    public boolean keyDown(int keycode) {
//        //reading the keys and moving the character
//        if (keycode == 37||keycode == 65) player.moveLeft(Gdx.graphics.getDeltaTime(), batch); //left=37, a=65
//        else if (keycode == 38||keycode == 87) player.moveUp(Gdx.graphics.getDeltaTime(), batch); //up=38, w=87
//        else if (keycode == 39||keycode == 68) player.moveRight(Gdx.graphics.getDeltaTime(), batch); //right=39, d=68
//        else if (keycode == 40||keycode == 83) player.moveDown(Gdx.graphics.getDeltaTime(), batch); //down=40, s=83
//        return false;
//    }
}
