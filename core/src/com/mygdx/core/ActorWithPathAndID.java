package com.mygdx.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class ActorWithPathAndID extends Image {

    protected String id;

    public ActorWithPathAndID(String id) {
        super();
        this.id = id;
    }

    public ActorWithPathAndID(String id, Texture texture) {
        super(texture);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void setId(String id) {
        this.id = id;
    }
}
