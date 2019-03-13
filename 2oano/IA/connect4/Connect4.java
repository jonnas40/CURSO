import java.util.Scanner;

class Connect4{


  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    Boolean win = false;
    while(win!=true){
        Board.printBoard(tab);
        tab = tab.play(stdin.nextInt());
        win = tab.checkWin();
    }
    Board.printBoard(tab);
    System.out.println(tab.turn + " wins");
  }
}
