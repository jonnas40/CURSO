import java.util.Scanner;
class Puzzle{
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    System.out.println("Tabuleiro de Input");
    int tabi[] = new int[16];
    for (int i=0; i<16; i++){
      tabi[i] = stdin.nextInt();
    }
    System.out.println("Tabuleiro de Output");
    int tabf[] = new int[16];
    for (int i=0; i<16; i++){
      tabf[i] = stdin.nextInt();
    }
  }
}
