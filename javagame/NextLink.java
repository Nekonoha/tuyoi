public class NextLink implements Linkalg{
  @Override
  public void linkalg(Cell[][] cellmap, Cell cell, int lindex, int cindex){
    link(cellmap, cell, lindex + 1, cindex);
    link(cellmap, cell, lindex - 1, cindex);
    link(cellmap, cell, lindex, cindex + 1);
    link(cellmap, cell, lindex, cindex - 1);
  }

  private void link(Cell[][] cellmap, Cell cell, int l, int c){
    try{
      cell.link(cellmap[l][c]);
    }catch(ArrayIndexOutOfBoundsException e){
      cell.link(new Cell(0));
    }
  }
}
