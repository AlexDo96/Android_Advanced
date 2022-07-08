package com.chao.conchimdienHelper;

import com.badlogic.gdx.InputProcessor;
import com.chao.GameObject.Bird;
import com.chao.gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	private Bird myBird;
	GameWorld myWorld;

	public InputHandler(GameWorld myWorld) {
		this.myWorld = myWorld;
		this.myBird = myWorld.getBird();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if (myWorld.isReady()) {
			myWorld.start();
		}

		myBird.onClick();

		if (myWorld.isGameOver()) {
			// Reset all variables, go to GameState.READ
			myWorld.restart();
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
