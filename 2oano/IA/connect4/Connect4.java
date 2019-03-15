import java.util.*;

class Connect4{

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    Boolean win = false;
    Boolean playerTurn = true;
    while(win!=true){
      if (playerTurn) {
        Board.printBoard(tab);
        int i = stdin.nextInt();
        if (i<7 && i>=0){
          playerTurn = false;
          tab = tab.play(i);
          win = tab.checkWin();
        }
        else {
          System.out.println("Coluna não existente");
        }
      }
      else {
        int ac = Algoritmos.minimax(tab);
        tab = tab.play(ac);
        win = tab.checkWin();
        playerTurn = true;
      }
    }
    Board.printBoard(tab);
    System.out.println(tab.turn + " wins");
  }

}
