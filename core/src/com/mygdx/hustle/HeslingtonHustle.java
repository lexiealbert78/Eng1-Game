package com.mygdx.hustle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HeslingtonHustle extends Game {

	SpriteBatch batch;
	@Override
	public void create () {
		batch=new SpriteBatch();
		this.setScreen(new MenuScreen(this));
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
