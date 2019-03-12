

class Board {

    char[][] board;

    public newBoard(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; i < 6; i++) {
                this.board[i][j] = '-';
            }
        }
    }

    public play(int n, char c){
        int i = 0;
        while (this.board[n][i] == '-') {
            i++;
        }
        this.board[n][i-1] = c;
    }
}