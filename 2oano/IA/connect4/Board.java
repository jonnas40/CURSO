import java.util.*;

class Board {

    char[][] board;
    char turn;
    char nextTurn;
    int lastPlayX = 0;
    int lastPlayY = 0;
    int score;
    int t=0;
    int n=0;
    LinkedList<Integer> actions = new LinkedList<Integer>();


    public Board(){
        board = new char[7][6];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                this.board[i][j] = ' ';
              }
        }
        this.turn = 'O';
        this.nextTurn = 'X';
    }


    public void setN(){
      this.n++;
    }


    public void setT(int v){
      this.t+=v;
    }


    public int getN(){
      return this.n;
    }


    public int getT(){
      return this.t;
    }


    public LinkedList<Integer> actions(){
      LinkedList<Integer> actions = new LinkedList<Integer>();
      for (int i = 0; i < 7; i++) {
        if (this.board[i][5] == ' ') actions.addLast(i);
      }
      return actions;
    }


    public static void printBoard(Board tab){
        System.out.println("+-------------+");
        for (int i=5; i>=0; i--){
          System.out.print("|" + tab.board[0][i] + "|" + tab.board[1][i] + "|" + tab.board[2][i] + "|" + tab.board[3][i] + "|" + tab.board[4][i] + "|" + tab.board[5][i] + "|" + tab.board[6][i] + "|\n");
        }
        System.out.println("+-------------+");
        System.out.println("|0|1|2|3|4|5|6|");
    }


    public static void copyBoard(Board src, Board target){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                target.board[i][j] = src.board[i][j];
            }
        }
        target.nextTurn = src.turn;
        target.turn = src.nextTurn;
        target.lastPlayX = src.lastPlayX;
        target.score = src.score;
    }

    public static Boolean compareBoard(Board a, Board b){
      for(int i=0; i<7; i++){
        for(int j=0; j<6; j++){
          if (a.board[i][j]!=b.board[i][j]) {
            return false;
          }
        }
      }
      return true;
    }


    public static LinkedList<Board> sons(Board b){
        LinkedList<Board> sons = new LinkedList<Board>();
        for (int i : b.actions()) {
          sons.add(b.play(i));
        }
        return sons;
    }


    public static List<Node<Board>> sonsM(Board b){
      List <Node<Board>> sons = new ArrayList<>();
      for (int i : b.actions() ) {
        Node<Board> played = new Node<>(b.play(i));
        sons.add(played);
      }
      return sons;
  }


    public Board play(int n){
        int i = 0;
        Board ret = new Board();
        copyBoard(this, ret);
        while (ret.board[n][i] != ' ' && i!=5) {
          i++;
        }
        ret.board[n][i] = this.nextTurn;
        ret.t=0;
        ret.n=0;
        ret.lastPlayX = n;
        ret.lastPlayY = i;
        ret.score = score(ret);
        return ret;
    }


    public int checkScore(String s){
      int numX = 0;
      int numO = 0;
      for (int i=0; i<s.length(); i++){
        if(s.charAt(i)=='X'){ numX++; }
        else if(s.charAt(i)=='O'){ numO++; }
      }
      if(numX == 1 && numO == 0){ return -1; }
      else if(numX == 2 && numO == 0){ return -10; }
      else if(numX == 3 && numO == 0){ return -50; }
      else if(numX == 4 && numO == 0){ return -9999; }
      else if(numX == 0 && numO == 1){ return 1; }
      else if(numX == 0 && numO == 2){ return 10; }
      else if(numX == 0 && numO == 3){ return 50; }
      else if(numX == 0 && numO == 4){ return 9999; }
      else { return 0; }
    }


    public int checkVert(char c){
      int total = 0;
      String s = "";
      for (int i=0; i<7; i++){
        for(int j=0; j<3; j++){
          //System.out.println("V: [" + this.lastPlayX + "],[" + (maxh-j) + "]" + j);
          s += this.board[i][j];
          s += this.board[i][j+1];
          s += this.board[i][j+2];
          s += this.board[i][j+3];
          total += checkScore(s);
          s = "";
        }
      }
      //System.out.println("Vertical: " + total);
      return total;
    }


    public int checkHor(char c){
      int total = 0;
      String s = "";
      for (int i=0; i<6; i++){
        for(int j=0; j<4; j++){
          //System.out.println("V: [" + this.lastPlayX + "],[" + (maxh-j) + "]" + j);
          s += this.board[j][i];
          s += this.board[j+1][i];
          s += this.board[j+2][i];
          s += this.board[j+3][i];
          total += checkScore(s);
          s = "";
        }
      }
      //System.out.println("Horizontal: " + total);
      return total;
    }


    public int checkDiagTL(char c){
      int total = 0;
      int x = 0;
      int y = 0;
      int values[] = new int[] {1,2,3,3,2,1};
      int auxx[] = new int[] {0,-1,-2,-2,-2,-2};
      int auxy[] = new int[] {3,4,5,5,5,5};
      String s = "";
      for(int i=0; i < 6; i++){
        int jau = 0;
        for (int j=0 ; j<values[i]; j++) {
          /*System.out.println("--------------");
          System.out.println(j);*/
          s += this.board[i+auxx[i]+j][j+auxy[i]-jau];
          /*x = i+auxx[i]+j;
          y = j+auxy[i]-jau;
          System.out.println("[" + x + "],[" + y + "]");*/
          s += this.board[i+auxx[i]+j+1][j+auxy[i]-jau-1];
          /*x = i+auxx[i]+j+1;
          y = j+auxy[i]-jau-1;
          System.out.println("[" + x + "],[" + y + "]");*/
          s += this.board[i+auxx[i]+j+2][j+auxy[i]-jau-2];
          /*x = i+auxx[i]+j+2;
          y = j+auxy[i]-jau-2;
          System.out.println("[" + x + "],[" + y + "]");*/
          s += this.board[i+auxx[i]+j+3][j+auxy[i]-jau-3];
          /*x = i+auxx[i]+j+3;
          y = j+auxy[i]-jau-3;
          System.out.println("[" + x + "],[" + y + "]");*/
          total += checkScore(s);
          s = "";
          jau += 2;
        }
        //System.out.println("--------------");
      }
      //System.out.println("DiagonalTL: " + total);
      return total;
    }


    public int checkDiagTR(char c){
      int total = 0;
      int x = 0;
      int y = 0;
      int values[] = new int[] {1,2,3,3,2,1};
      int auxx[] = new int[] {0,-1,-2,-2,-2,-2};
      int auxy[] = new int[] {2,1,0,0,0,0};
      String s = "";
      for(int i=0; i < 6; i++){
        int jau = 0;
        for (int j=0 ; j<values[i]; j++) {
          /*System.out.println("--------------");
          System.out.println(j);*/
          s += this.board[i+auxx[i]+j][j+auxy[i]+jau];
          /*x = i+auxx[i]+j;
          y = j+auxy[i]+jau;
          System.out.println("[" + x + "],[" + y + "]");*/
          s += this.board[i+auxx[i]+j+1][j+auxy[i]+jau+1];
          /*x = i+auxx[i]+j+1;
          y = j+auxy[i]+jau+1;
          System.out.println("[" + x + "],[" + y + "]");*/
          s += this.board[i+auxx[i]+j+2][j+auxy[i]+jau+2];
          /*x = i+auxx[i]+j+2;
          y = j+auxy[i]+jau+2;
          System.out.println("[" + x + "],[" + y + "]");*/
          s += this.board[i+auxx[i]+j+3][j+auxy[i]+jau+3];
          /*x = i+auxx[i]+j+3;
          y = j+auxy[i]+jau+3;
          System.out.println("[" + x + "],[" + y + "]");*/
          total += checkScore(s);
          s = "";
          jau += 0;
        }
        //System.out.println("--------------");
      }
      //System.out.println("DiagonalTR: " + total);
      return total;
    }


    public static int score(Board b){
      int score = 0;
      score = b.checkHor(b.turn) + b.checkVert(b.turn) + b.checkDiagTL(b.turn) + b.checkDiagTR(b.turn);
      if(b.turn == 'X'){
        score = score - 16;
      }
      else if (b.turn == 'O'){
        score = score + 16;
      }
      //System.out.println(score);
      return score;
    }


    public Boolean checkWin(){
      if(drawcheck())
      if (this.checkHor(this.turn) >= 512 || this.checkVert(this.turn) >= 512 || this.checkDiagTL(this.turn) >= 512 || this.checkDiagTR(this.turn) >= 512) return true;
      else if (this.checkHor(this.turn) <= -512 || this.checkVert(this.turn) <= -512 || this.checkDiagTL(this.turn) <= -512 || this.checkDiagTR(this.turn) <= -512) return true;
      return false;
    }


    public Boolean drawcheck(){ // verifica se há empate
      System.out.println(this.score);
      if(this.score != 16 || this.score !=-16){
        return false;
      }
      for (int i=0; i<7; i++){
        if(this.board[i][5] == ' '){
          return false;
        }
      }
      return true;
    }


    public int magicheck(){
      if(this.drawcheck()) return 2; // 2 se for empate
      if (this.checkHor(this.turn) >= 512 || this.checkVert(this.turn) >= 512 || this.checkDiagTL(this.turn) >= 512 || this.checkDiagTR(this.turn) >= 512) return 1; // 1 se  pc ganhar
      else if (this.checkHor(this.turn) <= -512 || this.checkVert(this.turn) <= -512 || this.checkDiagTL(this.turn) <= -512 || this.checkDiagTR(this.turn) <= -512) return -1; // -1 se o gajo ganhar
      return 0; // se nao houver nada retorna 0
    }


    public int min(int a, int b){
        return (a>b) ? b : a;
    }
}
