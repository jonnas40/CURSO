import java.util.Scanner;
class Puzzle{
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    System.out.print("Defina tamanho do lado do tabuleiro: ");
    int tabSide = stdin.nextInt;
    int tabSize = tabSide*tabSide;
    int[] tabI = Tabuleiro.newBoard(tabSize+1); //tab[0] nao se usa
    System.out.println("Tabuleiro inicial:");
    for (int i=1; i<=tabSize; i++){
      tabi[i] = stdin.nextInt();
    }
    System.out.println("Tabuleiro final:");
    int[] tabF= Tabuleiro.newBoard(tabSize+1);
    for (int i=1; i<=tabSize; i++){
      tabF[i] = stdin.nextInt();
    }
  }
}
