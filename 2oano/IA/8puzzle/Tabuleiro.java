
class Tabuleiro{
  int[] board;
  int side;
  Tabuleiro pai;
  char action;
  
  public Tabuleiro (int side){
    this.side = side;
    board = new int[(side*side)+1];
  }

  public void setPai(Tabuleiro pai){
    this.pai = pai;
  }

  public int findSpace(){
    for (int i=1; i<=this.side*this.side; i++){
      if(this.board[i]==0){
        return i;
      }
    }
    return 0;
  }

  public Tabuleiro Right(){
    Tabuleiro res = this;
    int space = this.findSpace();
    if((space % this.side) != 0){
      res.board[space] = res.board[space + 1];
      res.board[space + 1] = 0;
      res.action = 'R';
      return res;
    }
    return null;
  }

  public Tabuleiro Left() {
    Tabuleiro res = this;
    int space = res.findSpace();
    if((space % res.side) != 1){
      res.board[space] = res.board[space - 1];
      res.board[space - 1] = 0;
      res.action = 'L';
      return res;
    }
    return null;
  }

  public Tabuleiro Up() {
    Tabuleiro res = this;
    int space = res.findSpace();
    if(space - res.side > 0){
      res.board[space] = res.board[space - res.side];
      res.board[space - res.side] = 0;
      res.action = 'U';
      return res;
    }
    return null;
  }

  public Tabuleiro Down() {
    Tabuleiro res = this;
    int space = res.findSpace();
    if(space + res.side <= res.side*res.side){
      res.board[space] = res.board[space - res.side];
      res.board[space - res.side] = 0;
      res.action = 'D';
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

  public boolean solvability(){
    int inv = 0;
    for (int i = 1; i <= this.side*this.side; i++) {
      if (this.board[i]>i) {
        inv+=this.board[i]-i;
      }
    }
    if (this.side%2!=0 && inv%2==0) return true;
    else {
      if (this.side)   {
        
      }
    }
  }

}
