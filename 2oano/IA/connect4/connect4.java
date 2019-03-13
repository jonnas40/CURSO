import java.util.Scanner;

class connect4{


  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    while(true){
        Board.printBoard(tab);
        tab = tab.play(stdin.nextInt());
    }
  }
}
