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
        for (int i=0; i<6; i++){
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
            sons.add(play(i));
        }
        return sons;
    }

    public Board play(int n){
        int i = 5;
        Board ret = new Board();
        copyBoard(this, ret);
        while (ret.board[i][n] != ' ' && i>=0) {
            i--;
            if (ret.board[i][n] != ' ' && i==0) {
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

    private Boolean check(char c, int x, int y){
        return (this.board[x][y]==c) ? true : false;
    }

    private boolean checkLine(int y, char c){
        int count=0;
        for(int i = 0; i < 7; i++){
            if (this.check(c, y, i)) count++;
            else count=0;
            if (count==4) return true;
        }
        return false;
    }

    private boolean checkCollumn(int x, char c){
        int count=0;
        for(int i = 0; i < 6; i++){
            if (this.check(c, i, x)) count++;
            else count=0;
            if (count==4) return true;
        }
        return false;
    }

    private boolean checkDiagonalLR(int x, int y, char c){
        int count=0;
        int i = y - min(x,y);
        int j = x - min(x,y);
        while( (i<6) && (j<7)){
            if (this.check(c, i, j)) count++;
            else count=0;
            if (count==4) return true;
            i++;
            j++;
        }
        return false;
    }

    private boolean checkDiagonalRL(int x, int y, char c){
        int count=0;
        int j = ((y + x)<7 ? y+x : 6);
        int i = ((x - y)<6 ? x-y : 0);
        while( (i<6) && (j>=0)){
            if (this.check(c, i, j)) count++;
            else count=0;
            if (count==4) return true;
            i++;
            j--;
        }
        return false;
    }

    public Boolean checkWin(){
        if (this.checkLine(this.lastPlayY, this.turn) || this.checkCollumn(this.lastPlayX, this.turn) || this.checkDiagonalLR(this.lastPlayX, this.lastPlayY, this.turn) || this.checkDiagonalRL(this.lastPlayX, this.lastPlayY, this.turn)) return true;
        return false;
    }

    public int min(int a, int b){
        return (a>b) ? b : a;
    }
}
