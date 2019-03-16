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


  public static void play(int esc, int esc2){
    while(win!=true){
      if (playerTurn) {
        Board.printBoard(tab);
        int i = stdin.nextInt();
        if (i<7 && i>=0){
          int j = 0;
          playerTurn = false;
          tab = tab.play(i);
          win = tab.checkWin();
        }
        else {
          System.out.println("Coluna não existente");
        }
      }
      else {
        int ac = 0;
        switch (esc2){
          case 1:
            ac = Algoritmos.minimax(tab, esc);
            break;
          case 2:
            //ac = Algoritmos.alfabeta(tab, esc);
            break;
          case 3:
            //ac = Algoritmos.montecarlo(tab, esc);
            break;
        }
        tab = tab.play(ac);
        win = tab.checkWin();
        playerTurn = true;
      }
    }
    Board.printBoard(tab);
    System.out.println(tab.turn + " wins");
  }


  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Board tab = new Board();
    Boolean win = false;
    Boolean playerTurn = true;
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
          playminimax(esc2);
          break;
        case 2:
          print_menu();
          esc2 = stdin.nextInt();
          if(esc2 == 4){break;}
          else if (esc2 > 4){System.out.println("Input errado"); break;}
          break;
        case 3:
          print_menu();
          esc2 = stdin.nextInt();
          if(esc2 == 4){break;}
          else if (esc2 > 4){System.out.println("Input errado"); break;}
          break;
        case 4:
          System.exit(0);
          break;
        default:
          System.out.println("Input errado");
          break;
      }
    }
  }
}
