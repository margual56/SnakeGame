class Food{
  private IVector pos;
  
  public Food(){
    pos = new IVector((int)random(width/cellsize), (int)random(height/cellsize));
  }
  
  public void show(){
    int margin = 4;
    rect(pos.x*cellsize+margin, pos.y*cellsize+margin, cellsize-margin*2+1, cellsize-margin*2+1);
  }
  
  public void reposition(){
    pos = new IVector((int)random(width/cellsize), (int)random(height/cellsize));
  }
  
  public IVector pos(){
    return pos;
  }
}
