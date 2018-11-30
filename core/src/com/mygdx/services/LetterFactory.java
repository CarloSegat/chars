package com.mygdx.services;

import com.mygdx.game.Cursor;
import com.mygdx.letters.A;
import com.mygdx.letters.F;
import com.mygdx.letters.LetterBase;

import java.util.HashMap;
import java.util.Map;

public class LetterFactory {

    private GameServices gs;
    private Map<String, String> lettersIdsCounter;

    public LetterFactory(GameServices gs) {
        this.gs = gs;
        lettersIdsCounter = new HashMap<String, String>();
        lettersIdsCounter.put("a", "0");
        lettersIdsCounter.put("f", "0");
    }

    public LetterBase generateLetter(char letter, Cursor cursor){
        LetterBase letterToGenerate = null;
        switch (letter){
            case 'a':
                letterToGenerate = new A(getIdForLetter('a'), gs);
                break;
            case 'f':
                letterToGenerate = new F(getIdForLetter('f'), gs);
                break;
            default:
                break;

        }
        setLetterPosition(cursor, letterToGenerate);
        setLetterDirection(cursor, letterToGenerate);
        gs.getStage().addLetter(letterToGenerate);
        return letterToGenerate;
    }

    private void setLetterPosition(Cursor cursor, LetterBase letterToGenerate) {
        letterToGenerate.setX(cursor.getX());
        letterToGenerate.setY(cursor.getY());
    }

    private void setLetterDirection(Cursor cursor, LetterBase letterToGenerate) {
        if(cursor.isOnLeftSide()){
            letterToGenerate.setMovementDirection(1);
        } else {
            letterToGenerate.setMovementDirection(-1);
        }
    }

    private String getIdForLetter(char letter){
        String currentLetterId = this.lettersIdsCounter.get(Character.toString(letter));
        String incrementedId = String.valueOf(Integer.valueOf(currentLetterId) + 1);
        this.lettersIdsCounter.put(Character.toString(letter), incrementedId);
        return currentLetterId;
    }
}
