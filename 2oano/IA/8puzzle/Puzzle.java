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
    }
    if (tabI.solvability() != tabF.solvability()){
      System.out.println("Nao tem solucao");
      System.exit(0);
    }
    tabI.scoreO = Tabuleiro.setScoreO(tabI, tabF);
    tabI.scoreM = Tabuleiro.setScoreM(tabI, tabF);

    int esc = 0;
    long startTime = 0;
    long duration = 0;
    while(esc <8){
      printMenu();
      esc = stdin.nextInt();
      switch (esc){
        case 1:
          Tabuleiro tabBFS = new Tabuleiro(tabSide);
          System.out.println("Resolucao passo a passo:");
          System.out.println("-------------");
          startTime = System.currentTimeMillis();
          tabBFS = Algoritmos.BFS(tabI, tabF);
          duration = System.currentTimeMillis() - startTime;
          Tabuleiro.print_path(tabBFS);
          System.out.println();
          System.out.println("Tempo de Execução: " + duration + " ms");
          System.out.println();
          break;
        case 2:
          Tabuleiro tabDFS = new Tabuleiro(tabSide);
          System.out.println("Resolucao passo a passo:");
          System.out.println("-------------");
          startTime = System.currentTimeMillis();
          tabDFS = Algoritmos.DFS(tabI, tabF);
          duration = System.currentTimeMillis() - startTime;
          Tabuleiro.print_path(tabDFS);
          System.out.println();
          System.out.println("Tempo de Execução: " + duration + " ms");
          System.out.println();
          break;
        case 3:
          Tabuleiro tabIDFS = new Tabuleiro(tabSide);
          System.out.println("Resolucao passo a passo:");
          System.out.println("-------------");
          startTime = System.currentTimeMillis();
          tabIDFS = Algoritmos.IDFS(tabI, tabF);
          duration = System.currentTimeMillis() - startTime;
          Tabuleiro.print_path(tabIDFS);
          System.out.println();
          System.out.println("Tempo de Execução: " + duration + " ms");
          System.out.println();
          break;
        case 4:
          Tabuleiro tabGreedyO = new Tabuleiro(tabSide);
          System.out.println("Resolucao passo a passo:");
          System.out.println("-------------");
          startTime = System.currentTimeMillis();
          tabGreedyO = Algoritmos.GreedyO(tabI, tabF);
          duration = System.currentTimeMillis() - startTime;
          Tabuleiro.print_path(tabGreedyO);
          System.out.println();
          System.out.println("Tempo de Execução: " + duration + " ms");
          System.out.println();
          break;
        case 5:
          Tabuleiro tabGreedyM = new Tabuleiro(tabSide);
          System.out.println("Resolucao passo a passo:");
          System.out.println("-------------");
          startTime = System.currentTimeMillis();
          tabGreedyM = Algoritmos.GreedyM(tabI, tabF);
          duration = System.currentTimeMillis() - startTime;
          Tabuleiro.print_path(tabGreedyM);
          System.out.println();
          System.out.println("Tempo de Execução: " + duration + " ms");
          System.out.println();
          break;
        case 6:
          Tabuleiro tabAO = new Tabuleiro(tabSide);
          System.out.println("Resolucao passo a passo:");
          System.out.println("-------------");
          startTime = System.currentTimeMillis();
          tabAO = Algoritmos.AStarO(tabI, tabF);
          duration = System.currentTimeMillis() - startTime;
          Tabuleiro.print_path(tabAO);
          System.out.println();
          System.out.println("Tempo de Execução: " + duration + " ms");
          System.out.println();
          break;
        case 7:
          Tabuleiro tabAM = new Tabuleiro(tabSide);
          System.out.println("Resolucao passo a passo:");
          System.out.println("-------------");
          startTime = System.currentTimeMillis();
          tabAM = Algoritmos.AStarM(tabI, tabF);
          duration = System.currentTimeMillis() - startTime;
          Tabuleiro.print_path(tabAM);
          System.out.println();
          System.out.println("Tempo de Execução: " + duration + " ms");
          System.out.println();
          break;
        case 8:
          System.exit(0);
          break;
        default:
          System.out.println("Input errado");
          break;
      }
    }
    stdin.close();
  }


  public static void printMenu(){
    System.out.println("----- Escolha o algoritmo -----");
    System.out.println("1) BFS");
    System.out.println("2) DFS");
    System.out.println("3) IDFS");
    System.out.println("4) Greedy com Heuristica Out of Place");
    System.out.println("5) Greedy com Heuristica Manhattan");
    System.out.println("6) A* com Heuristica Out of Place");
    System.out.println("7) A* com Heuristica Manhattan");
    System.out.println("8) Sair");
  }
}
