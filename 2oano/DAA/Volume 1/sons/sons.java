import java.util.Scanner;
class sons{

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int [] tp = new int [101];
    int tipo;
    int quant;
    for(int i=0; i<n; i++){
      tipo = sc.nextInt();
      quant = sc.nextInt();
      tp[tipo]=quant;
    }
    int pop = sc.nextInt();
    int m;
    int pref;
    int depe=0;
    int sum=0;
    boolean [] sit = new boolean[pop+1];
    for (int i=1;i<=pop ; i++) {
      m = sc.nextInt();
      for (int j=0;j<m ;j++ ) {
        pref = sc.nextInt();
        if (tp[pref]!=0 && sit[i]==false){
          tp[pref]--;
          sit[i]=true;
        }
      }
      if (sit[i]==false) depe++;
    }
    for(int i=0; i<n; i++) sum += tp[i];
    System.out.println(depe);
    System.out.println(sum);
  }
}
