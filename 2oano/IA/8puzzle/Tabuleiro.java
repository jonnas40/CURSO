import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

class Tabuleiro{
  int[] board;
  int side;
  Tabuleiro pai;
  int zeroIndex;
  int depth;
  int scoreO;
  int scoreM;

  public Tabuleiro (int side){
    this.side = side;
    board = new int[(side*side)+1];
    this.pai = null;
    this.depth = 0;
  }

  public static int setScoreO (Tabuleiro src, Tabuleiro dest){
    int score = 0;
    for (int i = 1; i <= src.side*src.side; i++) {
      if (src.board[i]!=dest.board[i]) score++;
    }
    return score;
  }

/*
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
            default:
              this.adjs[0]=this.Up();
              this.adjs[1]=this.Right();
              this.adjs[2]=this.Down();
              this.adjs[3]=this.Left();
            }
        }

        public boolean compareTo(Tabuleiro comp){
          for (int i = 1; i <= this.side*this.side; i++) {
            if(this.board[i]!=comp.board[i]) return false;
          }
          return true;
        }*/

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


//------------------- Jon's testing functions ------------------------
//-------------- UwU pwease dwon't rewove me UwU ---------------------


  public static Tabuleiro newDown(Tabuleiro src, Tabuleiro dest, boolean flag){
    Tabuleiro res = new Tabuleiro(src.side);
    System.arraycopy(src.board, 0, res.board, 0, src.board.length);
    if(src.zeroIndex + src.side <= src.side*src.side){
      res.board[src.zeroIndex] = src.board[src.zeroIndex + src.side];
      res.board[src.zeroIndex + src.side] = 0;
      res.pai = src;
      res.depth = src.depth+1;
      res.zeroIndex = src.zeroIndex + src.side;
      if(flag){
        res.scoreO=setScoreO(res, dest);
        res.scoreM=setScoreM(res, dest);
      }
      return res;
    }
    return src;
  }


  public static Tabuleiro newUp(Tabuleiro src, Tabuleiro dest, boolean flag){
    Tabuleiro res = new Tabuleiro(src.side);
    System.arraycopy(src.board, 0, res.board, 0, src.board.length);
    if(src.zeroIndex - src.side > 0){
      res.board[src.zeroIndex] = src.board[src.zeroIndex - src.side];
      res.board[src.zeroIndex - src.side] = 0;
      res.pai = src;
      res.depth = src.depth+1;
      res.zeroIndex = src.zeroIndex - src.side;
      if(flag){
        res.scoreO=setScoreO(res, dest);
        res.scoreM=setScoreM(res, dest);
      }
      return res;
    }
    return src;
  }


  public static Tabuleiro newRight(Tabuleiro src, Tabuleiro dest, boolean flag){
    Tabuleiro res = new Tabuleiro(src.side);
    System.arraycopy(src.board, 0, res.board, 0, src.board.length);
    if((src.zeroIndex % src.side) != 0){
      res.board[src.zeroIndex] = src.board[src.zeroIndex + 1];
      res.board[src.zeroIndex + 1] = 0;
      res.pai = src;
      res.depth = src.depth+1;
      res.zeroIndex = src.zeroIndex + 1;
      if(flag){
        res.scoreO=setScoreO(res, dest);
        res.scoreM=setScoreM(res, dest);
      }
      return res;
    }
    return src;
  }


  public static Tabuleiro newLeft(Tabuleiro src, Tabuleiro dest, boolean flag){
    Tabuleiro res = new Tabuleiro(src.side);
    System.arraycopy(src.board, 0, res.board, 0, src.board.length);
    if((src.zeroIndex % src.side) != 1){
      res.board[src.zeroIndex] = src.board[src.zeroIndex - 1];
      res.board[src.zeroIndex - 1] = 0;
      res.pai = src;
      res.depth = src.depth+1;
      res.zeroIndex = src.zeroIndex - 1;
      if(flag){
        res.scoreO=setScoreO(res, dest);
        res.scoreM=setScoreM(res, dest);
      }
      return res;
    }
    return src;
  }


