Sheet s = new Sheet();

void setup() {
  size(500,500);
  s.makeSheet();
  s.dispSheet();
}

void draw() {
  background(0);
  if (s.playGame()) { 
    
    s.calcBingo();
    s.dispSheet();
  }
  dispSheet();
}

void keyPressed(){
  if(s.playGame()){
    s.cast();
  }
}

void dispSheet() {

  System.out.println("turn :" + s.turn);

  for (int i = 0; i < s.field.length; i++) {
    for (int j = 0; j < s.field[0].length; j++) {
      if (s.field[i][j] != 0) {
        fill(255);
        text(s.field[i][j] + "\t",width/10*(i+1),height/10*(j+1));
      } else {
        fill(255,0,0);
        text("X" + "\t",width/10*(i+1),height/10*(j+1));
      }
    }
        fill(255);
        text("Press eny key",width/10, height/10*7);
  }
}