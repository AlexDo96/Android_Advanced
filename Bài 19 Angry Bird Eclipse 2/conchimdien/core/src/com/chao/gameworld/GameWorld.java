package com.chao.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.chao.GameObject.Bird;
import com.chao.GameObject.ScrollHandler;
import com.chao.conchimdienHelper.AssetLoader;

public class GameWorld {
	private Bird bird;
	private ScrollHandler scroller;

	private Rectangle ground;
	private int score = 0;

	public int midPointY;
	private GameState currentState;

	public enum GameState {

		READY, RUNNING, GAMEOVER

	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void restart() {
		currentState = GameState.READY;
		score = 0;
		bird.onRestart(midPointY - 5);
		scroller.onRestart();
		currentState = GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
		// Conen bat dau 66 pixels duoi midPointY
		scroller = new ScrollHandler(this, midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 136, 11);
		currentState = GameState.READY;
		this.midPointY = midPointY;

	}

	public void update(float delta) {

		switch (currentState) {
		case READY:
			updateReady(delta);
			break;

		case RUNNING:
		default:
			updateRunning(delta);
			break;
		}

	}

	private void updateReady(float delta) {
		// chuacogihet
	}

	public void updateRunning(float delta) {

		if (delta > .15f) {
			delta = .15f;
		}

		System.out.println("GameWorld - update");
		bird.update(delta);
		scroller.update(delta);
		if (scroller.collides(bird) && bird.isAlive()) {
			// lendia, da game over
			scroller.stop();
			AssetLoader.dead.play();
			bird.die();

		}

		if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
			scroller.stop();
			bird.die();
			bird.decelerate();
			currentState = GameState.GAMEOVER;
		}

	}

	public Bird getBird() {
		return bird;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

}
