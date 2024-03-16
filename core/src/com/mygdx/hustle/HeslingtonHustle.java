package com.mygdx.hustle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class HeslingtonHustle extends Game {

	SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		OrthographicCamera camera = new OrthographicCamera();
		ExtendViewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		this.setScreen(new MenuScreen(this, viewport, camera));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}