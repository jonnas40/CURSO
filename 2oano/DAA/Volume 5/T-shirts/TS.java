import java.util.*;

class TS {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int max = sc.nextInt();
    int[] cus = new int[max+1];
    for (int i=1; i<=max; i++) cus[i]= sc.nextInt();
    int nmeses = sc.nextInt();
    int ltini = sc.nextInt();
    int ltfin = sc,nextInt();
    int man = sc.nextInt();
    int[] enc = new int[nmeses+1];
    for (int i=1; i<=nmeses; i++) enc[i] = sc.nextInt();
    int[][] cust = new int[nmeses+1][max+1];
    int[] cmin = new int[nmeses+1];
    int stock = 2;
    for (int i=1; i<=nmeses; i++) {
      for (int j=0; j<=max; j++) {
        if (j==0) cust[i][j]=man*cust[i-1][j];
        else {
            cust[i][j]=
        }
      }
    }
  }
}
