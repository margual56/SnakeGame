package app;

import Extras.IVector;
import processing.core.PConstants;

public class Snake {
	private IVector positions[];
	private int direction = 0;
	private boolean turned = true;
	private IVector directions[] = { new IVector(1, 0), new IVector(0, 1), new IVector(0, -1), new IVector(-1, 0) };
	private boolean eaten = false;

	public Snake(int starting_x, int starting_y) {
		positions = new IVector[3];

		for (int i = 0; i < 3; i++)
			positions[i] = new IVector(starting_x - i, starting_y);
	}

	public int length() {
		return positions.length;
	}

	public void move(MainWindow app) {
		if (eaten) {
			IVector tmp[] = new IVector[length() + 1];

			for (int i = length(); i > 0; i--) {
				tmp[i] = positions[i - 1].copy();
			}

			tmp[0] = positions[0].add(directions[direction]);

			positions = tmp;

			eaten = false;
		} else {
			for (int i = length() - 1; i > 0; i--) {
				positions[i] = positions[i - 1].copy();
			}

			positions[0].add(directions[direction]);
			positions[0].wrap(new IVector(0, (int) (app.width / app.cellsize)), new IVector(0, (int) (app.height / app.cellsize)));
		}

		turned = true;
	}

	public void show(int margin, MainWindow app) {
		app.fill(255);

		for (int i = length() - 1; i >= 0; i--) {

			if (i == 0)
				app.fill(255, 100, 100);

			app.rect(positions[i].x * app.cellsize + margin, positions[i].y * app.cellsize + margin, app.cellsize - margin * 2 + 1,
					app.cellsize - margin * 2 + 1);
		}
	}

	public boolean changeDirection(int code) {
		if (!turned)
			return false;

		switch (code) {
		case PConstants.RIGHT:
			if (direction != 3) {
				direction = 0;
				turned = false;
				return true;
			}

			return false;

		case PConstants.DOWN:
			if (direction != 2) {
				direction = 1;
				turned = false;
				return true;
			}
			return false;

		case PConstants.UP:
			if (direction != 1) {
				direction = 2;
				turned = false;
				return true;
			}

			return false;

		case PConstants.LEFT:
			if (direction != 0) {
				direction = 3;
				turned = false;
				return true;
			}

			return false;
		}
		return false;
	}

	public boolean eat(Food food) {
		if (positions[0].equals(food.pos())) {
			eaten = true;
		}

		return eaten;
	}

	public boolean lost() {
		for (int i = length() - 1; i > 0; i--)
			if (positions[i].equals(positions[0]))
				return true;

		return false;
	}
}
