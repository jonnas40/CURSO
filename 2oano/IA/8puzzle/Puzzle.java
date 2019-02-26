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
    System.out.println("Tabuleiro final:");
    Tabuleiro tabF = new Tabuleiro(tabSide);
    for (int i=1; i<=tabSide*tabSide; i++){
      tabF.board[i] = stdin.nextInt();
      if (tabF.board[i]==0) tabF.zeroIndex=i;
      tabF.adjs_no();
    }
    if (tabI.solvability() != tabF.solvability()){
      System.out.println("Nao tem solucao");
      System.exit(0);
    }
    Tabuleiro tabBFS = new Tabuleiro(tabSide);
    tabBFS = Algoritmos.BFS(tabI, tabF);
    //tabBFS = Algoritmos2.DFS(tabI, tabF);
    System.out.println("Resolucao passo a passo:");
    System.out.println("-------------");
    Tabuleiro.print_path(tabBFS);
    /*System.out.println();
    System.out.println(tabI.printTab());
    System.out.println(tabF.printTab());*/
  }
}