  public static LinkedList<Tabuleiro> tabSonsGreedy(Tabuleiro src, Tabuleiro dest, LinkedList<Tabuleiro> visited){
    LinkedList<Tabuleiro> sons = new LinkedList<Tabuleiro>();
    Tabuleiro tabs[] = new Tabuleiro[4];
    tabs[0] = newUp(src, dest, true);
    tabs[1] = newDown(src, dest, true);
    tabs[2] = newLeft(src, dest, true);
    tabs[3] = newRight(src, dest, true);
    for(int i=0; i<4; i++){
      if(tabs[i].testSonGreedy(visited)){
        visited.add(tabs[i]);
        sons.add(tabs[i]);
      }
    }
    return sons;
  }

  
  public boolean testSonGreedy(LinkedList<Tabuleiro> visited){
    boolean flag = true;
    for(Tabuleiro test : visited){
      if(Arrays.equals(this.board,test.board)){
        flag = false;
        return flag;
      }
    }
    return flag;
  }


  public static LinkedList<Tabuleiro> tabSonsA(Tabuleiro src, Tabuleiro dest, Map<int[],Integer> visited){
    LinkedList<Tabuleiro> sons = new LinkedList<Tabuleiro>();
    Tabuleiro tabs[] = new Tabuleiro[4];
    tabs[0] = newUp(src, dest, true);
    tabs[1] = newDown(src, dest, true);
    tabs[2] = newLeft(src, dest, true);
    tabs[3] = newRight(src, dest, true);
    for(int i=0; i<4; i++){
      if(tabs[i].testSonA(visited)){
        //visited.remove(tabs[i].board);  
        visited.replace(tabs[i].board,tabs[i].scoreO+tabs[i].depth);
        sons.add(tabs[i]);
      }
    }
    return sons;
  }


  public boolean testSonA(Map<int[],Integer> visited){
    boolean flag = true;
    if(visited.containsKey(this.board)){
      if (visited.get(this.board) < (this.scoreO+this.depth)) {
        flag = false;
        return flag;
      }
    }
    return flag;
  }


  public static LinkedList<Tabuleiro> tabSons(Tabuleiro src, Tabuleiro dest, LinkedList<Tabuleiro> visited){
    LinkedList<Tabuleiro> sons = new LinkedList<Tabuleiro>();
    Tabuleiro tabs[] = new Tabuleiro[4];
    tabs[0] = newUp(src, dest, false);
    tabs[1] = newDown(src, dest, false);
    tabs[2] = newLeft(src, dest, false);
    tabs[3] = newRight(src, dest, false);
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
      if(Arrays.equals(this.board,test.board) && this.depth >= test.depth){
        flag = false;
        return flag;
      }
    }
    return flag;
  }


  public static void print_path(Tabuleiro tab){
    Stack<Tabuleiro> s = new Stack<Tabuleiro>();
    while(tab.pai != null){
      s.push(tab);
      tab = tab.pai;
    }
    s.push(tab);
    while(!s.isEmpty()){
      tab = s.pop();
      aux_print(tab);
      System.out.println("Depth: "+ tab.depth);
      System.out.println("-------------");
    }
  }


  public static void aux_print(Tabuleiro tab){
    for (int i=1; i<=tab.side*tab.side; i++){
      System.out.print(tab.board[i] + " ");
      if(i%tab.side == 0) System.out.println();
    }
    //System.out.println();
  }

  public static int setScoreM(Tabuleiro src, Tabuleiro tabF){
    int pos[] = new int[src.side*src.side];
    int pos_init[] = new int[src.side*src.side];
    for (int i = 1; i <= src.side*src.side; i++) {
      pos[tabF.board[i]]=i-1;
      pos_init[src.board[i]]=i-1;
    }
    /*for(int i=0;i<src.side*src.side ; i++){
      System.out.println("pos[" + i + "]: " + pos[i]);
      System.out.println("pos_init[" + i + "]: " + pos_init[i]);
    }*/
    int counter=0;
    for (int i = 0; i < src.side*src.side; i++) {
      int pos_a=pos_init[i];
      while(pos_a / src.side != pos[i] / src.side){ //linhas
        if ( (pos_a / src.side) > (pos[i] / src.side) ) { //up
          pos_a -= src.side;
          counter++;
        }
        else if ( (pos_a / src.side) < (pos[i] / src.side) ){ //down
          pos_a += src.side;
          counter++;
        }
      }
      while(pos_a % src.side != pos[i] % src.side) { //colunas
        if ( pos_a > pos[i] ){ //left
          pos_a--;
          counter++;
        }
        else if (pos_a < pos[i]){ //right
          pos_a++;
          counter++;
        }
      }
    }
    return counter;
  }
}
