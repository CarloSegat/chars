package com.mygdx.services;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.animation.FireBall;
import com.mygdx.core.Config;
import com.mygdx.letters.LetterBase;

import java.util.HashMap;
import java.util.Map;

public class GameServices {

    private PaperBackgroundModel paperBGModel;
    private MyStage stage;
    private TextureMap textureMap;
    private LetterFactory letterFactory;
    private AnimationPropsFactory animationPropsFactory;

    public AnimationPropsFactory getAnimationPropsFactory() {
        return animationPropsFactory;
    }



    public GameServices() {
        initialiseStage();
        this.textureMap = new TextureMap();
        this.paperBGModel = new PaperBackgroundModel(com.mygdx.core.Config.virtualDevice[0],
                Config.virtualDevice[1]);
        this.letterFactory = new LetterFactory(this);
        this.animationPropsFactory = new AnimationPropsFactory(this);
    }

    private void initialiseStage() {
        OrthographicCamera camera = new OrthographicCamera(Config.virtualDevice[0], Config.virtualDevice[1]);
        camera.setToOrtho(false, Config.virtualDevice[0], Config.virtualDevice[1]);
        this.stage = new MyStage(new StretchViewport(Config.virtualDevice[0],
                Config.virtualDevice[1], camera));
    }

    public PaperBackgroundModel getPaperBGModel() {
        return paperBGModel;
    }

    public MyStage getStage() {
        return stage;
    }

    public TextureMap getTextureMap() {
        return textureMap;
    }

    public void removeLetter(LetterBase letter){
        letter.remove();
        stage.getLetters().remove(letter.getId());
    }

    public LetterFactory getLetterFactory() {
        return letterFactory;
    }
}
