package tdd.NASA;

public class SafeArea {
  private int x_range;
  private int y_range;
  
  public SafeArea(int x,int y){
    this.x_range = x;
    this.y_range = y;
  }
  public boolean isSafe(String position) {
    String array[] = position.split(" ");
    if (x_range < Integer.valueOf(array[0])||Integer.valueOf(array[0])<0) {
      return false;
    }
    if (y_range < Integer.valueOf(array[1])||Integer.valueOf(array[1])<0 ) {
      return false;
    }
    return true;
  }
}
