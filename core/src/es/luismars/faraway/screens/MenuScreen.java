package es.luismars.faraway.screens;

import es.luismars.faraway.Loader;
import es.luismars.faraway.stages.MenuStage;

/**
 * Created by dalek on 23/12/2015.
 */
public class MenuScreen extends CustomScreen {

    public MenuScreen () {
        super("menu");
        stage = new MenuStage();

        Loader.loadShip();
    }
}
