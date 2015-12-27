package es.luismars.faraway.screens;

import com.badlogic.gdx.Gdx;
import es.luismars.faraway.Constants;
import es.luismars.faraway.Loader;
import es.luismars.faraway.actors.Character;
import es.luismars.faraway.stages.ShipStage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by dalek on 23/12/2015.
 */
public class ShipScreen extends CustomScreen {

    public ShipScreen(Character player) {
        super("ship");
        Loader.loadShip();
        stage = new ShipStage(player);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        stage.setInvisible();
        stage.getRoot().addAction(fadeIn(Constants.FADE_TIME));

    }
}
