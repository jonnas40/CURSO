import java.util.*;

class Board {

    char[][] board;
    char turn;
    char nextTurn;
    int lastPlayX = 0;
    int lastPlayY = 0;


    public Board(){
        board = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.board[i][j] = ' ';
            }
        }
        this.turn = 'O';
        this.nextTurn = 'X';
    }


    public static void printBoard(Board tab){
        System.out.println("+-------------+");
        for (int i=5; i>=0; i--){
          System.out.print("|" + tab.board[i][0] + "|" + tab.board[i][1] + "|" + tab.board[i][2] + "|" + tab.board[i][3] + "|" + tab.board[i][4] + "|" + tab.board[i][5] + "|" + tab.board[i][6] + "|\n");
        }
        System.out.println("+-------------+");
        System.out.println("|0|1|2|3|4|5|6|");
    }


    public static void copyBoard(Board src, Board target){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                target.board[i][j] = src.board[i][j];
            }
        }
        target.nextTurn = src.turn;
        target.turn = src.nextTurn;
        target.lastPlayX = src.lastPlayX;
        target.lastPlayY = src.lastPlayY;
    }


    public Set sons(Board pai){
        Set<Board> sons = new HashSet<Board>();
        for (int i = 0; i < 7; i++) {
            sons.add(pai.play(i));
        }
        return sons;
    }


    public Board play(int n){
        int i = 0;
        Board ret = new Board();
        copyBoard(this, ret);
        while (ret.board[i][n] != ' ' && i<6) {
            i++;
            if (ret.board[i][n] != ' ' && i==5) {
                System.out.println("Jogada invalida!");
                return this;
            }
        }
        ret.board[i][n] = this.nextTurn;
        ret.turn = this.nextTurn;
        ret.nextTurn = this.turn;
        ret.lastPlayX = n;
        ret.lastPlayY = i;
        return ret;
    }


    public int checkScore(char c, String s){
      int numC = 0;
      int numNC = 0;
      for (int i=0; i<s.length(); i++){
        if(s.charAt(i)==c){ numC++; }
        else if(s.charAt(i)!=' '){ numNC++; }
      }
      if(numC == 1 && numNC == 0){ return 1; }
      else if(numC == 2 && numNC == 0){ return 10; }
      else if(numC == 3 && numNC == 0){ return 50; }
      else if(numC == 4 && numNC == 0){ return 512; }
      else if(numC == 0 && numNC == 1){ return -1; }
      else if(numC == 0 && numNC == 2){ return -10; }
      else if(numC == 0 && numNC == 3){ return -50; }
      else if(numC == 0 && numNC == 4){ return -512; }
      else { return 0; }
    }


    public int checkVert(char c){
      int maxh = this.lastPlayY + 4;
      int total = 0;
      String s = "";
      for (int i=0; i<5; i++){
        if(maxh < 6 && maxh-3 <= this.lastPlayY && maxh-3 >=0){
          for (int j = 0; j<4; j++) {
            s += this.board[maxh-j][this.lastPlayX];
          }
          total += checkScore(c,s);
          s = "";
        }
        maxh--;
      }
      System.out.println("Vertical: " + total);
      return total;
    }


    public int checkHor(char c){
      int maxw = this.lastPlayX + 4;
      int total = 0;
      String s = "";
      for (int i=0; i<5; i++){
        if(maxw < 7 && maxw-3 <= this.lastPlayX && maxw-3 >=0){
          for (int j = 0; j<4; j++) {
            s += this.board[this.lastPlayY][maxw-j];
          }
          total += checkScore(c,s);
          s = "";
        }
        maxw--;
      }
      System.out.println("Horizontal: " + total);
      return total;
    }


    public Boolean checkWin(){
        if ((this.checkHor(this.turn) + this.checkVert(this.turn)) > 512 /* + this.checkDiagonalLR(this.lastPlayX, this.lastPlayY, this.turn) + this.checkDiagonalRL(this.lastPlayX, this.lastPlayY, this.turn) > 512*/) return true;
        return false;
    }


    public int min(int a, int b){
        return (a>b) ? b : a;
    }
}
