class Snake {
  private IVector positions[];
  private int direction = 0;
  private boolean turned = true;
  private IVector directions[] = {new IVector(1, 0), new IVector(0, 1), new IVector(0, -1), new IVector(-1, 0)};
  private boolean eaten = false;

  public Snake(int starting_x, int starting_y) {
    positions = new IVector[3];

    for (int i = 0; i<3; i++)
      positions[i] = new IVector(starting_x-i, starting_y);
  }

  public int length() {
    return positions.length;
  }

  public void move() {
    if (eaten) {
      IVector tmp[] = new IVector[length()+1];

      for (int i = length(); i>0; i--) {
        tmp[i] = positions[i-1].copy();
      }

      tmp[0] = positions[0].add(directions[direction]);

      positions = tmp;

      eaten = false;
    } else {
      for (int i = length()-1; i>0; i--) {
        positions[i] = positions[i-1].copy();
      }

      positions[0].add(directions[direction]);
      positions[0].wrap(new IVector(0, (int)(width/cellsize)), new IVector(0, (int)(height/cellsize)));
    }
    
    turned = true;
  }

  public void show(int margin) {
    fill(255);

    for (int i = length()-1; i>=0; i--) {

      if (i == 0)
        fill(255, 100, 100);

      rect(positions[i].x*cellsize+margin, positions[i].y*cellsize+margin, cellsize-margin*2+1, cellsize-margin*2+1);
    }
  }

  public boolean changeDirection(int code) {
    if(!turned)
      return false;
    
    switch(code) {
    case RIGHT:
      if (direction != 3) {
        direction = 0;
        turned = false;
        return true;
      }

      return false;

    case DOWN:
      if (direction != 2) {
        direction = 1;
        turned = false;
        return true;
      }
      return false;

    case UP:
      if (direction != 1) {
        direction = 2;
        turned = false;
        return true;
      }

      return false;

    case LEFT:
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
  
  public boolean lost(){    
    for(int i = length()-1; i>0; i--)
      if(positions[i].equals(positions[0]))
        return true;
    
    return false;
  }
}
