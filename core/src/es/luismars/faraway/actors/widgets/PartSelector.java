package es.luismars.faraway.actors.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import es.luismars.faraway.Loader;

/**
 * Created by dalek on 24/12/2015.
 */
public class PartSelector extends Table {

    private Label label;

    public PartSelector(String part, Skin skin) {
        super();

        CustomButton leftButton = new CustomButton(skin, "arrowLeft");
        label = new Label(part, skin, "slim");
        CustomButton rightButton = new CustomButton(skin, "arrowRight");

        leftButton.setName("left");
        rightButton.setName("right");

        label.setAlignment(Align.center);
        add(leftButton);
        add(label).prefWidth(64);
        add(rightButton);
        center();

    }

    public String getText() {
        return String.valueOf(label.getText());
    }

    public void setText(String text) {
        label.setText(text);
    }
}
