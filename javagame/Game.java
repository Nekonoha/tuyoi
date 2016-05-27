import java.util.*;
public class Game{
  public Cell[][] cellmap;
  Game(Cell[][] cellmap){
    this.cellmap = cellmap;
  }

  void update(){
    for(Cell[] line: cellmap) for(Cell cell: line){
      cell.judge();
    }

    for(Cell[] line: cellmap) for(Cell cell: line){
      cell.update();
    }
  }

  void link(Linkalg la){
    for(int i = 0; i < cellmap.length; i++){
      for(int j = 0; j < cellmap[i].length; j++){
        la.linkalg(cellmap, cellmap[i][j], i, j);
      }
    }
  }
}
