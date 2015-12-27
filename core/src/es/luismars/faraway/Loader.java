package es.luismars.faraway;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

import static es.luismars.faraway.Constants.*;
import static es.luismars.faraway.CharacterConstants.*;

/**
 * Created by dalek on 21/12/2015.
 */
public class Loader {

    private static boolean shipLoaded = false;
    private static TextureRegion player;
    private static TextureRegion[][] maleParts, femaleParts;
    private static Skin skin;

    private static Map<String, Sound> sounds;
    private static Map<String, Animation> animations;

    private static float frame = 0.016666668f;

    public static void loadShip() {
        if (shipLoaded) return;

        skin = new Skin(Gdx.files.internal("gui.json"));

        loadMaleParts();

        loadFemaleParts();

        animations = new HashMap<String, Animation>();

        Texture stationTexture = new Texture("jamStation.png");
        TextureRegion[] frames = TextureRegion.split(stationTexture, TILE_SIZE * 3, TILE_SIZE * 2)[0];
        Animation station = new Animation(5 * frame, Array.with(frames), Animation.PlayMode.LOOP);
        animations.put("station", station);

        sounds = new HashMap<String, Sound>();

        sounds.put("select", Gdx.audio.newSound(Gdx.files.getFileHandle("Select.wav", Files.FileType.Internal)));
        sounds.put("open", Gdx.audio.newSound(Gdx.files.getFileHandle("Open.wav", Files.FileType.Internal)));
        sounds.put("start", Gdx.audio.newSound(Gdx.files.getFileHandle("Start.wav", Files.FileType.Internal)));

        shipLoaded = true;
    }

    private static void loadFemaleParts() {
        TextureRegion[] tmp;
        TextureRegion[][] femalePartsMatrix = new TextureRegion(new Texture("jamCharsetFemale.png")).split(TILE_SIZE, TILE_SIZE * 2);
        player = femalePartsMatrix[5][0];

        femaleParts = new TextureRegion[5][15];


        //body
        tmp = new TextureRegion[FEMALE_BODY_LENGTH];
        System.arraycopy(femalePartsMatrix[0], 0, tmp, 0, FEMALE_BODY_LENGTH);
        femaleParts[BODY] = tmp;

        //legs
        tmp = new TextureRegion[FEMALE_LEGS_LENGTH];
        System.arraycopy(femalePartsMatrix[1], 0, tmp, 0, FEMALE_LEGS_LENGTH);
        femaleParts[LEGS] = tmp;

        //boots
        tmp = new TextureRegion[FEMALE_BOOTS_LENGTH];
        System.arraycopy(femalePartsMatrix[2], 0, tmp, 0, FEMALE_BOOTS_LENGTH);
        femaleParts[BOOTS] = tmp;

        //skin
        tmp = new TextureRegion[FEMALE_SKIN_LENGTH];
        System.arraycopy(femalePartsMatrix[3], 0, tmp, 0, FEMALE_SKIN_LENGTH);
        femaleParts[SKIN] = tmp;

        //hair
        tmp = new TextureRegion[FEMALE_HAIR_LENGTH];
        System.arraycopy(femalePartsMatrix[4], 0, tmp, 0, FEMALE_HAIR_LENGTH);
        femaleParts[HAIR] = tmp;
    }

    private static void loadMaleParts() {
        TextureRegion[][] malePartsMatrix = new TextureRegion(new Texture("jamCharsetMale.png")).split(TILE_SIZE, TILE_SIZE * 2);
        player = malePartsMatrix[5][0];


        maleParts = new TextureRegion[5][15];

        TextureRegion[] tmp;
        //body
        tmp = new TextureRegion[MALE_BODY_LENGTH];
        System.arraycopy(malePartsMatrix[0], 0, tmp, 0, MALE_BODY_LENGTH);
        maleParts[BODY] = tmp;

        //legs
        tmp = new TextureRegion[MALE_LEGS_LENGTH];
        System.arraycopy(malePartsMatrix[1], 0, tmp, 0, MALE_LEGS_LENGTH);
        maleParts[LEGS] = tmp;

        //boots
        tmp = new TextureRegion[MALE_BOOTS_LENGTH];
        System.arraycopy(malePartsMatrix[2], 0, tmp, 0, MALE_BOOTS_LENGTH);
        maleParts[BOOTS] = tmp;

        //skin
        tmp = new TextureRegion[MALE_SKIN_LENGTH];
        System.arraycopy(malePartsMatrix[3], 0, tmp, 0, MALE_SKIN_LENGTH);
        maleParts[SKIN] = tmp;

        //hair
        tmp = new TextureRegion[MALE_HAIR_LENGTH];
        System.arraycopy(malePartsMatrix[4], 0, tmp, 0, MALE_HAIR_LENGTH);
        maleParts[HAIR] = tmp;
    }

    public static TextureRegion getPlayer() {
        if (!shipLoaded) {
            loadShip();
        }
        return player;
    }

    public static Skin getSkin() {
        if (!shipLoaded) {
            loadShip();
        }
        return skin;
    }

    public static TextureRegion[][] getMaleParts() {
        return maleParts;
    }

    public static TextureRegion[][] getFemaleParts() {
        return femaleParts;
    }

    public static TextureRegion getPart(boolean male, int part, int index) {
        if (male) {
            return maleParts[part][index];
        } else {
            return femaleParts[part][index];
        }
    }

   public static void playSound(String sound) {
       sounds.get(sound).play(1, 1, 0);
   }

    public static Animation getAnimation(String name) {
        return animations.get(name);
    }
}
