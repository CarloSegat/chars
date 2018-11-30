package com.mygdx.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.core.ActorWithPathAndID;

public class StaticActor extends ActorWithPathAndID {

    public StaticActor(Texture texture, String path, String id) {
        super(id, texture);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
