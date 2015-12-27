package es.luismars.faraway.screens;

import es.luismars.faraway.Loader;
import es.luismars.faraway.stages.CreatePlayerStage;


/**
 * Created by dalek on 24/12/2015.
 */
public class CreatePlayerScreen extends CustomScreen {

    public CreatePlayerScreen() {
        super("createplayer");
        stage = new CreatePlayerStage();

        Loader.loadShip();
    }
}
