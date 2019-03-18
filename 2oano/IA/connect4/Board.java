import java.util.*;

class Board {

    char[][] board;
    char turn;
    char nextTurn;
    int lastPlayX = 0;
    int lastPlayY = 0;
    int score;


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
        for (int i = 0; i < 7; i++) {
            sons.add(b.play(i));
        }
        return sons;
    }


    public Board play(int n){
        int i = 0;
        Board ret = new Board();
        copyBoard(this, ret);
        while (ret.board[n][i] != ' ' && i<6) {
          if (ret.board[n][i] != ' ' && i==5) {
            return this;
          }
          i++;
        }
        ret.board[n][i] = this.nextTurn;
        //ret.turn = this.nextTurn;
        //ret.nextTurn = this.turn;
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
      else if(numX == 4 && numO == 0){ return -512; }
      else if(numX == 0 && numO == 1){ return 1; }
      else if(numX == 0 && numO == 2){ return 10; }
      else if(numX == 0 && numO == 3){ return 50; }
      else if(numX == 0 && numO == 4){ return 512; }
      else { return 0; }
    }


    public int checkVert(char c){
      int maxh = this.lastPlayY + 3;
      int total = 0;
      String s = "";
      for (int i=0; i<5; i++){
        if(maxh < 6 && maxh-3 >=0 && maxh-3 <= this.lastPlayY && maxh >= this.lastPlayY){
          for (int j = 0; j<4; j++) {
            //System.out.println("V: [" + this.lastPlayX + "],[" + (maxh-j) + "]" + j);
            s += this.board[this.lastPlayX][maxh-j];
          }
          total += checkScore(s);
          s = "";
        }
        maxh--;
      }
      //System.out.println("Vertical: " + total);
      return total;
    }


    public int checkHor(char c){
      int maxw = this.lastPlayX + 3;
      int total = 0;
      String s = "";
      for (int i=0; i<5; i++){
        if(maxw < 7 && maxw-3 >=0 && maxw-3 <= this.lastPlayX && maxw >= this.lastPlayX){
          for (int j = 0; j<4; j++) {
            //System.out.println("H: [" + (maxw-j)+ "],[" + this.lastPlayY + "]" + j);
            s += this.board[maxw-j][this.lastPlayY];
          }
          total += checkScore(s);
          s = "";
        }
        maxw--;
      }
      //System.out.println("Horizontal: " + total);
      return total;
    }


    public int checkDiagTL(char c){
      int maxh = this.lastPlayY + 3;
      int maxw = this.lastPlayX - 3;
      int total = 0;
      String s = "";
      for(int i=0; i<5; i++){
        if (maxh<6 && maxw>=0 && maxw+3 >= this.lastPlayX && maxw <= this.lastPlayX && maxh-3 <= this.lastPlayY && maxh >= this.lastPlayY && maxw + 3 <7 && maxh - 3 >=0){
          for (int j = 0; j<4; j++) {
            //System.out.println("TL: [" + (maxw+j) + "],[" + (maxh-j) + "]" + j);
            s += this.board[maxw+j][maxh-j];
          }
          total += checkScore(s);
          s = "";
        }
        maxh--;
        maxw++;
      }
      //System.out.println("DiagonalTL: " + total);
      return total;
    }


    public int checkDiagTR(char c){
      int maxh = this.lastPlayY + 3;
      int maxw = this.lastPlayX + 3;
      int total = 0;
      String s = "";
      for (int i=0; i<5; i++){
        if (maxh<6 && maxw<7 && maxw-3 <= this.lastPlayX && maxw >= this.lastPlayX && maxh-3 <= this.lastPlayY && maxh >= this.lastPlayY && maxw - 3 >=0 && maxh - 3 >=0){
          for (int j = 0; j<4; j++) {
            //System.out.println("TR: [" + (maxw-j) + "],[" + (maxh-j) + "]" + j);
            s += this.board[maxw-j][maxh-j];
          }
          total += checkScore(s);
          s = "";
        }
        maxh--;
        maxw--;
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
        if ((this.checkHor(this.turn) + this.checkVert(this.turn) + this.checkDiagTL(this.turn) + this.checkDiagTR(this.turn)) >= 512) return true;
        else if ((this.checkHor(this.turn) + this.checkVert(this.turn) + this.checkDiagTL(this.turn) + this.checkDiagTR(this.turn)) <= -512) return true;
        return false;
    }


    public int min(int a, int b){
        return (a>b) ? b : a;
    }
}
