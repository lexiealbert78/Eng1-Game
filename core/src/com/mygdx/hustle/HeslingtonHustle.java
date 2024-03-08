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

	SpriteBatch batch;
	Player player;

	private ShapeRenderer shapeRenderer;
	private Energy energy;
	// Define a boolean flag to track if the button was previously pressed
	private boolean enterPressed = false;
	
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

		// Update the energy bar's current energy level (example: decrease energy with each interaction)
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && !enterPressed){
			// Decrement energy level
			float newEnergyLevel = energy.getCurrentEnergy() - 12.0f;
			// Ensure energy level doesn't go below zero
			newEnergyLevel = Math.max(newEnergyLevel, 0);
			energy.setCurrentEnergy(newEnergyLevel);

			// Set the flag to true to indicate that Enter was pressed
			enterPressed = true;
		}

		// Reset the flag when Enter is released
		if (!Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			enterPressed = false;
		}

		// Draw the energy bar
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		energy.draw(shapeRenderer);
		shapeRenderer.end();

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
