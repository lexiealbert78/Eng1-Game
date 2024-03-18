package com.mygdx.hustle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MenuScreen implements Screen {
	private final HeslingtonHustle parent;
	private final Stage stage;
	private final Texture backgroundTexture;
	private final ExtendViewport viewport;
	private final OrthographicCamera camera;

    public MenuScreen(final HeslingtonHustle heslingtonHustle, final ExtendViewport view, final OrthographicCamera cam){
		parent=heslingtonHustle;
		viewport=view;
		camera=cam;

		backgroundTexture = new Texture("menu.png");

		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage=new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show(){
		//create a table to fill the screen, then everything else can fit inside
		Table table = new Table();
		table.setFillParent(true);

		//TODO implement ui skin
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

		//create buttons
		TextButton newGame = new TextButton("New Game",skin);
		TextButton preferences = new TextButton("Preferences",skin);
		TextButton exit = new TextButton("Exit",skin);

		//put buttons into the table
		table.row().pad(20,0,0,0);
		table.add(newGame).fillX().width(200).height(50);
		table.row().pad(20,0, 0,0);
		table.add(preferences).fillX().width(200).height(50);
		table.row().pad(20,0, 0,0);
		table.add(exit).fillX().width(200).height(50);

		//final table edits
		TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(backgroundTexture));
		table.setBackground(textureRegionDrawableBg);
		stage.addActor(table);

		//button listeners
		exit.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor){
				Gdx.app.exit();
			}
		});
		preferences.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor){
				parent.setScreen(new GameScreen(parent, viewport, camera));
				dispose();
			}
		});
		newGame.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor){
				parent.setScreen(new GameScreen(parent, viewport, camera)); //PREFERENCES
			}
		});
	}
	
	@Override
	public void render(float delta){
		//clear screen
		//Gdx.gl.glClearColor(50/255f, 80/255f, 230/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width,height,true);
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

	@Override
	public void dispose() {

	}
}
