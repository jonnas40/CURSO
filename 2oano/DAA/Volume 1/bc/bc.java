import java.util.Scanner;
import java.util.LinkedList;

class bc{

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k = sc.nextInt();
    int loss=0;
    Cliente curr = mew Cliente();
    LinkedList<Cliente> fila= new LinkedList<Cliente>();
    /*for (int i=0; i<k; i++) {
      int h=sc.nextInt();
      int min=sc.nextInt();
      int dem=sc.nextInt();
      if(fila.size()==3) loss++;
      else fila.addLast(new Cliente(h, min, dem));
      curr=fila.pop();
      */
      int h=sc.nextInt();
      int min=sc.nextInt();
      int dem=sc.nextInt();
      while()
    }
  }

class Cliente{
  int h;
  int min;
  int dem;
  int hc;
  int minc;
  Cliente(int h, int min, int dem){
    h=h;
    min=min;
    dem=dem;
    int aux=dem + min;
    if(dem>=60){
      hs=h+1;
      mins=aux-60;
    } else {
      mins=aux;
      hs=h;
    }
  }
}
