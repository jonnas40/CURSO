import java.util.Scanner;


class Puzzle{
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    System.out.print("Defina tamanho do lado do tabuleiro: ");
    int tabSide = stdin.nextInt();
    Tabuleiro tabI = new Tabuleiro(tabSide); //tab[0] nao se usa
    System.out.println("Tabuleiro inicial:");
    for (int i=1; i<=(tabSide*tabSide); i++){
      tabI.board[i] = stdin.nextInt();
    }
    System.out.println("Tabuleiro final:");
    Tabuleiro tabF = new Tabuleiro(tabSide);
    for (int i=1; i<=tabSide*tabSide; i++){
      tabF.board[i] = stdin.nextInt();
    }
    /*System.out.println();
    System.out.println(tabI.printTab());
    System.out.println(tabF.printTab());*/
  }
}
