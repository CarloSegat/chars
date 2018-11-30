package com.mygdx.letters;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.services.GameServices;

public class A extends LetterBase {

    public static String defaultAnimationPath = "aShieldCycle.png";
    private int letterSpeed = 150;
    private float attackRate = 2f;
    private float attackCounter = 0f;


    public A(String id, GameServices gs) {
        super(id, gs);
        this.lifePoints = 100;
        this.attackPoints = 15;
        this.currentLife = lifePoints;
        this.gs = gs;
        this.animation = gs.getTextureMap().getAnimation("a");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isMoving()){
            setX(getX() + this.letterSpeed * delta * getMovementDirection());
            bounds.setX(getX());
            bounds.setY(getY());

            if(getMovementDirection() == 1){
                wrapDownLeftToRight();
            }
            if(getMovementDirection() == -1){
                wrapDownRightToLeft();
            }
        }
        // TODO timing issue, need to be done even if not moving
        hasHitLetter();
        if(! isMoving() && isAnimationFinished()){
            attackCounter += delta;
            if(attackCounter >= attackRate){
                doAttackAnimation();
                attackCounter = 0;
                int iterationCounter = gs.getStage().getLetters().values().size();
                for (LetterBase letter : gs.getStage().getLetters().values()) {
                    iterationCounter--;
                    if (this.getBounds().overlaps(letter.getBounds()) && this != letter) {
                            letter.setCurrentLife(letter.getCurrentLife() - this.getAttackPoints());
                    } else {
                        if(iterationCounter == 0){
                            setMoving(true);
                        }
                    }
                }
            }
        }
    }

    private void doAttackAnimation() {
        MoveByAction forth = new MoveByAction();
        MoveByAction back = new MoveByAction();
        forth.setAmount(25f * getMovementDirection(), 5f);
        forth.setDuration(0.2f);
        back.setAmount(-25f * getMovementDirection(), -5f);
        back.setDuration(0.2f);
        SequenceAction attack = new SequenceAction(forth, back);
        this.addAction(attack);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha * lifeTransparency);
    }


    public void hasHitLetter() {
        for (LetterBase letter : gs.getStage().getLetters().values()) {
            if (this.getBounds().overlaps(letter.getBounds()) && this != letter) {
                this.setMoving(false);
                letter.setMoving(false);
                setAnimationRunning(true);
            }
        }
    }


}
