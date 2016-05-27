public class LifegameCell extends Cell{
  LifegameCell(int status){
    super(status);
  }
  LifegameCell(int status, int num){
    super(status, num);
  }
  @Override
  void judge(){
    int count = 0;
    for(Cell cell: links){
      if(cell.status == 1){
        if(++count == 4) break;
      }
    }

    if(status == 0){
      if(count == 3) nextStatus = 1;
    }else if(status == 1){
      if(2 <= count && count <= 3){
        nextStatus = 1;
      }
    }
  }

  @Override
  void update(){
    status = nextStatus;
    nextStatus = 0;
  }
}
