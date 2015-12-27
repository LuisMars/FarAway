package es.luismars.faraway.actors.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import es.luismars.faraway.Loader;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static es.luismars.faraway.Constants.*;
/**
 * Created by dalek on 23/12/2015.
 */
public class MessageBox extends ScrollPane {
    private boolean transition;
    private boolean open;
    private final String text;

    public MessageBox(String text, int x, int y) {
        this(text, x, y, 10, 10);
    }

    public MessageBox(String text, int x, int y, int width, int height) {
        super(null, Loader.getSkin());
        this.text = text;
        Label label = new Label(text, Loader.getSkin());
        label.setWrap(true);

        setFadeScrollBars(false);
        setWidget(label);
        setPosition(x, y);
        setSize(width, height);

        setFlickScroll(false);
        //setScrollingDisabled(true, true);
        getColor().a = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void toggle() {
        if (transition) {
            return;
        }
        if (open) {
            close();
        } else {
            open();
        }
        open = !open;
    }

    public void open() {
        if (transition) {
            return;
        }
        SequenceAction action = new SequenceAction();
        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                transition = true;
            }
        }));

        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                ((Label) getWidget()).setText(text);
            }
        }));


        action.addAction(
                new ParallelAction(
                        sizeTo(TILE_SIZE * 8, TILE_SIZE * 4, FADE_TIME),
                        new TemporalAction(FADE_TIME) {
                            @Override
                            protected void update(float percent) {
                                getWidget().getColor().a = percent;
                                getColor().a = percent;
                            }
                        }
                )
        );
        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                setScrollingDisabled(false, false);
            }
        }));
        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                transition = false;
            }
        }));

        addAction(action);
    }

    public void close() {
        if (transition) {
            return;
        }
        SequenceAction action = new SequenceAction();

        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                transition = true;
            }
        }));
        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                setScrollingDisabled(true, true);
            }
        }));
        action.addAction(sizeTo(10, 10, FADE_TIME));
        action.addAction(new TemporalAction(FADE_TIME) {
            @Override
            protected void update(float percent) {
                getWidget().getColor().a = 1 - percent;
                getColor().a = 1 - percent;
            }
        });
        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                ((Label) getWidget()).setText("");
            }
        }));
        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                transition = false;
            }
        }));

        addAction(action);
    }
}
