package com.mygdx.services;

import com.mygdx.animation.FireBall;
import com.mygdx.letters.LetterBase;

import java.util.HashMap;
import java.util.Map;

public class AnimationPropsFactory {

    private GameServices gs;
    private Map<String, String> actorsTypeCounters;

    public AnimationPropsFactory(GameServices gs) {
        this.gs = gs;
        actorsTypeCounters = new HashMap<String, String>();
    }

    public String createFireBall(LetterBase letter){
        if(actorsTypeCounters.get("fireBall") == null){
            actorsTypeCounters.put("fireBall", "0");
        }
        String currentCount = actorsTypeCounters.get("fireBall");
        FireBall fb = new FireBall(currentCount, letter, gs);
        actorsTypeCounters.put("fireBall", String.valueOf(Integer.valueOf(currentCount) + 1));
        gs.getStage().addActor(fb);
        return fb.getId();
    }
}
