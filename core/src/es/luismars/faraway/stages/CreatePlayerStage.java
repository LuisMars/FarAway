package es.luismars.faraway.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import es.luismars.faraway.Constants;
import es.luismars.faraway.Loader;
import es.luismars.faraway.actors.Character;
import es.luismars.faraway.actors.widgets.CustomTextButton;
import es.luismars.faraway.actors.widgets.PartSelector;
import es.luismars.faraway.screens.ShipScreen;

import java.util.Map;

import static es.luismars.faraway.CharacterConstants.*;

/**
 * Created by dalek on 24/12/2015.
 */
public class CreatePlayerStage extends CustomStage {

    private Table table;

    private int body, legs, boots, skinImg, hair;
    private boolean male;
    private int bodyLength, legsLength, bootsLength, skinImgLength, hairLength;

    private TextureRegion[][] parts;
    private Image[] partsArray;
    private Cell<Stack> playerCell;
    private PartSelector genderSelector;


    public CreatePlayerStage() {
        super();
        Skin skin = Loader.getSkin();

        table = new Table(skin);
        table.setFillParent(true);
        addActor(table);

        table.add("(New Player)", "title").colspan(2).row();

        parts = Loader.getMaleParts();

        partsArray = new Image[5];

        resetParts(true);

        playerCell = table.stack(partsArray).size(Constants.TILE_SIZE * 4, Constants.TILE_SIZE * 8).pad(8);

        //table.row();
        Table selectors = new Table();

        genderSelector = new PartSelector("male", skin);
        male = true;

        PartSelector skinSelector = new PartSelector("skin", skin);
        PartSelector hairSelector = new PartSelector("hair", skin);
        PartSelector bodySelector = new PartSelector("body", skin);
        PartSelector legsSelector = new PartSelector("legs", skin);
        PartSelector bootsSelector = new PartSelector("boots", skin);

        genderSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchGender();
            }
        });

        addSelectorsListeners(skinSelector, hairSelector, bodySelector, legsSelector, bootsSelector);

        selectors.add(genderSelector).expandY().pad(2).row();
        selectors.add(skinSelector).expandY().pad(2).row();
        selectors.add(hairSelector).expandY().pad(2).row();
        selectors.add(bodySelector).expandY().pad(2).row();
        selectors.add(legsSelector).expandY().pad(2).row();
        selectors.add(bootsSelector).expandY().pad(2).row();

        table.add(selectors).fillY();
        table.row().height(20).padTop(4);

        CustomTextButton back, create;

        back = new CustomTextButton("Back", skin);
        create = new CustomTextButton("Create", skin);

        table.add(back).prefSize(64, 20);
        table.add(create).prefSize(64, 20);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen("mainmenu");
            }
        });

        create.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Character player = new Character(male, body, legs, boots, skinImg, hair);
                changeScreen(new ShipScreen(player));
            }
        });

        table.center();

        //setDebugAll(true);
    }

    private void switchGender() {
        if (male) {
            male = false;
            parts = Loader.getFemaleParts();
            resetParts(false);
            reloadPlayer();
            genderSelector.setText("female");
        } else {
            male = true;
            parts = Loader.getMaleParts();
            resetParts(true);
            reloadPlayer();
            genderSelector.setText("male");
        }
    }

    private void addSelectorsListeners(PartSelector skinSelector, PartSelector hairSelector, PartSelector bodySelector, PartSelector legsSelector, PartSelector bootsSelector) {
        skinSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor.getName().equals("left")) {
                    skinImg = (skinImg + skinImgLength - 1) % skinImgLength;

                } else {
                    skinImg = (skinImg + 1) % skinImgLength;
                }
                partsArray[SKIN] = new Image(parts[SKIN][skinImg]);
                reloadPlayer();
            }
        });

        hairSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor.getName().equals("left")) {
                    hair = (hair + hairLength - 1) % hairLength;

                } else {
                    hair = (hair + 1) % hairLength;
                }
                partsArray[HAIR] = new Image(parts[HAIR][hair]);
                reloadPlayer();
            }
        });

        bodySelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor.getName().equals("left")) {
                    body = (body + bodyLength - 1) % bodyLength;

                } else {
                    body = (body + 1) % bodyLength;
                }
                partsArray[BODY] = new Image(parts[BODY][body]);
                reloadPlayer();
            }
        });

        legsSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor.getName().equals("left")) {
                    legs = (legs + legsLength - 1) % legsLength;

                } else {
                    legs = (legs + 1) % legsLength;
                }
                partsArray[LEGS] = new Image(parts[LEGS][legs]);
                reloadPlayer();
            }
        });

        bootsSelector.addListener(new ChangeListener() {
            @Override
             public void changed(ChangeEvent event, Actor actor) {
                if (actor.getName().equals("left")) {
                    boots = (boots + bootsLength - 1) % bootsLength;

                } else {
                    boots = (boots + 1) % bootsLength;
                }
                partsArray[BOOTS] = new Image(parts[BOOTS][boots]);
                reloadPlayer();
            }
        });
    }

    public void resetParts(boolean male) {
        body = legs = boots = skinImg = hair = 0;
        if (male) {
            bodyLength = MALE_BODY_LENGTH;
            legsLength = MALE_LEGS_LENGTH;
            bootsLength = MALE_BOOTS_LENGTH;
            skinImgLength = MALE_SKIN_LENGTH;
            hairLength = MALE_HAIR_LENGTH;
        } else {
            bodyLength = FEMALE_BODY_LENGTH;
            legsLength = FEMALE_LEGS_LENGTH;
            bootsLength = FEMALE_BOOTS_LENGTH;
            skinImgLength = FEMALE_SKIN_LENGTH;
            hairLength = FEMALE_HAIR_LENGTH;
        }

        partsArray[BODY] = new Image(parts[BODY][body]);
        partsArray[LEGS] = new Image(parts[LEGS][legs]);
        partsArray[BOOTS] = new Image(parts[BOOTS][boots]);
        partsArray[SKIN] = new Image(parts[SKIN][skinImg]);
        partsArray[HAIR] = new Image(parts[HAIR][hair]);

    }

    public void reloadPlayer() {
        playerCell.getActor().clear();
        for (Image image : partsArray) {
            playerCell.getActor().add(image);
        }
    }
}
