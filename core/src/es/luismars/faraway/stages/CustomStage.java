package es.luismars.faraway.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import es.luismars.faraway.Constants;
import es.luismars.faraway.FarAway;
import es.luismars.faraway.screens.CustomScreen;

import static es.luismars.faraway.Constants.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * Created by dalek on 21/12/2015.
 */
public class CustomStage extends Stage {

    protected boolean canZoom;

    public CustomStage() {
        super(new ExtendViewport(WIDTH, HEIGHT));
    }

    @Override
    public boolean scrolled(int amount) {
        if (canZoom) {
            float zoom = ((OrthographicCamera) getCamera()).zoom;
            float step = amount > 0 ? 0.125f : - 0.125f;

            zoom += step;
            ((OrthographicCamera) getCamera()).zoom = MathUtils.clamp(zoom, 0.5f, 2);
        }
        return true;
    }

    protected void lookAtCell(int x, int y) {
        lookAt((x + 0.5f) * TILE_SIZE, (y + 0.5f) * TILE_SIZE);
    }

    protected void lookAt(float x, float y) {
        getCamera().position.set(x, y, 0);
    }

    public void resize(int width, int height) {
        getViewport().update(width, height);
    }

    public void changeScreen(final CustomScreen screen) {
        SequenceAction sequenceAction = new SequenceAction();

        sequenceAction.addAction(moveTo(WIDTH, 0, FADE_TIME));
        sequenceAction.addAction(run(new Runnable() {
            @Override
            public void run() {
                ((FarAway) Gdx.app.getApplicationListener()).setScreen(screen);
            }
        }));
        getRoot().addAction(sequenceAction);
    }

    public void changeScreen(final String screen) {

        SequenceAction sequenceAction = new SequenceAction();

        sequenceAction.addAction(moveTo(WIDTH, 0, FADE_TIME));
        sequenceAction.addAction(run(new Runnable() {
            @Override
            public void run() {
                ((FarAway) Gdx.app.getApplicationListener()).setScreen(screen);
            }
        }));
        getRoot().addAction(sequenceAction);
    }

    public void setInvisible() {
        getRoot().getColor().a = 0;
    }
}
