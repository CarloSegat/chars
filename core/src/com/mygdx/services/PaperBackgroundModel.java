package com.mygdx.services;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.core.Config;

import java.security.InvalidParameterException;

import static com.badlogic.gdx.math.MathUtils.floor;

public class PaperBackgroundModel {

    private int numberOfLines = 11;
    private int spaceBetweenLines = Config.virtualDevice[1]/ (numberOfLines + 6);
    private int lineWidth = 1;
    private int offsetTop = 50;

    private float[] linesIndices = new float[numberOfLines];
    private float[] spawnCoordinatesLeft = new float[numberOfLines];
    private float[] spawnCoordinatesRight = new float[numberOfLines];

    public PaperBackgroundModel(int width, int height) {
        initializeLines(width, height);
        initializeSpawnCoordinatesLeft(width, height);
        initializeSpawnCoordinatesRight(width, height);
    }

    public int calculateRowFromPoint(Vector2 vector2){
        float y = vector2.y;
        if(y < 0 || y > Config.virtualDevice[1]){
            throw new InvalidParameterException("Invalid argument");
        }
        int i = Config.virtualDevice[1] - (int) y - offsetTop;
        if(i < 0){
            return 0;
        }
        int i1 = floor(i / spaceBetweenLines);
        return i1;
    }

    private void initializeLines(int width, int height) {
        for(int i = 0; i < numberOfLines; i++)
        {
            // TODO shoudln't need to take away the 2nd spaceeBetweenLine
            linesIndices[i] = height - (i * spaceBetweenLines) - spaceBetweenLines - offsetTop;
        }
    }

    private void initializeSpawnCoordinatesLeft(int width, int height) {
        for(int i = 0; i < numberOfLines; i++)
        {
            spawnCoordinatesLeft[i] = linesIndices[i] + lineWidth;
        }
    }

    private void initializeSpawnCoordinatesRight(int width, int height) {
        for(int i = 0; i < numberOfLines; i++)
        {
            spawnCoordinatesRight[i] = spawnCoordinatesLeft[i] +
                    Config.virtualDevice[0] - Config.letterSpriteStandardWidth;
        }
    }

    public float[] getLinesIndices() {
        return linesIndices;
    }

    public float[] getSpawnCoordinatesLeft() {
        return spawnCoordinatesLeft;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public int getSpaceBetweenLines() {
        return spaceBetweenLines;
    }


    public int calculateLeftOrRightFromPoint(Vector2 coordsForImageBackgound) {
        if(coordsForImageBackgound.x > Config.virtualDevice[0] / 2){
            return Config.virtualDevice[0] - Config.cursorSpriteStandardWidth;
        }else{
            return 0;
        }
    }
}
