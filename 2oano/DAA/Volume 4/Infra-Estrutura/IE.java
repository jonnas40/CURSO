import java.util.*;
class IE{

public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int nnos = sc.nextInt();
  int lmin = sc.nextInt();
  int lmax = sc.nextInt();
  int cmin = sc.nextInt();
  int cmax = sc.nextInt();
  int alt = sc.nextInt();
  int or = sc.nextInt();
  int dest = sc.nextInt();
  int count =0;
  int p1 = sc.nextInt();
  while(p1!=-1){
    int p2 = sc.nextInt();
    int maxl = sc.nextInt();
    int maxc = sc.nextInt();
    int maxa = sc.nextInt();
    if (maxl>=lmin && maxa>=alt && maxc>=cmin) {
      count++;
    }
    p1=sc.nextInt();
  }
  System.out.println(count);
}
}
