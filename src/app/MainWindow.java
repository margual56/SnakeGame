package app;

import processing.core.PApplet;

public class MainWindow extends PApplet {
	int cellsize = 50;
	boolean lost = false;

	Snake snake;
	Food f;

	public void settings() {
		size(1000, 1000);
	}

	public void setup() {
		snake = new Snake((int) ((width / cellsize) / 2), (int) ((height / cellsize) / 2));
		f = new Food(this);
	}

	public void draw() {
		background(0);

		noFill();
		stroke(100);
		// drawGrid();

		if (frameCount % 10 == 0) {
			snake.move(this);

			if (snake.eat(f)) {
				f.reposition(this);
			}
		}

		noStroke();
		snake.show(2, this);

		noStroke();
		fill(50, 255, 100);
		f.show(this);

		if (snake.lost()) {
			lost = true;
			fill(255, 0, 0, 100);
			rect(0, 0, width, height);
			noLoop();
		}
	}

	void drawGrid() {
		for (int x = 0; x < width; x += cellsize) {
			for (int y = 0; y < height; y += cellsize) {
				rect(x, y, cellsize, cellsize);
			}
		}
	}

	public void keyPressed() {
		if (snake.changeDirection(keyCode))
			return;
	}

	int sign(float n) {
		if (n == 0)
			return 0;

		return round(abs(n) / n);
	}

	public static void main(String[] args) {
		String[] processingArgs = { "Turing Machine" };
		MainWindow mySketch = new MainWindow();
		PApplet.runSketch(processingArgs, mySketch);
	}
}