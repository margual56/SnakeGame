package app;

import Extras.IVector;

public class Food {
	private IVector pos;

	public Food(MainWindow app) {
		pos = new IVector((int) app.random(app.width / app.cellsize), (int) app.random(app.height / app.cellsize));
	}

	public void show(MainWindow app) {
		int margin = 4;
		app.rect(pos.x * app.cellsize + margin, pos.y * app.cellsize + margin, app.cellsize - margin * 2 + 1,
				app.cellsize - margin * 2 + 1);
	}

	public void reposition(MainWindow app) {
		pos = new IVector((int) app.random(app.width / app.cellsize), (int) app.random(app.height / app.cellsize));
	}

	public IVector pos() {
		return pos;
	}
}
