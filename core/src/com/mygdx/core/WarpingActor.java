package com.mygdx.core;

import com.mygdx.services.GameServices;

public class WarpingActor extends AnimatedActor {

    private GameServices gs;

    public WarpingActor(String id, GameServices gs) {
        super(id);
        this.gs = gs;
    }

    protected void wrapDownLeftToRight() {
        if(getX() > Config.virtualDevice[0]){
            moveDownOnTheLeft();
            wrapAtTheTopLeftToRight();
        }
    }

    protected void wrapDownRightToLeft() {
        if(getX() < 0){
            moveDownOnTheRight();
            wrapAtTheTopRightToLeft();
        }
    }

    private void wrapAtTheTopRightToLeft() {
        int lastRowIndex = gs.getPaperBGModel().getSpawnCoordinatesLeft().length - 1;
        boolean letterIsBelowBottomYCoordinate = getY() <= gs.getPaperBGModel().getSpawnCoordinatesLeft()[lastRowIndex];
        if(letterIsBelowBottomYCoordinate){
            // TODO reset X better
            setX(Config.virtualDevice[0] - 64);
            setY(gs.getPaperBGModel().getSpawnCoordinatesLeft()[0]);
        }
    }

    private void moveDownOnTheRight() {
        // TODO reset X better
        setX(Config.virtualDevice[0] - 64);
        setY(getY() - gs.getPaperBGModel().getSpaceBetweenLines());
    }

    private void positionLetterAtTopRight() {
    }


    private void wrapAtTheTopLeftToRight() {
        int lastRowIndex = gs.getPaperBGModel().getSpawnCoordinatesLeft().length - 1;
        boolean letterIsBelowBottomYCoordinate = getY() <= gs.getPaperBGModel().getSpawnCoordinatesLeft()[lastRowIndex];
        if(letterIsBelowBottomYCoordinate){
            setX(0);
            setY(gs.getPaperBGModel().getSpawnCoordinatesLeft()[0]);
        }
    }

    private void moveDownOnTheLeft() {
        setX(0);
        setY(getY() - gs.getPaperBGModel().getSpaceBetweenLines());
    }
}
