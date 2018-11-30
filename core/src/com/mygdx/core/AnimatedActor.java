/*******************************************************
 * Copyright (C) 2015 Mirco Timmermann - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Mirco Timmermann <mtimmermann@gmx.de>, December 2015
 * 
 *******************************************************/
package com.mygdx.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.services.GameServices;


/*
 * Actor for sprite sheets.
 */
public class AnimatedActor extends ActorWithPathAndID {
	protected Animation<TextureRegion> animation;

    public boolean isAnimationRunning() {
        return isAnimationRunning;
    }

    protected boolean isAnimationRunning = false;
	protected float stateTime = 0;

	public boolean flipX = false;
	public boolean flipY = false;
	
	public boolean autoSizeAdjust = true;
	public boolean autoSizeAdjustFrame = false;
	public boolean autoUpdateParentSize = false;
	
	
	public AnimatedActor(String id) {
		super(id);
	}

	public void setAnimation(Animation<TextureRegion> animation) {
		this.animation = animation;
		
		if(this.animation != null) {
			if(autoSizeAdjust) {
				this.setWidth(animation.getKeyFrame(0).getRegionWidth()); 
				this.setHeight(animation.getKeyFrame(0).getRegionHeight());
				
				if(autoUpdateParentSize) {
					Actor parent = this.getParent();
					
					if(parent != null) {
						parent.setWidth(this.getWidth());
						parent.setHeight(this.getHeight());
					}
				}
			}
		}
	}

    public void setAnimationRunning(boolean animationRunning) {
        isAnimationRunning = animationRunning;
    }

	@Override
	public void act(float delta) {
	    if(isAnimationRunning){
            stateTime += delta;
        } else {
	        stateTime = 0;
        }


		
		if(autoSizeAdjustFrame) {
			TextureRegion textureRegion = animation.getKeyFrame(stateTime);
			
			this.setWidth(textureRegion.getRegionWidth()); 
			this.setHeight(textureRegion.getRegionHeight());
			
			if(autoUpdateParentSize) {
				this.getParent().setWidth(this.getWidth());
				this.getParent().setHeight(this.getHeight());
			}
		}
		
		super.act(delta);
	}
	
	public void setFrameDuration(float frameDuration) {
		if(animation != null) {
			animation.setFrameDuration(frameDuration);
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(animation != null) {
			float x = getX();
			if(flipX) x += getWidth();
			
			float y = getY();
			if(flipY) y += getHeight();
			
			Color color = batch.getColor();
			batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b, parentAlpha);
			
			batch.draw(animation.getKeyFrame(stateTime),
					x, y,
					getOriginX(), getOriginY(),
					getWidth(), getHeight(),
					(flipX ? -this.getScaleX() : this.getScaleY()),
					(flipY ? -this.getScaleY() : this.getScaleY()),
					getRotation());
		
			batch.setColor(color);
		}
	}
	
	public void resetStateTime() {
		stateTime = 0;
	}
	
	public void setStateTime(float time) {
		stateTime = time;
	}
	
	public float getStateTime() {
		return stateTime;
	}
	
	public boolean isAnimationFinished() {
		return animation.isAnimationFinished(stateTime);
	}
	
	public int GetKeyFrameIndex() {
		return animation.getKeyFrameIndex(stateTime);
	}
	
	public TextureRegion GetKeyFrame() {
		return animation.getKeyFrame(stateTime);
	}
	
	public float GetFrameDuration() {
		if(animation != null) {
			return animation.getFrameDuration();
		}
		return 0;
	}
	
	public int GetFrameLength() {
		if(animation != null) {
			return animation.getKeyFrames().length;
		}
		return 0;
	}
	
	public Animation<TextureRegion> getAnimation() {
		return animation;
	}


	@Override
	public String getId() {
		return id;
	}

	public void setAnimationMode(Animation.PlayMode mode){
		this.stateTime = 0;
		animation.setPlayMode(mode);
	}
}
