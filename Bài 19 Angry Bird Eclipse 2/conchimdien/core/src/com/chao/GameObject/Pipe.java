package com.chao.GameObject;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {

	private Random r;
	private Rectangle skullUp, skullDown, barUp, barDown;
	private boolean isScored = false;

	public static final int VERTICAL_GAP = 45;
	public static final int SKULL_WIDTH = 24;
	public static final int SKULL_HEIGHT = 11;
	private float groundY;

	public Pipe(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);
		// Initialize a Random object for Random number generation
		r = new Random();
		skullUp = new Rectangle();
		skullDown = new Rectangle();
		barUp = new Rectangle();
		barDown = new Rectangle();

		this.groundY = groundY;

	}

	@Override
	public void reset(float newX) {
		// goiphuongthuc reset tronglopcha(Scrollable)
		super.reset(newX);
		// doichieucao bang 1 so ngaunhien
		height = r.nextInt(90) + 15;
		isScored = false;
	}

	@Override
	public void update(float delta) {
		// goi update trong superclass (Scrollable)
		super.update(delta);

		// set() giúpthiet lap goctrentraivarong, caocuahinhchunhat

		barUp.set(position.x, position.y, width, height);
		barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				groundY - (position.y + height + VERTICAL_GAP));

		// chieurong skull la 24. barchi 22 do do skull can dich sang trai 1
		// pixel
		skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
				- SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
		skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
				SKULL_WIDTH, SKULL_HEIGHT);

	}

	public Rectangle getSkullUp() {
		return skullUp;
	}

	public Rectangle getSkullDown() {
		return skullDown;
	}

	public Rectangle getBarUp() {
		return barUp;
	}

	public Rectangle getBarDown() {
		return barDown;
	}

	public boolean collides(Bird bird) {
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
					|| Intersector.overlaps(bird.getBoundingCircle(), barDown)
					|| Intersector.overlaps(bird.getBoundingCircle(), skullUp) || Intersector
						.overlaps(bird.getBoundingCircle(), skullDown));
		}
		return false;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}

	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}

}
