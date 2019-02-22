
class Tabuleiro {
  int[] board;
  int side;
  Tabuleiro pai;
  char action;
  int zeroIndex;
  Tabuleiro[] adjs = new Tabuleiro[3];

  public Tabuleiro (int side){
    this.side = side;
    board = new int[(side*side)+1];
  }

  public void setPai(Tabuleiro pai){
    this.pai = pai;
  }

  public Tabuleiro Right(){
    Tabuleiro res = new Tabuleiro(this.side*this.side);
    res.copyTab(this);
    if((this.zeroIndex % this.side) != 0){
      res.board[this.zeroIndex] = this.board[this.zeroIndex + 1];
      res.board[this.zeroIndex + 1] = 0;
      res.action = 'R';
      res.zeroIndex = this.zeroIndex + 1;
      return res;
    }
    return null;
  }

  public Tabuleiro Left() {
    Tabuleiro res = new Tabuleiro(this.side*this.side);
    res.copyTab(this);
    if((this.zeroIndex % this.side) != 1){
      res.board[this.zeroIndex] = this.board[this.zeroIndex - 1];
      res.board[this.zeroIndex - 1] = 0;
      res.action = 'L';
      res.zeroIndex = this.zeroIndex - 1;
      return res;
    }
    return null;
  }

  public Tabuleiro Up() {
    Tabuleiro res = new Tabuleiro(this.side*this.side);
    res.copyTab(this);
    if(this.zeroIndex - this.side > 0){
      res.board[this.zeroIndex] = this.board[this.zeroIndex - this.side];
      res.board[this.zeroIndex - this.side] = 0;
      res.action = 'U';
      res.zeroIndex = this.zeroIndex - this.side;
      return res;
    }
    return null;
  }

  public Tabuleiro Down() {
    Tabuleiro res = new Tabuleiro(this.side*this.side);
    res.copyTab(this);
    if(this.zeroIndex + this.side <= this.side*this.side){
      res.board[this.zeroIndex] = this.board[this.zeroIndex + this.side];
      res.board[this.zeroIndex + this.side] = 0;
      res.action = 'D';
      res.zeroIndex = this.zeroIndex + this.side;
      return res;
    }
    return null;
  }

  public String printTab(){
    String res = "";
    for(int i=1; i<=this.side*this.side; i++){
      res+=this.board[i];
      res+=" ";
      if(i%this.side==0) res+='\n';
    }
    return res;
  }

  public String printPath(){
    String res = "";
    this.printPathR(res);
    return res;
  }

  private String printPathR(String res){
    if (this.pai!=null) {
      printPathR(res);
    }
    return res + this.printTab() + this.actionPrint();
  }

  private String actionPrint(){
    String res = "";
    switch (this.action) {
      case 'L': 
        res += "0 moveu-se para a esquerda";
        break;
      case 'R': 
        res += "0 moveu-se para a direita";
        break;
      case 'U': 
        res += "0 moveu-se para cima";
        break;
      case 'D': 
        res += "0 moveu-se para baixo";
        break;
    }
    return res;
  }

  public boolean solvability(){
    int inv = 0;
    for (int i = 1; i <= this.side*this.side; i++) {
      if (this.board[i]>i) {
        inv+=this.board[i]-i;
      }
    }
    if ( ( (this.side%2==1) && (inv%2==0) ) || ( (this.side%2==0) && (( (this.zeroIndex/this.side)%2==1 ) == (inv%2==0) )) ){
      return true;
    }
    return false;
  }
  
  public Tabuleiro copyTab(Tabuleiro src){
    this.zeroIndex=src.zeroIndex;
    this.setPai(src);
    for (int i = 1; i <=src.side*src.side; i++) this.board[i] = src.board[i];
    return this;
  }

  public Tabuleiro[] adjs_no() {
    switch (this.action){
      case 'U':
        adjs[0]=this.Up();
        adjs[1]=this.Right();
        adjs[2]=this.Left();
        break;
      case 'L':
        adjs[0]=this.Up();
        adjs[1]=this.Left();
        adjs[2]=this.Down();
        break;
      case 'R':
        adjs[0]=this.Up();
        adjs[1]=this.Right();
        adjs[2]=this.Down();
        break;
      case ('D'): 
        adjs[1]=this.Right();
        adjs[2]=this.Left();
        adjs[0]=this.Down();
        break;
      default:
        adjs[0]=this.Up();
        adjs[1]=this.Right();
        adjs[2]=this.Down();
        adjs[3]=this.Left();
      }
    return adjs;
  }

  public boolean compareTo(Tabuleiro comp){
    boolean flag = true;
    for (int i = 1; i < this.side*this.side; i++) {
      if(this.board[i]!=comp.board[i]) return flag=false;
    }
    return flag;
  }
      
}