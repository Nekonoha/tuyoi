public class LightsupCell extends Cell{
  LightsupCell(int status){
    super(status);
  }
  LightsupCell(int status, int num){
    super(status, num);
  }

  @Override
  void update(){
    for(Cell c: links)
      c.status = c.status == 0 ? 1 : 0;
    this.status = this.status == 0 ? 1 : 0;
  }
}
