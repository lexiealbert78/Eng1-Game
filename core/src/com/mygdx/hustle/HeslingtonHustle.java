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

	Player player;

	private ShapeRenderer shapeRenderer;
	private Energy energy;
	// Define a boolean flag to track if the button was previously pressed
	private boolean enterPressed = false;

	private DayTimer dayTimer = new DayTimer();
	
	@Override
	public void create () {
		//creating player from the Player class
		player = new Player();


		shapeRenderer = new ShapeRenderer();
		energy = new Energy(50, 50, 200, 20, 100); // Adjust position, size, and max energy as needed
	}


	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		// Update the day timer
		if(dayTimer.update(Gdx.graphics.getDeltaTime())){
			//end of day
			energy.resetEnergy();
			//possibly reset screen, move character position back to home

		}
		//dayTimer.update(Gdx.graphics.getDeltaTime());
		dayTimer.renderDayNumber(); // Render the day number above the energy bar

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

		boolean noArrowKeyPressed = !(Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
				Gdx.input.isKeyPressed(Input.Keys.UP) ||
				Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
				Gdx.input.isKeyPressed(Input.Keys.RIGHT));

		if (noArrowKeyPressed) {
			player.idle();
		}

		//reading the arrow keys and moving the character
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.moveDown(Gdx.graphics.getDeltaTime());
		else if(Gdx.input.isKeyPressed(Input.Keys.UP)) player.moveUp(Gdx.graphics.getDeltaTime());
		else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.moveLeft(Gdx.graphics.getDeltaTime());
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.moveRight(Gdx.graphics.getDeltaTime());


	}

}
