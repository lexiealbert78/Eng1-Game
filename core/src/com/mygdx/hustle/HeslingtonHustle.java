package com.mygdx.hustle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.concurrent.TimeUnit;

public class HeslingtonHustle extends ApplicationAdapter {
	///////////////////////////////////////////////////////////////////////////////////
	private Viewport viewport;
	private Camera camera;
	private Texture backgroundTexture;
	SpriteBatch batch;
	/////////////////////////////////////////////////////////////////////////////////////
	Player player;


	private ShapeRenderer shapeRenderer;
	private Energy energy;
	// Define a boolean flag to track if the button was previously pressed
	private boolean enterPressed = false;

	private DayTimer dayTimer = new DayTimer();
	private Score score = new Score();

	private AccommodationBuilding accommodationBuilding = new AccommodationBuilding(-100,350,600,550);
	private RecreationBuilding recreationBuilding = new RecreationBuilding(600, -700, 500, 400);

	
	@Override
	public void create () {
		///////////////////////////////MAP//////////////////////////////////////////
		batch = new SpriteBatch();
		//map background
		batch.begin();
		backgroundTexture = new Texture("map.png");
		// Initialize the camera and viewport
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		batch.end();

		//////////////////////////////PLAYER, ENERGY BAR CREATION/////////////////////////////////////////////

		//creating player from the Player class
		player = new Player(batch);

		// Set the camera's initial position
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();

		shapeRenderer = new ShapeRenderer();
		energy = new Energy(50, 50, 200, 20, 100); // Adjust position, size, and max energy as needed
	}


	@Override
	public void render () {
		ScreenUtils.clear(0, 0.5f, 0, 1);
		///////////////////////////CAMERA/////////////////////////////////////////////////////
		// Update the viewport and camera
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		camera.update();

		//camera follow player
		camera.position.x = player.getX() +player.getWidth()/2;
		camera.position.y = player.getY() + player.getHeight()/2;
		camera.update();
		// Set the batch's projection matrix to the camera's combined matrix
		batch.setProjectionMatrix(camera.combined);

		//render map
		batch.begin();
		batch.draw(backgroundTexture, -250, -1200, 2250, 2250);
		batch.end();
		//////////////////////////////END OF DAY//////////////////////////////////////////////////

		dayTimer.update(Gdx.graphics.getDeltaTime(), energy);
		dayTimer.renderDayNumber(); // Render the day number above the energy bar

		//////////////////////////////INTERACTIONS//////////////////////////////////////////////////////////////

		// Update the energy bar's current energy level (example: decrease energy with each interaction)
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && !enterPressed){
			//check if overlapping any buildings

			if(accommodationBuilding.checkOverlaps(player)){
				accommodationBuilding.interact(energy, dayTimer, score);
			}

			if(recreationBuilding.checkOverlaps(player)){
				recreationBuilding.interact(energy, dayTimer, score);
			}

			//energy.decrementEnergy();
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

}
