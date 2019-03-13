import java.util.Scanner;

class Connect4{


  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    Boolean win = false;
    Boolean playerTurn = true;
    while(win!=true){
      if (playerTurn) {
        playerTurn = false;
        Board.printBoard(tab);
        tab = tab.play(stdin.nextInt());
        win = tab.checkWin();
      }
        tab = tab.play(Algoritmos.minimax(tab));
        win = tab.checkWin();
        playerTurn = true;
    }
    Board.printBoard(tab);
    System.out.println(tab.turn + " wins");
  }
}
