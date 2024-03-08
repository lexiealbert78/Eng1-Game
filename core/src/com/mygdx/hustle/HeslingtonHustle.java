package com.mygdx.hustle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.concurrent.TimeUnit;

public class HeslingtonHustle extends ApplicationAdapter {

	SpriteBatch batch;
	Player player;
	
	@Override
	public void create () {
		//creating player from the Player class
		player = new Player();

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0.5f, 0, 1);


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
