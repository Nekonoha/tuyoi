import java.util.*;
public class Cell{
  public int status;
  public int nextStatus = 0;
  public List<Cell> links = new ArrayList<Cell>();
  public int num;
  Cell(int status){
    this.status = status;
  }
  Cell(int status, int num){
    this(status);
    this.num = num;
  }

  void link(Cell target){
    if(target != this) links.add(target);
  }

  void judge(){
  }

  void update(){
  }
}
