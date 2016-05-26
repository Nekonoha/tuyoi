import java.util.*;
public class Main{
	public static void main(String[] args){
    Random rd = new Random();
    int le = 5;
    int ce = 5;
    Cell[][] fie = new Cell[le][ce];
    for(int i = 0; i < fie.length; i++){
      for(int j = 0; j < fie[i].length; j++){
        fie[i][j] = new LightsupCell(rd.nextInt(2));
      }
    }
    Game lg = new Game(fie);
    lg.link(new NextLink());

    Scanner sc = new Scanner(System.in);
    try{
      while(true){
        p(fie);
        int x = sc.nextInt(), y = sc.nextInt();
        lg.cellmap[y][x].update();
      }
    }catch(Exception e){
      System.out.println(e);
    }
	}

  static void p(Cell[][] fie){
    for(int i = 0; i < fie.length; i++){
      for(int j = 0; j < fie[i].length; j++){
        System.out.print(fie[i][j].status);
      }
      System.out.println("");
    }
  }
}
