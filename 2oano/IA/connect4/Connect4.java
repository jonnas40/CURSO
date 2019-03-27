import java.util.*;

class Connect4{


  public static void print_menuP(){
    System.out.println("----- Escolha o algoritmo -----");
    System.out.println("1) Minimax");
    System.out.println("2) Minimax com Alfa-Beta");
    System.out.println("3) Monte Carlo Tree Climbing");
    System.out.println("4) Sair");
  }


  public static void print_menu(){
    System.out.println("----- Selecione a Dificuldade -----");
    System.out.println("1) Fácil");
    System.out.println("2) Médio");
    System.out.println("3) Difícil");
    System.out.println("4) Sair");
  }


  public static void playC4(int esc, int esc2){
    Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    int win = 0;
    Boolean playerTurn = true;
    int ac = -1;
    while(win==0){
      if (playerTurn) {
        Board.printBoard(tab);
        if(ac!=-1) System.out.println("Jogou em: " + ac);
        int i = stdin.nextInt();
        if (i<7 && i>=0){
          int j = 0;
          if(tab.actions().contains(i)){
            tab = tab.play(i);
          }
          else{ 
            System.out.println("Coluna cheia");
            continue;
          }
          win = tab.magicheck();
          playerTurn = false;
        }
        else {
          System.out.println("Coluna não existente");
        }
      }
      else {
        ac = 0;
        switch (esc){
          case 1:
            ac = Algoritmos.minimax(tab, esc2);
            break;
          case 2:
            ac = Algoritmos.alfabeta(tab, esc2);
            break;
          case 3:
            ac = Algoritmos.MCTS(tab, esc2);
            break;
        }
        tab = tab.play(ac);
        win = tab.magicheck();
        playerTurn = true;
      }
    }
    Board.printBoard(tab);
    System.out.println("Jogou em: " + ac);
    if(win==2) System.out.println("Empate");
    else System.out.println(tab.turn + " wins");
  }

  public static void testC4(){
    Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    int win = 0;
    while(win==0){
      Board.printBoard(tab);
      int i = stdin.nextInt();
      if (i<7 && i>=0){
        int j = 0;
        if(tab.actions().contains(i)){
          tab = tab.play(i);
        }
        else{ 
          System.out.println("Coluna cheia");
          continue;
        }
      }
      else {
        System.out.println("Coluna não existente");
      }
      win = tab.magicheck();
      //System.out.println(Board.score(tab));
    }
    Board.printBoard(tab);
    if(win==2) System.out.println("Empate");
    else System.out.println(tab.turn + " wins");
  }


  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    int esc = 0;
    int esc2 = 0;
    while(esc <5){
      print_menuP();
      esc = stdin.nextInt();
      switch (esc){
        case 1:
          print_menu();
          esc2 = stdin.nextInt();
          if(esc2 == 4){break;}
          else if (esc2 > 4 || esc<1){System.out.println("Input errado"); break;}
          playC4(esc, (esc2 * 2 + 2));
          break;
        case 2:
          print_menu();
          esc2 = stdin.nextInt();
          if(esc2 == 4){break;}
          else if (esc2 > 4){System.out.println("Input errado"); break;}
          playC4(esc, (esc2 * 3));
          break;
        case 3:
          print_menu();
          esc2 = stdin.nextInt();
          if(esc2 == 4){break;}
          else if (esc2 > 4){System.out.println("Input errado"); break;}
          playC4(esc, (esc2*60000));
          break;
        case 4:
          System.exit(0);
          break;
        case 5:
          testC4();
          break;
        default:
          System.out.println("Input errado");
          break;
      }
    }
    stdin.close();
  }
}
