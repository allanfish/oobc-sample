package tdd.NASA;

import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;


public class NasaTest {
  private Supervisor supervisor=null;;
  private SafeArea safeArea = null;
  @Before
  public void initSupervisor() {
  this.safeArea = new SafeArea(5,5);
  this.supervisor= new Supervisor(safeArea);
    
  }
  
  @Test
  public void init() {
    MartianCar mCar = supervisor.sendMartianCar("0 0 E");
    
    Assertions.assertEquals("0 0 E", mCar.getPosition());
  }
  
  @Test
  public void moveForward() {
    int x = 0;
    int y = 0;
    String direction = "E";
    MartianCar mCar = supervisor.sendMartianCar(x + " " + y + " " + direction);
    
    Assertions.assertEquals(x + 1, supervisor.sendOrder(mCar, "M").getX());
  }
  
  @Test
  public void turnLeftAfterMove() {
    int x = 0;
    int y = 0;
    String direction = "E";
    MartianCar mCar = new MartianCar(x, y, direction);
    
    mCar.execute("ML");
    
    Assertions.assertEquals(x + 1, mCar.getX());
    Assertions.assertEquals("N", mCar.getDirection()); 
  }
  
  @Test
  public void turnRightAfterMove() {
    int x = 0;
    int y = 0;
    String direction = "E";
    MartianCar mCar = new MartianCar(x, y, direction);
    
    mCar.execute("MR");
    
    Assertions.assertEquals(x + 1, mCar.getX());
    Assertions.assertEquals("S", mCar.getDirection()); 
  }
  
  @Test
  public void complexOrder() {
    int x = 1;
    int y = 2;
    String direction = "N";
    MartianCar mCar = new MartianCar(x, y, direction);
    
    mCar.execute("LMLMLMLMML");
    
    Assertions.assertEquals(1, mCar.getX());
    Assertions.assertEquals(3, mCar.getY());
    Assertions.assertEquals("W", mCar.getDirection()); 
    
    MartianCar mCar2 = new MartianCar(3, 3, "E");
    
    mCar2.execute("MMRMMRMRRM");
    
    Assertions.assertEquals(5, mCar2.getX());
    Assertions.assertEquals(1, mCar2.getY());
    Assertions.assertEquals("E", mCar2.getDirection()); 
  }
  
  @Test
  public void firstFall() {
    MartianCar mCar = supervisor.sendMartianCar("5 5 E");
    mCar.execute("M");
    Assertions.assertEquals("5 5 E RIP", mCar.getPosition());
  }
  
  @Test
  public void anotherFall() {
    MartianCar mCar = supervisor.sendMartianCar("0 5 W");
    mCar.execute("M");
    Assertions.assertEquals("0 5 W RIP", mCar.getPosition());
  }
  
  @Test
  public void runOnSafePlace() {
    MartianCar mCar = supervisor.sendMartianCar("3 3 W");
    Assertions.assertEquals(true, safeArea.isSafe(mCar.getPosition()));
  }
}
