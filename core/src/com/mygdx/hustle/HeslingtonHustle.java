package com.mygdx.hustle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class HeslingtonHustle extends Game {

	SpriteBatch batch;
	private boolean muted;

	@Override
	public void create() {
		//for testing, change one of the values in the if statement to go straight to the end screen
		if(1==1) {
			batch = new SpriteBatch();
			OrthographicCamera camera = new OrthographicCamera();
			ExtendViewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
			this.setScreen(new MenuScreen(this, viewport, camera));
		}
		else {
			batch = new SpriteBatch();
			Score scor = new Score();
			OrthographicCamera camera = new OrthographicCamera();
			ExtendViewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
			this.setScreen(new EndScreen(this, viewport, camera, scor));
		}
		//to build ./gradlew desktop:dist in console
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public boolean getMuted(){
		return muted;
	}

	public void setMuted(boolean mute){
		muted = mute;
	}
}