package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.animation.FireBall;
import com.mygdx.background.PaperBackgroundUI;
import com.mygdx.background.PaperLine;

import com.mygdx.services.GameServices;
public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

	PaperBackgroundUI bgUI;
	PaperLine[] paperLines;
	PaperLine pl;
	Cursor cursor;
	float elapsedTime;
	FireBall fb;
	Stage stage;
	Group group;
	Image background;
	Image bucket;

	GameServices gs;

	@Override
	public void create () {
		gs = new GameServices();
		bgUI = new PaperBackgroundUI(gs.getTextureMap().getTexture("paper"), "bl");
		paperLines = new PaperLine[gs.getPaperBGModel().getNumberOfLines()];
		instanciateLines(gs.getTextureMap().getTexture("line"));
		cursor = new Cursor(gs.getTextureMap().getTexture("cursor"), "leftCursor");
		ScreenViewport viewport = new ScreenViewport();
		//stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		group =  new Group();
		background = new Image(new Texture(Gdx.files.internal("paperBig.png")));
		bucket = new Image(new Texture(Gdx.files.internal("f.png")));
		bucket.setName("bucket");


		// order determines Z-index
		group.addActor(background);
		group.addActor(bucket);
		bucket.setPosition(200f, 250f);
		//stage.addActor(group);
		gs.getStage().addActor(bgUI);


		Gdx.input.setInputProcessor(this);
	}

	private void instanciateLines(Texture paperTexture) {
		for(int i = 0; i < gs.getPaperBGModel().getNumberOfLines(); i++){
			paperLines[i] = new PaperLine(paperTexture, Integer.toString(i));
			paperLines[i].setName(Integer.toString(i));
			paperLines[i].setY(gs.getPaperBGModel().getLinesIndices()[i]);
		}
	}

	private void addLinesToStage(){
		for(int i = 0; i < gs.getPaperBGModel().getNumberOfLines(); i++) {
			gs.getStage().addActor(paperLines[i]);
		}
	}

	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gs.getStage().act(Gdx.graphics.getDeltaTime()); // actions updated here
        gs.getStage().draw();
	}

	@Override
	public void dispose () {
		gs.getStage().dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.A){
			gs.getLetterFactory().generateLetter('a', cursor);
		}
		if(keycode == Input.Keys.F){
			gs.getLetterFactory().generateLetter('f', cursor);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 coordsForImageBackgound = gs.getStage().screenToStageCoordinates(new Vector2(
				(float)screenX, (float)screenY));
		int rowIndex = gs.getPaperBGModel().calculateRowFromPoint(coordsForImageBackgound);
		int leftOrRight = gs.getPaperBGModel().calculateLeftOrRightFromPoint(coordsForImageBackgound);
		if(rowIndex >= 0 && rowIndex < gs.getPaperBGModel().getNumberOfLines())
			cursor.setY(gs.getPaperBGModel().getSpawnCoordinatesLeft()[rowIndex]);
			cursor.setX(leftOrRight);
			cursor.setVisibilityCounter(0);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
