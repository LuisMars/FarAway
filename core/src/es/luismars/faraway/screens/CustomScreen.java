package es.luismars.faraway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import es.luismars.faraway.Constants;
import es.luismars.faraway.Loader;
import es.luismars.faraway.stages.CustomStage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

/**
 * Created by dalek on 21/12/2015.
 */
public class CustomScreen implements Screen {

    protected CustomStage stage;
    private String name;

    public CustomScreen(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Loader.playSound("open");
        stage.getRoot().setPosition(-Constants.WIDTH, 0);
        stage.getRoot().addAction(moveBy(Constants.WIDTH, 0, Constants.FADE_TIME));
    }

    @Override
    public void render(float delta) {
        stage.act();

        Gdx.gl.glClearColor(0.13f, 0.11f, 0.23f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.resize(width, height);
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
        stage.dispose();
    }

    public String getName() {
        return name;
    }
}
