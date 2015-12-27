package es.luismars.faraway.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import es.luismars.faraway.Constants;
import es.luismars.faraway.Loader;
import static es.luismars.faraway.CharacterConstants.*;
/**
 * Created by dalek on 21/12/2015.
 */
public class Character extends Actor {

    private Rectangle world;
    private boolean male;
    private int body;
    private int legs;
    private int boots;
    private int skinImg;
    private int hair;
    private TextureRegion[] parts;

    private boolean flipped;

    private Vector2 acceleration, position, speed;
    private boolean stoppingX;

    private boolean floating;
    private float floatingTimer;


    public Character(boolean male, int body, int legs, int boots, int skinImg, int hair) {
        this.male = male;
        this.body = body;
        this.legs = legs;
        this.boots = boots;
        this.skinImg = skinImg;
        this.hair = hair;
    }

    public void initialize(Rectangle world, int x) {
        initialize(world, x, 0.75f);
    }

    public void initialize(Rectangle world, float x, float y) {
        this.world = world;

        parts = new TextureRegion[5];

        parts[BODY] = Loader.getPart(male, BODY, body);
        parts[HAIR] = Loader.getPart(male, HAIR, hair);
        parts[BOOTS] = Loader.getPart(male, BOOTS, boots);
        parts[SKIN] = Loader.getPart(male, SKIN, skinImg);
        parts[LEGS] = Loader.getPart(male, LEGS, legs);

        setBounds(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE * 2);
        position = new Vector2(getX(), getY());
        speed = new Vector2();
        acceleration = new Vector2();
        floating = true;
    }

    @Override
    public void act(float delta) {
        super.act(delta);


        if (stoppingX) {
            speed.x *= 1 - delta;
            if (MathUtils.isZero(speed.x, 1)) {
                speed.x = 0;
            }
        } else {
            speed.mulAdd(acceleration, delta);
            speed.x = MathUtils.clamp(speed.x, -Constants.SPACE_MAX_SPEED, Constants.SPACE_MAX_SPEED);
        }

        position.mulAdd(speed, delta);

        if (floating) {
            floatingTimer += delta * Constants.FLOATING_RATE;
            position.y += MathUtils.cos(floatingTimer) * Constants.FLOATING_RANGE;
        }

        checkOuterBounds();

        updatePosition(position);
    }

    private void checkOuterBounds() {
        if (position.x < world.x) {
            position.x = world.x;
            stopX();
            speed.x = 0;
        } else if (position.x + getWidth() > world.x + world.getWidth()) {
            position.x = world.x + world.getWidth() - getWidth();
            stopX();
            speed.x = 0;
        }

        if (position.y < world.y) {
            position.y = world.y;
        } else if (position.y + getHeight() > world.y + world.getHeight()) {
            position.y = world.y + world.getHeight() - getHeight();
        }
    }

    public void moveLeft() {
        stoppingX = false;
        acceleration.x = -Constants.SPACE_ACCELERATION;
    }

    public void moveRight() {
        stoppingX = false;
        acceleration.x = Constants.SPACE_ACCELERATION;
    }

    public void stopX() {
        stoppingX = true;
        acceleration.x = 0;
    }

    public void updatePosition(Vector2 position) {
        setPosition((int) position.x, (int) position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (flipped != speed.x < 0 && speed.x != 0) {
            flipped = !flipped;
            for (TextureRegion part : parts) {
                part.flip(true, false);
            }
        }
        batch.setColor(getColor());
        for (TextureRegion part : parts) {
            batch.draw(part, getX(), getY(), getWidth(), getHeight());
        }
    }
}
