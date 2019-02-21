
class Tabuleiro{
  int[] board;
  int side;
  Tabuleiro pai;
  char action;
  int zeroIndex;

  public Tabuleiro (int side){
    this.side = side;
    board = new int[(side*side)+1];
  }

  public void setPai(Tabuleiro pai){
    this.pai = pai;
  }

  /*public int findSpace(){
    for (int i=1; i<=this.side*this.side; i++){
      if(this.board[i]==0){
        return i;
      }
    }
    return 0;
  }*/

  public Tabuleiro Right(){
    Tabuleiro res = this;
    if((this.zeroIndex % this.side) != 0){
      res.board[this.zeroIndex] = res.board[this.zeroIndex + 1];
      res.board[this.zeroIndex + 1] = 0;
      res.action = 'R';
      res.zeroIndex = this.zeroIndex + 1;
      return res;
    }
    return null;
  }

  public Tabuleiro Left() {
    Tabuleiro res = this;
    if((this.zeroIndex % res.side) != 1){
      res.board[this.zeroIndex] = res.board[this.zeroIndex - 1];
      res.board[this.zeroIndex - 1] = 0;
      res.action = 'L';
      res.zeroIndex = this.zeroIndex - 1;
      return res;
    }
    return null;
  }

  public Tabuleiro Up() {
    Tabuleiro res = this;
    if(this.zeroIndex - res.side > 0){
      res.board[this.zeroIndex] = res.board[this.zeroIndex - res.side];
      res.board[this.zeroIndex - res.side] = 0;
      res.action = 'U';
      res.zeroIndex = this.zeroIndex - res.side;
      return res;
    }
    return null;
  }

  public Tabuleiro Down() {
    Tabuleiro res = this;
    if(this.zeroIndex + res.side <= res.side*res.side){
      res.board[this.zeroIndex] = res.board[this.zeroIndex + res.side];
      res.board[this.zeroIndex + res.side] = 0;
      res.action = 'D';
      res.zeroIndex = this.zeroIndex + res.side;
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
    if ( ( (this.side%2==1) && (inv%2==0) ) || ( (this.side%2==0) && (( (this.zeroIndex/this.side)%2==1 ) == (inv%2==0) )) ){
      return true;
    }
    return false; //test
    }

  }
