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
      if (tabI.board[i]==0) tabI.zeroIndex=i;
    }
    while (!tabI.solvability()) {
      System.out.println("Estado inicial sem solucao!");
      System.out.println("Por favor volte a introduzir um estado inicial:");
      for (int i=1; i<=(tabSide*tabSide); i++){
        tabI.board[i] = stdin.nextInt();
        if (tabI.board[i]==0) tabI.zeroIndex=i;
      }
    }
    System.out.println("Tabuleiro final:");
    Tabuleiro tabF = new Tabuleiro(tabSide);
    for (int i=1; i<=tabSide*tabSide; i++){
      tabF.board[i] = stdin.nextInt();
      if (tabF.board[i]==0) tabF.zeroIndex=i;
    }
    Tabuleiro tabDFS = new Tabuleiro(tabSide);
    tabDFS = Algoritmos.BFS(tabI, tabF);
    tabDFS.printPath();
    /*System.out.println();
    System.out.println(tabI.printTab());
    System.out.println(tabF.printTab());*/
  }
}
