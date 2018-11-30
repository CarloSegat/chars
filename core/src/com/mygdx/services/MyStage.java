package com.mygdx.services;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mygdx.letters.LetterBase;

public class MyStage extends Stage {

    private int counter = 0;
    Map<String, LetterBase> letters;
    List<Actor> allActors;


    public MyStage(Viewport viewport) {
        super(viewport);
        letters = new HashMap<String, LetterBase>();
    }

    public void addLetter(LetterBase letter){
        //TODO check ID if already present
        letter.setId(letter.getId() + counter++);
        this.letters.put(letter.getId(), letter);
        addActor(letter);
    }

    public Map<String, LetterBase> getLetters() {
        return letters;
    }

    public List<Actor> getAllActors() {
        return allActors;
    }

}
