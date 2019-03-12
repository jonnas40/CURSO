import java.util.*;

class Board {

    char[][] board;
    char turn;
    char nextTurn;

    public Board(){
        board = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.board[i][j] = '-';
            }
        }
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