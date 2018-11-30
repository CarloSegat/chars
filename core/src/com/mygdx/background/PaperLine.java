package com.mygdx.background;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.core.StaticActor;

public class PaperLine extends StaticActor {
    public static String defaultPath = "line.png";

    public PaperLine(Texture texture, String id, String path) {
        super(texture, path, id);
    }


    public PaperLine(Texture texture, String id) {
        super(texture, PaperLine.defaultPath, id);
    }
}
