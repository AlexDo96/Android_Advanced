package com.chao.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.chao.conchimdienHelper.InputHandler;
import com.chao.gameworld.GameRenderer;
import com.chao.gameworld.GameWorld;

public class GameScreen implements Screen{


	private GameWorld world;
	private GameRenderer renderer;
	private float runTime; //*
	public GameScreen()
	{
		System.out.println("GameScreen - ham tao");
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);

		int midPointY = (int) (gameHeight / 2);

		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world,(int) gameHeight, midPointY);
		Gdx.input.setInputProcessor(new InputHandler(world));

	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		runTime += delta;  
		world.update(delta);
		renderer.render(runTime);
		//red:10, green:15,blue:230,100% opacity
/*		Gdx.gl.glClearColor(10/255.0f,15/255.0f, 230/255.0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println(1/delta);*/		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		System.out.println("man hinh chao - resize");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.println("man hinh chao - show");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		System.out.println("man hinh chao - hide");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		System.out.println("man hinh chao - pause");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		System.out.println("man hinh chao - resume");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("man hinh chao - dispose");
	}




}
