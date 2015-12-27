package es.luismars.faraway.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static es.luismars.faraway.Constants.*;

/**
 * Created by dalek on 26/12/2015.
 */
public class AnimatedActor extends Actor {

    private final Animation animation;

    private float stateTime;

    public AnimatedActor(Animation animation, int x, int y) {
        this.animation = animation;
        TextureRegion region = animation.getKeyFrame(0);
        setBounds(x * TILE_SIZE, y * TILE_SIZE, region.getRegionWidth(), region.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
        batch.draw(animation.getKeyFrame(stateTime), getX(), getY(), getWidth(), getHeight());
    }
}
