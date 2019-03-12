import java.util.Scanner;

class connect4{

  public static void printBoard(Board tab){
    System.out.println("+-------------+");
    for (int i=5; i>=0; i--){
      System.out.print("|" + tab.board[i][0] + "|" + tab.board[i][1] + "|" + tab.board[i][2] + "|" + tab.board[i][3] + "|" + tab.board[i][4] + "|" + tab.board[i][5] + "|" + tab.board[i][6] + "|\n");
    }
    System.out.println("+-------------+");
    System.out.println("|0|1|2|3|4|5|6|");
  }


  public static void main(String[] args) {
    //Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    printBoard(tab);
  }
}
