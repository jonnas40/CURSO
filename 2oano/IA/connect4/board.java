import java.util.*;

class Board {

    char[][] board;
    char turn;
    char nextTurn;

    public Board(){
        board = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.board[i][j] = ' ';
            }
        }
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
    }

    public Set sons(Board pai){
        Set<Board> sons = new HashSet<Board>();
        for (int i = 0; i < 7; i++) {
            sons.add(play(i, pai.nextTurn));
        }
        return sons;
    }

    public Board play(int n, char c){
        int i = 0;
        Board ret = new Board();
        copyBoard(this, ret);
        while (ret.board[i][n] == '-') {
            i++;
        }
        ret.board[i-1][n] = c;
        return ret;
    }
}
