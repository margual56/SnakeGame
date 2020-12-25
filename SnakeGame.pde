int cellsize = 50;
boolean lost = false;

Snake snake;
Food f;

void setup() {
  size(1000, 1000);

  snake = new Snake((int)((width/cellsize)/2), (int)((height/cellsize)/2));
  f = new Food();
}

void draw() {
  background(0);

  noFill();
  stroke(100);
  //drawGrid();

  if (frameCount%10==0){
    snake.move();
    
    if(snake.eat(f)){
      f.reposition();
    }
  }
    
  noStroke();
  snake.show(2);

  noStroke();
  fill(50, 255, 100);
  f.show();
  
  if(snake.lost()){
    lost = true;
    fill(255, 0, 0, 100);
    rect(0, 0, width, height);
    noLoop();
  }
}

void drawGrid() {
  for (int x = 0; x<width; x+=cellsize) {
    for (int y = 0; y<height; y+=cellsize) {
      rect(x, y, cellsize, cellsize);
    }
  }
}

void keyPressed(){
  if(snake.changeDirection(keyCode))
    return;
}
