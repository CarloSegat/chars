package com.mygdx.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.core.AnimatedActor;
import com.mygdx.core.WarpingActor;
import com.mygdx.services.GameServices;
import com.mygdx.letters.LetterBase;

public class FireBall extends WarpingActor {

    private GameServices gs;
    private Animation<TextureRegion> ballMoving;
    private Animation<TextureRegion> ballExploding;
    private Rectangle bounds = new Rectangle((int)getX(), (int)getY(), 64, 32);
    private int speed = 300;
    private int movementDirection;
    private boolean isExploding = false;
    private float damage = 10f;

    private LetterBase owner;

    public FireBall(String id, LetterBase owner, GameServices gs) {
        super(id, gs);
        setBounds(getX(), getY(), 64, 64);
        this.gs = gs;
        this.owner = owner;
        setUpAnimations(gs);
        setZIndex(99999999);
        setMovementDirection(owner.getMovementDirection());
        // TODO improve ball spawn
        setX(owner.getX() + 40);
        setY(owner.getY());
    }

    private void setUpAnimations(GameServices gs) {
        ballMoving = gs.getTextureMap().getAnimation("fireBall");
        ballExploding = gs.getTextureMap().getAnimation("fireBallExplosion");
        setAnimationToMoving();
        setAnimationMode(Animation.PlayMode.LOOP);
        setAnimationRunning(true);
        setMovementDirection(1);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() + this.speed * delta * getMovementDirection());
        bounds.setX(getX());
        bounds.setY(getY());
        if(this.animation == ballMoving){
            hasHitLetter();
        }
        if(this.animation == ballExploding && this.animation.isAnimationFinished(stateTime)){
            this.remove();
        }
    }

    public void setAnimationToMoving(){
        animation = ballMoving;
    }

    public void setAnimationExploding(){
        animation = ballExploding;
        stateTime = 0;
    }

    public int getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(int movementDirection) {
        this.movementDirection = movementDirection;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void hasHitLetter() {
        for (LetterBase letter : gs.getStage().getLetters().values()) {
            if (this.getBounds().overlaps(letter.getBounds()) && letter != owner) {
                this.setAnimationExploding();
                letter.setCurrentLife(letter.getCurrentLife() - damage);
            }
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }


}
