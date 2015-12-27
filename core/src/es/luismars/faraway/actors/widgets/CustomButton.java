package es.luismars.faraway.actors.widgets;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import es.luismars.faraway.Loader;

/**
 * Created by dalek on 26/12/2015.
 */
public class CustomButton extends Button {

    public CustomButton (Skin skin, String style) {
        super(skin, style);
        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Loader.playSound("select");
            }
        });
    }
}
