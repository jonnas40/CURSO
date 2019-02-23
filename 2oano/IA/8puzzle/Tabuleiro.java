import java.util.LinkedList;
import java.util.Queue;

class Tabuleiro {
  int[] board;
  int side;
  Tabuleiro pai;
  char action;
  int zeroIndex;
  Tabuleiro[] adjs = new Tabuleiro[4];

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
        res += "\n0 moveu-se para a esquerda";
        break;
      case 'R':
        res += "\n0 moveu-se para a direita";
        break;
      case 'U':
        res += "\n0 moveu-se para cima";
        break;
      case 'D':
        res += "\n0 moveu-se para baixo";
        break;
    }
    return res;
  }

  public boolean solvability(){
    int inv = 0;
    Tabuleiro aux = new Tabuleiro(this.side);
    aux.board = this.board;
    for (int i = 1; i <= this.side*this.side; i++) {
      for(int j=i+1;j<=this.side*this.side;j++){
          if(aux.board[i] > aux.board[j] && aux.board[j]!=0){
              inv++;
          }
      }
    }
    if((this.side%2==1) && (inv%2==0)){ // se largura for impar so interessam inversoes
      return true;
    }
    //se largura for par e 0 estiver linha par, entao inversoes impar
    else if((this.side%2==0) && (((this.zeroIndex-1)/this.side)%2==0) && (inv%2==1)){
      return true;
    }
    //se largura for par e 0 estiver linha impar, entao inversoes par
    else if((this.side%2==0) && (((this.zeroIndex-1)/this.side)%2==1) && (inv%2==0)){
      return true;
    }
    else{
      return false;
    }
  }

  public Tabuleiro copyTab(Tabuleiro src){
    this.zeroIndex=src.zeroIndex;
    this.setPai(src);
    for (int i = 1; i <=src.side*src.side; i++) this.board[i] = src.board[i];
    this.adjs_no();
    return this;
  }

  public void adjs_no(){
    switch (this.action){
      case 'U':
        this.adjs[0]=this.Up();
        this.adjs[1]=this.Right();
        this.adjs[2]=this.Left();
        break;
      case 'L':
        this.adjs[0]=this.Up();
        this.adjs[1]=this.Left();
        this.adjs[2]=this.Down();
        break;
      case 'R':
        this.adjs[0]=this.Up();
        this.adjs[1]=this.Right();
        this.adjs[2]=this.Down();
        break;
      case ('D'):
        this.adjs[1]=this.Right();
        this.adjs[2]=this.Left();
        this.adjs[0]=this.Down();
        break;
      /*default:
        this.adjs[0]=this.Up();
        this.adjs[1]=this.Right();
        this.adjs[2]=this.Down();
        this.adjs[3]=this.Left();*/
      }
  }

  public boolean compareTo(Tabuleiro comp){
    for (int i = 1; i <= this.side*this.side; i++) {
      if(this.board[i]!=comp.board[i]) return false;
    }
    return true;
  }

//------------------- Jon's testing functions ------------------------
//-------------- UwU pwease dwon't rewove me UwU ---------------------

  public Tabuleiro newDown(Tabuleiro src){
    Tabuleiro res = new Tabuleiro(this.side);
    if(this.zeroIndex + this.side <= this.side*this.side){
      res.board[this.zeroIndex] = this.board[this.zeroIndex + this.side];
      res.board[this.zeroIndex + this.side] = 0;
      res.pai = src;
      res.zeroIndex = this.zeroIndex + this.side;
      return res;
    }
    return src;
  }

  public Tabuleiro newUp(Tabuleiro src){
    Tabuleiro res = new Tabuleiro(this.side);
    if(this.zeroIndex - this.side > 0){
      res.board[this.zeroIndex] = this.board[this.zeroIndex - this.side];
      res.board[this.zeroIndex - this.side] = 0;
      res.pai = src;
      res.zeroIndex = this.zeroIndex - this.side;
      return res;
    }
    return src;
  }

  public Tabuleiro newRight(Tabuleiro src){
    Tabuleiro res = new Tabuleiro(this.side);
    if((this.zeroIndex % this.side) != 0){
      res.board[this.zeroIndex] = this.board[this.zeroIndex + 1];
      res.board[this.zeroIndex + 1] = 0;
      res.pai = src;
      res.zeroIndex = this.zeroIndex + 1;
      return res;
    }
    return src;
  }

  public Tabuleiro newLeft(Tabuleiro src){
    Tabuleiro res = new Tabuleiro(this.side);
    if((this.zeroIndex % this.side) != 1){
      res.board[this.zeroIndex] = this.board[this.zeroIndex - 1];
      res.board[this.zeroIndex - 1] = 0;
      res.pai = src;
      res.zeroIndex = this.zeroIndex - 1;
      return res;
    }
    return src;
  }

  public static LinkedList<Tabuleiro> tabSons(Tabuleiro src, Tabuleiro dest, LinkedList<Tabuleiro> visited){
    LinkedList<Tabuleiro> sons = new LinkedList<Tabuleiro>();
    Tabuleiro tabs[] = new Tabuleiro[4];
    tabs[0].newUp(src);
    tabs[1].newDown(src);
    tabs[2].newLeft(src);
    tabs[3].newRight(src);
    for(int i=0; i<4; i++){
      if(tabs[i].testSon(visited)){
        visited.add(tabs[i]);
        sons.add(tabs[i]);
      }
    }
    return sons;
  }

  public boolean testSon(LinkedList<Tabuleiro> visited){
    boolean flag = true;
    for(Tabuleiro test : visited){
      if(this.board == test.board){
        flag = false;
        break;
      }
    }
    return flag;
  }

}
