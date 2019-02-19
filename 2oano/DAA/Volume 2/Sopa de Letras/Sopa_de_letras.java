import java.util.Scanner;

class Sopa_de_letras{

  public static void main(String[] args) {
    String pal1 = new String();
    String pal2 = new String();
    Scanner sc = new Scanner(System.in);
    pal1=sc.nextLine();
    pal2=sc.nextLine();
    for (int i = 0; i<pal1.length() && i<pal2.length(); i++) {
      if (pal1.charAt(i)!=pal2.charAt(i)) {
        System.out.print(pal1.charAt(i)+pal2.charAt(i));
        return ;
      }
    }
    System.out.println("Nenhum");
  }
}
