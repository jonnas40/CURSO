import java.util.Scanner;

class Connect4{

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    Boolean win = false;
    while(win!=true){
        Board.printBoard(tab);
        int i = stdin.nextInt();
        if (i<7 && i>=0){
          tab = tab.play(i);
          win = tab.checkWin();
        }
        else {
          System.out.println("Coluna não existente");
        }
    }
    Board.printBoard(tab);
    System.out.println(tab.turn + " wins");
  }

}
