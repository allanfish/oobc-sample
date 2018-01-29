package tdd.NASA;

public class MartianCar {

  public static final int NORTH = 0;
  public static final int EAST = 1;
  public static final int SOUTH = 2;
  public static final int WEST = 3;
  public static final String DIRECTION_MAP = "NESW"; 
  
  private int x = 0;
  private int y = 0;
  private boolean fall = false;
  private int direction = -1;
  private SafeArea safeArea = null;

  public MartianCar(SafeArea safeArea, int x, int y, String d) {
    this.safeArea = safeArea;
    this.x = x;
    this.y = y;
    this.parseDirection(d); 
  }
  

  public MartianCar( int x, int y, String d) {
    this.safeArea =new SafeArea(5,5);
    this.x = x;
    this.y = y;
    this.parseDirection(d); 
  }


  private void parseDirection(String d) {
    this.direction = DIRECTION_MAP.indexOf(d);
  }

  public void execute(String order) {
    for (int i = 0; i < order.length(); i ++) {
      String command = order.substring(i, i + 1);
      
      switch (command) {
        case "L":
        case "R":
          this.changeDirection(command); 
          break;
        case "M":
          switch(this.direction) {
            case NORTH:
              this.y += 1;
              break;
            case SOUTH:
              this.y -= 1;
              break;
            case WEST:
              this.x -= 1;
              if(false==safeArea.isSafe(this.getPosition())){
                fall = true;
                this.x+=1;
              }
              break;
            case EAST:
              this.x += 1;
              if(false==safeArea.isSafe(this.getPosition())){
                fall = true;
                this.x-=1;
              }
              break;
          }
      }
    }
  }
  
  private void changeDirection(String command) {
    switch (command) {
      case "L":
        this.direction += 3;
        break;
      case "R":
        this.direction += 1;
    }
    
    this.direction %= 4;
  }
  
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public String getDirection() {
    return DIRECTION_MAP.substring(direction, direction + 1);
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }
  
  public String getPosition() {

    String pos=this.x + " " + this.y + " " + this.getDirection();
    if(fall)
      pos+=" RIP";
    return pos;
  }
}
