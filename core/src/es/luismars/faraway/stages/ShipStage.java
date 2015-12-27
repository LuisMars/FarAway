package es.luismars.faraway.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import es.luismars.faraway.*;
import es.luismars.faraway.actors.Character;
import es.luismars.faraway.actors.widgets.MessageBox;

import static es.luismars.faraway.Constants.*;

/**
 * Created by dalek on 21/12/2015.
 */
public class ShipStage extends CustomStage {

    private OrthogonalTiledMapRenderer mapRenderer;
    private int mapWidth, mapHeight;

    private Rectangle bounds;

    private Character player;

    private MessageBox messageBox;

    private boolean debug;

    public ShipStage(Character player) {
        super();
        canZoom = true;

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("testmap.tmx");

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        mapWidth = map.getProperties().get("width", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class);

        bounds = new Rectangle(0, 0, mapWidth * TILE_SIZE, mapHeight * TILE_SIZE);

        centerCameraOnMap();

        this.player = player;
        this.player.initialize(bounds, mapWidth / 2);
        addActor(this.player);

        messageBox = new MessageBox("test text\nimportant stuff\n\ntest    test", 11 * TILE_SIZE, (mapHeight - 1) * TILE_SIZE);
        addActor(messageBox);
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode) {
            case Keys.D:
                player.moveRight();
                break;
            case Keys.A:
                player.moveLeft();
                break;
            case Keys.SPACE:
                messageBox.toggle();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {
        switch (keyCode) {
            case Keys.D:
                if (!Gdx.input.isKeyPressed(Keys.A)) {
                    player.stopX();
                } else {
                    player.moveLeft();
                }
                break;
            case Keys.A:
                if (!Gdx.input.isKeyPressed(Keys.D)) {
                    player.stopX();
                } else {
                    player.moveRight();
                }
                break;
        }
        return true;
    }

    protected void centerCameraOnMap() {
        lookAtCell(mapWidth / 2, mapHeight / 2);
    }

    protected void lookAt(Character character) {
        float x = character.getX() + character.getWidth() / 2;
        float y = (mapHeight / 2 + 1.5f) * TILE_SIZE;
        lookAt(x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        lookAt(player);
    }

    @Override
    public void draw() {
        Camera camera = getViewport().getCamera();
        camera.update();

        mapRenderer.setView((OrthographicCamera) camera);
        mapRenderer.getBatch().setColor(getRoot().getColor());

        mapRenderer.render();
        super.draw();
    }
}
