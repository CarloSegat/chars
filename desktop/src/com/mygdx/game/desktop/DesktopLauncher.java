package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.core.Config;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		applyMyConfig(config);
		new LwjglApplication(new MyGdxGame(), config);
	}

	public static void applyMyConfig(LwjglApplicationConfiguration config) {
		config.title = "CharsWars";
		config.width = Config.virtualDevice[0];
		config.height = Config.virtualDevice[1];
		config.resizable = false;
	}
}
