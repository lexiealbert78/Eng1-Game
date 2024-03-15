package com.mygdx.hustle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
	private final HeslingtonHustle parent;
	private final Stage stage;

    public MenuScreen(final HeslingtonHustle heslingtonHustle){
		parent=heslingtonHustle;
		stage=new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

        OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void show(){
		//create a table to fill the screen, then everything else can fit inside
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true);

		//TODO implement ui skin
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

		//create buttons
		TextButton newGame = new TextButton("New Game",skin);
		TextButton preferences = new TextButton("Preferences",skin);
		TextButton exit = new TextButton("Exit",skin);

		//put buttons into the table
		table.add(newGame).fillX().uniformX();
		table.row().pad(10,0,10,0);
		table.add(preferences).fillX().uniformX();
		table.row();
		table.add(exit).fillX().uniformX();
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
				parent.setScreen(new GameScreen(parent));
				dispose();
			}
		});
		newGame.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor){
				parent.setScreen(new GameScreen(parent)); //PREFERENCES
			}
		});
	}
	
	@Override
	public void render(float delta){
		//clear screen
		Gdx.gl.glClearColor(0f,0f,0f,1);
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
