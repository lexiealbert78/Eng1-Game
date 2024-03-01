package com.mygdx.hustle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class HeslingtonHustle extends ApplicationAdapter {
	SpriteBatch batch;
	private Rectangle player;
	private Texture stillMan;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stillMan = new Texture(Gdx.files.internal("still man.png"));

		player = new Rectangle();
		player.x = 800 / 2 - 64 / 2;
		player.y = 200;
		player.width = 64;
		player.height = 64;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0.5f, 0, 1);
		batch.begin();
		batch.draw(stillMan, player.x, player.y);
		batch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.y -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) player.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x += 200 * Gdx.graphics.getDeltaTime();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		stillMan.dispose();
	}
}
