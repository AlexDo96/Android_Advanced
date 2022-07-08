package com.chao.gameworld;

import com.badlogic.gdx.math.Rectangle;
import com.chao.GameObject.Bird;
import com.chao.GameObject.ScrollHandler;

public class GameWorld {
	private Bird bird;
	private ScrollHandler scroller;

	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
		// Conen bat dau 66 pixels duoi midPointY
		scroller = new ScrollHandler(midPointY + 66);

	}

	public void update(float delta) {
		System.out.println("GameWorld - update");
		bird.update(delta);
		scroller.update(delta);

	}

	public Bird getBird() {
		return bird;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

}
