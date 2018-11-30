package com.mygdx.services;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.background.PaperBackgroundUI;
import com.mygdx.background.PaperLine;

import com.mygdx.game.Cursor;
import com.mygdx.letters.A;
import com.mygdx.letters.F;

public class TextureMap {

    private Map<String, Texture> stringToTexture = new HashMap();
    private Map<String, Animation> stringToAnimation = new HashMap();

    public TextureMap() {
        setUpTextures();
        setUpLetterAAnimation();
        setUpFireMovingAnimation();
        setUpFireExplodingAnimation();
        setUpLetterFAnimation();
    }

    private void setUpLetterAAnimation() {
        TextureRegion[][] tmpFrames = TextureRegion.split(getTexture("aShieldCycle"),
                64,64);
        TextureRegion[] animationFrames = new TextureRegion[16];
        int index = 0;
        for (int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }
        Animation animation = new Animation<TextureRegion>(1f/16f,animationFrames);
        stringToAnimation.put("a", animation);
    }

    // TODO one frame animation bad solution
    private void setUpLetterFAnimation() {
        TextureRegion[][] tmpFrames = TextureRegion.split(getTexture("f"),
                64,64);
        TextureRegion[] animationFrames = new TextureRegion[1];
        animationFrames[0] = tmpFrames[0][0];
        Animation animation = new Animation<TextureRegion>(0,animationFrames);
        stringToAnimation.put("f", animation);
    }

    private void setUpFireMovingAnimation() {
        TextureRegion[][] tmpFrames = TextureRegion.split(getTexture("fireBall"),
                64,64);
        TextureRegion[] animationFrames = new TextureRegion[2];
        animationFrames[0] = tmpFrames[0][0];
        animationFrames[1] = tmpFrames[1][0];

        Animation animation = new Animation<TextureRegion>(1f/20f,animationFrames);
        stringToAnimation.put("fireBall", animation);
    }

    private void setUpFireExplodingAnimation() {
        TextureRegion[][] tmpFrames = TextureRegion.split(getTexture("fireBallExplosion"),
                64,64);
        TextureRegion[] animationFrames = new TextureRegion[15];
        for(int i = 0; i != 15; i++){
            animationFrames[i] = tmpFrames[0][i];
        }
        Animation animation = new Animation<TextureRegion>(1f/20f,animationFrames);
        stringToAnimation.put("fireBallExplosion", animation);
    }

    private void setUpTextures() {
        stringToTexture.put("line", new Texture(Gdx.files.internal(PaperLine.defaultPath)));
        stringToTexture.put("a", new Texture(Gdx.files.internal(A.defaultAnimationPath)));
        stringToTexture.put("f", new Texture(Gdx.files.internal(F.defaultSprite)));
        stringToTexture.put("cursor", new Texture(Gdx.files.internal(Cursor.defaultPath)));
        stringToTexture.put("paper", new Texture(Gdx.files.internal(PaperBackgroundUI.defaultPath)));
        stringToTexture.put("aShieldCycle", new Texture(Gdx.files.internal("aShieldCycle.png")));
        stringToTexture.put("fireBall", new Texture(Gdx.files.internal("fire_ball.png")));
        stringToTexture.put("fireBallExplosion", new Texture(Gdx.files.internal("fire_ball_explosion.png")));
    }

    public Texture getTexture(String name){
        return this.stringToTexture.get(name);
    }

    public Animation getAnimation(String name){
        return this.stringToAnimation.get(name);
    }
}

