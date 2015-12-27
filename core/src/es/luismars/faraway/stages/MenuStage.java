package es.luismars.faraway.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import es.luismars.faraway.Loader;
import es.luismars.faraway.actors.AnimatedActor;
import es.luismars.faraway.actors.widgets.CustomTextButton;
import es.luismars.faraway.screens.CreatePlayerScreen;
import es.luismars.faraway.screens.ShipScreen;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

/**
 * Created by dalek on 23/12/2015.
 */
public class MenuStage extends CustomStage {

    private Table table;

    public MenuStage() {
        table = new Table();
        table.setFillParent(true);

        Skin skin = Loader.getSkin();

        table.add(new Label("(Far Away)", skin, "title"));
        table.row().expandX();
        table.add(new AnimatedActor(Loader.getAnimation("station"), 0, 0)).space(Value.percentHeight(0.66f));

        table.row().height(20).padTop(4);

        CustomTextButton play = new CustomTextButton("Play", skin);

        table.add(play).width(64);

        table.row().height(20).padTop(4);

        CustomTextButton options = new CustomTextButton("Options", skin);

        table.add(options).width(64);

        addActor(table);


        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen("createplayer");
            }
        });
/*
        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen("createplayer");
            }
        });
*/
    }
}
