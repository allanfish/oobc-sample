package tdd.NASA;

public class Supervisor {
  private SafeArea  safeArea;

  public Supervisor(SafeArea area){
    this.safeArea = area;
  }

  public MartianCar sendMartianCar(String originalState) {
    String array[] = originalState.split(" ");
    int x = Integer.valueOf(array[0]);
    int y = Integer.valueOf(array[1]);
    String direction = array[2];
    return new MartianCar(safeArea,x, y, direction);
  }
  
  public MartianCar sendOrder(MartianCar car, String order) {
    car.execute(order);
    return car;
  }

  public static void main(String[] args){
    String[] commands ={ "5 5","1 2 N","LMLMLMLMML","3 3 E","MMM"};
    String[] range=commands[0].split(" ");
    int x = Integer.parseInt(range[0]);
    int y = Integer.parseInt(range[1]);
    SafeArea safecheck=new SafeArea(x,y);
    Supervisor supervisor = new Supervisor(safecheck);
    for(int i=1;i<commands.length;){
      MartianCar car=supervisor.sendMartianCar(commands[i++]);
      car =  supervisor.sendOrder(car,commands[i++]);
      System.out.println(car.getPosition());
    }
  }


}
