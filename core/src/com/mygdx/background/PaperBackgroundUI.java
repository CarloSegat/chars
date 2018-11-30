package com.mygdx.background;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.core.StaticActor;

public class PaperBackgroundUI extends StaticActor implements InputProcessor {
    public static String defaultPath = "paperBig.png";

    public PaperBackgroundUI(Texture texture, String id) {
        super(texture, PaperBackgroundUI.defaultPath, id);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public PaperBackgroundUI(Texture texture, String id, String path) {
        super(texture, path, id);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Vector2 coord = stage.screenToStageCoordinates(new Vector2(
        // (float)screenX, (float)screenY));
        System.out.println(screenX);
        System.out.println(screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
