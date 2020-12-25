package Extras;

import processing.core.PVector;

@SuppressWarnings("serial")
public class IVector extends PVector {
	public int x, y;

	public IVector(int x, int y) {
		super(x, y);

		this.x = x;
		this.y = y;
	}

	public IVector add(IVector i) {
		this.x += i.x;
		this.y += i.y;

		return copy();
	}

	public IVector sub(IVector i) {
		this.x -= i.x;
		this.y -= i.y;

		return copy();
	}

	public IVector copy() {
		return new IVector(this.x, this.y);
	}

	public boolean equals(IVector other) {
		return this.x == other.x && this.y == other.y;
	}

	public IVector wrap(IVector horizontal, IVector vertical) {
		if (this.x < horizontal.x)
			this.x = horizontal.y;
		else if (this.x >= horizontal.y)
			this.x = horizontal.x;

		if (this.y < vertical.x)
			this.y = vertical.y;
		else if (this.y >= vertical.y)
			this.y = vertical.x;

		return this.copy();
	}
}
