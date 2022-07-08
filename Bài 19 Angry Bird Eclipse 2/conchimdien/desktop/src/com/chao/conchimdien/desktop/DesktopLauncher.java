package com.chao.conchimdien.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.chao.conchimdien.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="con chim dien";
		config.useGL30=false;
		config.width=272;
		config.height=408;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
