public class AllLink implements Linkalg{
  @Override
  public void linkalg(Cell[][] cellmap, Cell cell, int lindex, int cindex){
    for(int i = -1; i < 2; i++){
      for(int j = -1; j < 2; j++){
        int lres = lindex + i;
        int cres = cindex + j;
        cell.link(
            lres >= 0 &&
            cres >= 0 &&
            lres < cellmap.length &&
            cres < cellmap[lres].length ?
            cellmap[lres][cres] : new Cell(0));
      }
    }
  }
}
