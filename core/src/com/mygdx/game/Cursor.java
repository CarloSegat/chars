package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.core.StaticActor;
import com.mygdx.core.Config;

public class Cursor extends StaticActor {
    public static String defaultPath = "cursor.png";
    private boolean isOnRightSide;
    private boolean isOnLeftSide;

    public void setVisibilityCounter(int visibilityCounter) {
        this.visibilityCounter = visibilityCounter;
    }

    private int visibilityCounter = 0;

    public Cursor(Texture texture, String id) {
        super(texture, id, Cursor.defaultPath);
    }

    public Cursor(Texture texture, String id, String path) {
        super(texture, id, path);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        blink();
        setIsLeftOrRight();
    }

    private void setIsLeftOrRight() {
        if(getX() > Config.virtualDevice[0] / 2) {
            isOnRightSide = true;
            isOnLeftSide = false;
        } else {
            isOnRightSide = false;
            isOnLeftSide = true;
        }
    }

    private void blink() {
        visibilityCounter++;
        if(visibilityCounter >= 20 && visibilityCounter < 30){
            this.setVisible(false);
        }
        if(visibilityCounter > 0 && visibilityCounter < 20){
            this.setVisible(true);
        }
        if(visibilityCounter == 30){
            visibilityCounter = 0;
        }
    }

    public boolean isOnRightSide() {
        return isOnRightSide;
    }

    public boolean isOnLeftSide() {
        return isOnLeftSide;
    }
}
