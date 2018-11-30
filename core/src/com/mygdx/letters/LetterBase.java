package com.mygdx.letters;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.mygdx.services.GameServices;
import com.mygdx.core.WarpingActor;


public abstract class LetterBase extends WarpingActor {

    protected GameServices gs;
    private int movementDirection;
    private boolean isMoving = true;
    protected Rectangle bounds;
    protected float lifePoints;
    protected float attackPoints;
    protected float currentLife;
    protected float lifeTransparency = 1f;

    LetterBase(String id, GameServices gs) {
        super(id, gs);
        bounds = new Rectangle((int)getX(), (int)getY(), 64, 64);
        this.gs = gs;
    }

    public void resize(float percentage){
        ScaleByAction sba = new ScaleByAction();
        sba.setAmount(percentage);
        sba.setDuration(0f);
        this.addAction(sba);
    }



    public int getMovementDirection() {
        if (movementDirection != 0 && movementDirection != 1 && movementDirection != -1){
            throw new RuntimeException("movement direction is wrong");
        }
        return movementDirection;
    }

    public void setMovementDirection(int movementDirection) {
        this.movementDirection = movementDirection;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(currentLife <= 0){
            gs.removeLetter(this);
        }
        // letter fades as it loses life points
        lifeTransparency = currentLife / lifePoints;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setLifePoints(float lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setAttackPoints(float attackPoints) {
        this.attackPoints = attackPoints;
    }

    public float getLifePoints() {
        return lifePoints;
    }

    public float getAttackPoints() {
        return attackPoints;
    }

    public void setCurrentLife(float currentLife) {
        if(!(currentLife < 0)){
            this.currentLife = currentLife;
        } else {
            this.currentLife = 0;
        }
    }

    public float getCurrentLife() {
        return currentLife;
    }

}
