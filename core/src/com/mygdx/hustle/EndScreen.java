package com.mygdx.hustle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class EndScreen implements Screen {
    private final HeslingtonHustle parent;
    private final ExtendViewport viewport;
    private final OrthographicCamera camera;
    private final Score score;

    public EndScreen(HeslingtonHustle heslingtonHustle, ExtendViewport view, OrthographicCamera cam, Score scor) {
        parent = heslingtonHustle;
        viewport=view;
        camera=cam;
        score=scor;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        camera.update();

        Stage stage = new Stage();
        stage.draw();
        score.renderFinalScores(viewport);
    }

    @Override
    public void resize(int width, int height) {

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
