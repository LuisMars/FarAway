package es.luismars.faraway;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import es.luismars.faraway.screens.CreatePlayerScreen;
import es.luismars.faraway.screens.CustomScreen;
import es.luismars.faraway.screens.MenuScreen;
import es.luismars.faraway.screens.ShipScreen;

import java.util.HashMap;
import java.util.Map;

public class FarAway extends Game {

    Map<String, Screen> screens;

    @Override
    public void create() {

        screens = new HashMap<String, Screen>();

        screens.put("mainmenu", new MenuScreen());
        screens.put("createplayer", new CreatePlayerScreen());

        setScreen(screens.get("mainmenu"));
    }

    public void setScreen(CustomScreen screen) {
        if (!screens.containsKey(screen.getName())) {
            screens.put(screen.getName(), screen);
        }
        super.setScreen(screen);
    }

    public void setScreen(String screen) {
        setScreen(screens.get(screen));
    }
}
