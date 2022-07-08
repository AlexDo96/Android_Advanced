package com.chao.GameObject;

import java.util.Random;

public class Pipe extends Scrollable {

	private Random r;

	public Pipe(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		// Initialize a Random object for Random number generation
		r = new Random();
	}

	@Override
	public void reset(float newX) {
		// goiphuongthuc reset tronglopcha(Scrollable)
		super.reset(newX);
		// doichieucao bang 1 so ngaunhien
		height = r.nextInt(90) + 15;
	}

}
